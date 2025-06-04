package com.github.av2.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order information")
public class Order {
    @Schema(description = "The unique identifier for the order", required = true)
    private Integer orderId;
    
    @Schema(description = "The ID of the branch that placed the order", required = true)
    private Integer branchId;
    
    @Schema(description = "The date and time when the order was placed", required = true)
    private String orderDate;
    
    @Schema(description = "The current status of the order", required = true)
    private String status;
    
    @Schema(description = "Additional notes about the order")
    private String notes;
}