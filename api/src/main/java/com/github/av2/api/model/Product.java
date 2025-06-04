package com.github.av2.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Product information")
public class Product {
    @Schema(description = "The unique identifier for the product", required = true)
    private Integer productId;
    
    @Schema(description = "The ID of the supplier for this product", required = true)
    private Integer supplierId;
    
    @Schema(description = "The name of the product", required = true)
    private String name;
    
    @Schema(description = "Description of the product")
    private String description;
    
    @Schema(description = "The price of the product", required = true)
    private Float price;
    
    @Schema(description = "The SKU (Stock Keeping Unit) of the product", required = true)
    private String sku;
    
    @Schema(description = "The unit type (e.g., piece, kg, etc.)", required = true)
    private String unit;
    
    @Schema(description = "The image filename for the product")
    private String imgName;
}