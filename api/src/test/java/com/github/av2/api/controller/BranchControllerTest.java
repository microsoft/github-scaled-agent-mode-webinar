package com.github.av2.api.controller;

import com.github.av2.api.model.Branch;
import com.github.av2.api.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BranchController.class)
class BranchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @Autowired
    private ObjectMapper objectMapper;

    private Branch testBranch;

    @BeforeEach
    void setUp() {
        testBranch = new Branch(1, 1, "Test Branch", "Test Description", "123 Test St", "John Doe", "john@test.com", "123-456-7890");
    }

    @Test
    void getAllBranches_ShouldReturnList() throws Exception {
        when(branchService.findAll()).thenReturn(Arrays.asList(testBranch));

        mockMvc.perform(get("/api/branches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].branchId").value(testBranch.getBranchId()))
                .andExpect(jsonPath("$[0].name").value(testBranch.getName()));
    }

    @Test
    void getBranchById_WithExistingId_ShouldReturnBranch() throws Exception {
        when(branchService.findById(1)).thenReturn(Optional.of(testBranch));

        mockMvc.perform(get("/api/branches/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.branchId").value(testBranch.getBranchId()))
                .andExpect(jsonPath("$.name").value(testBranch.getName()));
    }

    @Test
    void getBranchById_WithNonExistingId_ShouldReturn404() throws Exception {
        when(branchService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/branches/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createBranch_WithValidData_ShouldReturnCreated() throws Exception {
        when(branchService.save(any(Branch.class))).thenReturn(testBranch);

        mockMvc.perform(post("/api/branches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBranch)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.branchId").value(testBranch.getBranchId()))
                .andExpect(jsonPath("$.name").value(testBranch.getName()));
    }

    @Test
    void updateBranch_WithExistingId_ShouldReturnUpdated() throws Exception {
        when(branchService.findById(1)).thenReturn(Optional.of(testBranch));
        when(branchService.save(any(Branch.class))).thenReturn(testBranch);

        mockMvc.perform(put("/api/branches/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBranch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.branchId").value(testBranch.getBranchId()))
                .andExpect(jsonPath("$.name").value(testBranch.getName()));
    }

    @Test
    void updateBranch_WithNonExistingId_ShouldReturn404() throws Exception {
        when(branchService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/branches/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBranch)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteBranch_WithExistingId_ShouldReturn204() throws Exception {
        when(branchService.deleteById(1)).thenReturn(true);

        mockMvc.perform(delete("/api/branches/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBranch_WithNonExistingId_ShouldReturn404() throws Exception {
        when(branchService.deleteById(999)).thenReturn(false);

        mockMvc.perform(delete("/api/branches/999"))
                .andExpect(status().isNotFound());
    }
}