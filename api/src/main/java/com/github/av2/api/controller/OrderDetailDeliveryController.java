package com.github.av2.api.controller;

import com.github.av2.api.model.OrderDetailDelivery;
import com.github.av2.api.service.OrderDetailDeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-detail-deliveries")
@Tag(name = "Order Detail Deliveries", description = "API endpoints for managing order detail deliveries")
public class OrderDetailDeliveryController {
    private final OrderDetailDeliveryService orderDetailDeliveryService;

    public OrderDetailDeliveryController(OrderDetailDeliveryService orderDetailDeliveryService) {
        this.orderDetailDeliveryService = orderDetailDeliveryService;
    }

    @GetMapping
    @Operation(summary = "Returns all order detail deliveries")
    public List<OrderDetailDelivery> getAllOrderDetailDeliveries() {
        return orderDetailDeliveryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an order detail delivery by ID")
    @ApiResponse(responseCode = "200", description = "Order detail delivery found")
    @ApiResponse(responseCode = "404", description = "Order detail delivery not found")
    public ResponseEntity<OrderDetailDelivery> getOrderDetailDeliveryById(@PathVariable Integer id) {
        return orderDetailDeliveryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new order detail delivery")
    @ApiResponse(responseCode = "201", description = "Order detail delivery created successfully")
    public OrderDetailDelivery createOrderDetailDelivery(@RequestBody OrderDetailDelivery orderDetailDelivery) {
        return orderDetailDeliveryService.save(orderDetailDelivery);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an order detail delivery")
    @ApiResponse(responseCode = "200", description = "Order detail delivery updated successfully")
    @ApiResponse(responseCode = "404", description = "Order detail delivery not found")
    public ResponseEntity<OrderDetailDelivery> updateOrderDetailDelivery(
            @PathVariable Integer id,
            @RequestBody OrderDetailDelivery orderDetailDelivery) {
        if (!orderDetailDeliveryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        orderDetailDelivery.setDeliveryId(id);
        return ResponseEntity.ok(orderDetailDeliveryService.save(orderDetailDelivery));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order detail delivery")
    @ApiResponse(responseCode = "204", description = "Order detail delivery deleted successfully")
    @ApiResponse(responseCode = "404", description = "Order detail delivery not found")
    public ResponseEntity<Void> deleteOrderDetailDelivery(@PathVariable Integer id) {
        return orderDetailDeliveryService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}