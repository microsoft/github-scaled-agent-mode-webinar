package com.github.av2.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Supplier information")
public class Supplier {
    @Schema(description = "The unique identifier for the supplier", required = true)
    private Integer supplierId;
    
    @Schema(description = "The name of the supplier", required = true)
    private String name;
    
    @Schema(description = "Description of the supplier")
    private String description;
    
    @Schema(description = "Contact person for the supplier")
    private String contactPerson;
    
    @Schema(description = "Contact email for the supplier")
    private String email;
    
    @Schema(description = "Contact phone number for the supplier")
    private String phone;
}