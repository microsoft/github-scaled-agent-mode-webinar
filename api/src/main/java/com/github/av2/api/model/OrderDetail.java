package com.github.av2.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order detail information")
public class OrderDetail {
    @Schema(description = "The unique identifier for the order detail", required = true)
    private Integer orderDetailId;
    
    @Schema(description = "The ID of the order this detail belongs to", required = true)
    private Integer orderId;
    
    @Schema(description = "The ID of the product being ordered", required = true)
    private Integer productId;
    
    @Schema(description = "The quantity of the product ordered", required = true)
    private Integer quantity;
    
    @Schema(description = "The unit price of the product at the time of order", required = true)
    private Float unitPrice;
    
    @Schema(description = "Additional notes about the order detail")
    private String notes;
}