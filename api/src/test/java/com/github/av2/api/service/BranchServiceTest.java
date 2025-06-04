package com.github.av2.api.service;

import com.github.av2.api.model.Branch;
import com.github.av2.api.data.SeedData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchServiceTest {
    @Mock
    private SeedData seedData;

    private BranchService branchService;

    private Branch testBranch;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testBranch = new Branch(1, 1, "Test Branch", "Test Description", "123 Test St", "John Doe", "john@test.com", "123-456-7890");
        when(seedData.getBranches()).thenReturn(Arrays.asList(testBranch));
        branchService = new BranchService(seedData);
    }

    @Test
    void findAll_ShouldReturnAllBranches() {
        // Act
        List<Branch> result = branchService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testBranch.getBranchId(), result.get(0).getBranchId());
    }

    @Test
    void findById_WithExistingId_ShouldReturnBranch() {
        // Act
        Optional<Branch> result = branchService.findById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testBranch.getBranchId(), result.get().getBranchId());
    }

    @Test
    void findById_WithNonExistingId_ShouldReturnEmpty() {
        // Act
        Optional<Branch> result = branchService.findById(999);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void save_NewBranch_ShouldAddAndReturnBranch() {
        // Arrange
        Branch newBranch = new Branch(2, 1, "New Branch", "New Description", "456 New St", "Jane Doe", "jane@test.com", "098-765-4321");

        // Act
        Branch result = branchService.save(newBranch);

        // Assert
        assertNotNull(result);
        assertEquals(newBranch.getBranchId(), result.getBranchId());
        assertEquals(2, branchService.findAll().size());
    }

    @Test
    void save_ExistingBranch_ShouldUpdateAndReturnBranch() {
        // Arrange
        Branch updatedBranch = new Branch(1, 1, "Updated Branch", "Updated Description", "789 Update St", "Jim Doe", "jim@test.com", "111-222-3333");

        // Act
        Branch result = branchService.save(updatedBranch);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Branch", result.getName());
        assertEquals(1, branchService.findAll().size());
    }

    @Test
    void deleteById_WithExistingId_ShouldReturnTrue() {
        // Act
        boolean result = branchService.deleteById(1);

        // Assert
        assertTrue(result);
        assertTrue(branchService.findAll().isEmpty());
    }

    @Test
    void deleteById_WithNonExistingId_ShouldReturnFalse() {
        // Act
        boolean result = branchService.deleteById(999);

        // Assert
        assertFalse(result);
        assertEquals(1, branchService.findAll().size());
    }
}