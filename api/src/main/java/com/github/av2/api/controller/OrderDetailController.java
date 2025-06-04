package com.github.av2.api.controller;

import com.github.av2.api.model.OrderDetail;
import com.github.av2.api.service.OrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@Tag(name = "Order Details", description = "API endpoints for managing order details")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    @Operation(summary = "Returns all order details")
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an order detail by ID")
    @ApiResponse(responseCode = "200", description = "Order detail found")
    @ApiResponse(responseCode = "404", description = "Order detail not found")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Integer id) {
        return orderDetailService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new order detail")
    @ApiResponse(responseCode = "201", description = "Order detail created successfully")
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.save(orderDetail);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an order detail")
    @ApiResponse(responseCode = "200", description = "Order detail updated successfully")
    @ApiResponse(responseCode = "404", description = "Order detail not found")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Integer id, @RequestBody OrderDetail orderDetail) {
        if (!orderDetailService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        orderDetail.setOrderDetailId(id);
        return ResponseEntity.ok(orderDetailService.save(orderDetail));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order detail")
    @ApiResponse(responseCode = "204", description = "Order detail deleted successfully")
    @ApiResponse(responseCode = "404", description = "Order detail not found")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Integer id) {
        return orderDetailService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}