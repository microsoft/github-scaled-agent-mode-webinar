package com.github.av2.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Headquarters information")
public class Headquarters {
    @Schema(description = "The unique identifier for the headquarters", required = true)
    private Integer headquartersId;
    
    @Schema(description = "The name of the headquarters", required = true)
    private String name;
    
    @Schema(description = "Description of the headquarters")
    private String description;
    
    @Schema(description = "Physical address of the headquarters")
    private String address;
    
    @Schema(description = "Contact person for the headquarters")
    private String contactPerson;
    
    @Schema(description = "Contact email for the headquarters")
    private String email;
    
    @Schema(description = "Contact phone number for the headquarters")
    private String phone;
}