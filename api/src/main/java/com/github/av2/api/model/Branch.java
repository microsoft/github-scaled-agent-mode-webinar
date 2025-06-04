package com.github.av2.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Branch information")
public class Branch {
    @Schema(description = "The unique identifier for the branch", required = true)
    private Integer branchId;
    
    @Schema(description = "The ID of the headquarters this branch belongs to", required = true)
    private Integer headquartersId;
    
    @Schema(description = "The name of the branch", required = true)
    private String name;
    
    @Schema(description = "Description of the branch")
    private String description;
    
    @Schema(description = "Physical address of the branch")
    private String address;
    
    @Schema(description = "Contact person for the branch")
    private String contactPerson;
    
    @Schema(description = "Contact email for the branch")
    private String email;
    
    @Schema(description = "Contact phone number for the branch")
    private String phone;
}