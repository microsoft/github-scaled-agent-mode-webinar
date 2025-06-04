package com.github.av2.api.controller;

import com.github.av2.api.model.Branch;
import com.github.av2.api.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@Tag(name = "Branches", description = "API endpoints for managing branches")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    @Operation(summary = "Returns all branches")
    public List<Branch> getAllBranches() {
        return branchService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a branch by ID")
    @ApiResponse(responseCode = "200", description = "Branch found")
    @ApiResponse(responseCode = "404", description = "Branch not found")
    public ResponseEntity<Branch> getBranchById(@PathVariable Integer id) {
        return branchService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new branch")
    @ApiResponse(responseCode = "201", description = "Branch created successfully")
    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.save(branch);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a branch")
    @ApiResponse(responseCode = "200", description = "Branch updated successfully")
    @ApiResponse(responseCode = "404", description = "Branch not found")
    public ResponseEntity<Branch> updateBranch(@PathVariable Integer id, @RequestBody Branch branch) {
        if (!branchService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        branch.setBranchId(id);
        return ResponseEntity.ok(branchService.save(branch));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a branch")
    @ApiResponse(responseCode = "204", description = "Branch deleted successfully")
    @ApiResponse(responseCode = "404", description = "Branch not found")
    public ResponseEntity<Void> deleteBranch(@PathVariable Integer id) {
        return branchService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}