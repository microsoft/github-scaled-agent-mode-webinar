package com.github.av2.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Delivery information")
public class Delivery {
    @Schema(description = "The unique identifier for the delivery", required = true)
    private Integer deliveryId;
    
    @Schema(description = "The ID of the branch this delivery is for", required = true)
    private Integer branchId;
    
    @Schema(description = "The status of the delivery", required = true)
    private String status;
    
    @Schema(description = "Delivery date and time")
    private String deliveryDate;
    
    @Schema(description = "Additional notes about the delivery")
    private String notes;
}