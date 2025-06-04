using System.Collections.Generic;
using OctoCat.Api.Models;
using OctoCat.Api.Data;
using Xunit;

namespace OctoCat.Api.Tests
{
    public class BranchRepositoryTests
    {
        private readonly List<Branch> _branches;
        private readonly GenericRepository<Branch, int> _repository;

        public BranchRepositoryTests()
        {
            // Setup test data
            _branches = new List<Branch>
            {
                new Branch { BranchId = 1, HeadquartersId = 1, Name = "Main Office", Address = "123 Main St", City = "Seattle", State = "WA", PostalCode = "98101", Country = "USA", Phone = "555-123-4567", Email = "main@octocat.com" },
                new Branch { BranchId = 2, HeadquartersId = 1, Name = "Downtown", Address = "456 Pine St", City = "Seattle", State = "WA", PostalCode = "98102", Country = "USA", Phone = "555-987-6543", Email = "downtown@octocat.com" }
            };
            
            _repository = new GenericRepository<Branch, int>(_branches);
        }

        [Fact]
        public void GetAll_ReturnsAllBranches()
        {
            // Act
            var result = _repository.GetAll();
            
            // Assert
            Assert.Equal(2, result.Count());
            Assert.Contains(result, b => b.BranchId == 1);
            Assert.Contains(result, b => b.BranchId == 2);
        }
        
        [Fact]
        public void GetById_ExistingId_ReturnsBranch()
        {
            // Act
            var result = _repository.GetById(1);
            
            // Assert
            Assert.NotNull(result);
            Assert.Equal(1, result.BranchId);
            Assert.Equal("Main Office", result.Name);
        }
        
        [Fact]
        public void GetById_NonExistingId_ReturnsNull()
        {
            // Act
            var result = _repository.GetById(99);
            
            // Assert
            Assert.Null(result);
        }
        
        [Fact]
        public void Add_NewBranch_AddsToCollection()
        {
            // Arrange
            var newBranch = new Branch { BranchId = 3, HeadquartersId = 2, Name = "East Side", City = "Bellevue" };
            
            // Act
            var result = _repository.Add(newBranch);
            
            // Assert
            Assert.Equal(3, _branches.Count);
            Assert.Contains(_branches, b => b.BranchId == 3);
            Assert.Same(newBranch, result);
        }
        
        [Fact]
        public void Update_ExistingBranch_UpdatesInCollection()
        {
            // Arrange
            var updatedBranch = new Branch { BranchId = 1, HeadquartersId = 1, Name = "Updated Name", Address = "Updated Address", City = "Updated City" };
            
            // Act
            var result = _repository.Update(updatedBranch);
            
            // Assert
            Assert.True(result);
            var branch = _repository.GetById(1);
            Assert.NotNull(branch);
            Assert.Equal("Updated Name", branch!.Name);
            Assert.Equal("Updated Address", branch.Address);
            Assert.Equal("Updated City", branch.City);
        }
        
        [Fact]
        public void Update_NonExistingBranch_ReturnsFalse()
        {
            // Arrange
            var nonExistingBranch = new Branch { BranchId = 99, Name = "Does Not Exist" };
            
            // Act
            var result = _repository.Update(nonExistingBranch);
            
            // Assert
            Assert.False(result);
        }
        
        [Fact]
        public void Delete_ExistingBranch_RemovesFromCollection()
        {
            // Act
            var result = _repository.Delete(1);
            
            // Assert
            Assert.True(result);
            Assert.Single(_branches);
            Assert.DoesNotContain(_branches, b => b.BranchId == 1);
        }
        
        [Fact]
        public void Delete_NonExistingBranch_ReturnsFalse()
        {
            // Act
            var result = _repository.Delete(99);
            
            // Assert
            Assert.False(result);
            Assert.Equal(2, _branches.Count);
        }
    }
}