package com.github.av2.api.controller;

import com.github.av2.api.model.Delivery;
import com.github.av2.api.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/deliveries")
@Tag(name = "Deliveries", description = "API endpoints for managing deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    @Operation(summary = "Returns all deliveries")
    public List<Delivery> getAllDeliveries() {
        return deliveryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a delivery by ID")
    @ApiResponse(responseCode = "200", description = "Delivery found")
    @ApiResponse(responseCode = "404", description = "Delivery not found")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Integer id) {
        return deliveryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new delivery")
    @ApiResponse(responseCode = "201", description = "Delivery created successfully")
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a delivery")
    @ApiResponse(responseCode = "200", description = "Delivery updated successfully")
    @ApiResponse(responseCode = "404", description = "Delivery not found")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable Integer id, @RequestBody Delivery delivery) {
        if (!deliveryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        delivery.setDeliveryId(id);
        return ResponseEntity.ok(deliveryService.save(delivery));
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update delivery status and trigger system notification")
    @ApiResponse(responseCode = "200", description = "Status updated successfully")
    @ApiResponse(responseCode = "404", description = "Delivery not found")
    @ApiResponse(responseCode = "500", description = "Error executing notification command")
    public ResponseEntity<Map<String, Object>> updateDeliveryStatus(
            @PathVariable Integer id,
            @RequestBody Map<String, String> request) {
        
        String status = request.get("status");
        String notifyCommand = request.get("notifyCommand");
        
        Map<String, Object> response = deliveryService.updateStatus(id, status, notifyCommand);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a delivery")
    @ApiResponse(responseCode = "204", description = "Delivery deleted successfully")
    @ApiResponse(responseCode = "404", description = "Delivery not found")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Integer id) {
        return deliveryService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}