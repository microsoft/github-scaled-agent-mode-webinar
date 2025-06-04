package com.github.av2.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order detail delivery information")
public class OrderDetailDelivery {
    @Schema(description = "The unique identifier for the order detail delivery", required = true)
    private Integer deliveryId;
    
    @Schema(description = "The ID of the order detail this delivery belongs to", required = true)
    private Integer orderDetailId;
    
    @Schema(description = "The quantity being delivered", required = true)
    private Integer quantity;
    
    @Schema(description = "The status of the delivery", required = true)
    private String status;
    
    @Schema(description = "The scheduled delivery date")
    private String deliveryDate;
    
    @Schema(description = "Additional notes about the delivery")
    private String notes;
}