package com.github.av2.api.controller;

import com.github.av2.api.model.Headquarters;
import com.github.av2.api.service.HeadquartersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/headquarters")
@Tag(name = "Headquarters", description = "API endpoints for managing headquarters locations")
public class HeadquartersController {
    private final HeadquartersService headquartersService;

    public HeadquartersController(HeadquartersService headquartersService) {
        this.headquartersService = headquartersService;
    }

    @GetMapping
    @Operation(summary = "Returns all headquarters")
    public List<Headquarters> getAllHeadquarters() {
        return headquartersService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a headquarters by ID")
    @ApiResponse(responseCode = "200", description = "Headquarters found")
    @ApiResponse(responseCode = "404", description = "Headquarters not found")
    public ResponseEntity<Headquarters> getHeadquartersById(@PathVariable Integer id) {
        return headquartersService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new headquarters")
    @ApiResponse(responseCode = "201", description = "Headquarters created successfully")
    public Headquarters createHeadquarters(@RequestBody Headquarters headquarters) {
        return headquartersService.save(headquarters);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a headquarters")
    @ApiResponse(responseCode = "200", description = "Headquarters updated successfully")
    @ApiResponse(responseCode = "404", description = "Headquarters not found")
    public ResponseEntity<Headquarters> updateHeadquarters(@PathVariable Integer id, @RequestBody Headquarters headquarters) {
        if (!headquartersService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        headquarters.setHeadquartersId(id);
        return ResponseEntity.ok(headquartersService.save(headquarters));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a headquarters")
    @ApiResponse(responseCode = "204", description = "Headquarters deleted successfully")
    @ApiResponse(responseCode = "404", description = "Headquarters not found")
    public ResponseEntity<Void> deleteHeadquarters(@PathVariable Integer id) {
        return headquartersService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}