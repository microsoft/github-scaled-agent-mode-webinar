using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Net.Http.Json;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.Extensions.DependencyInjection;
using OctoCat.Api.Data;
using OctoCat.Api.Models;
using Xunit;

namespace OctoCat.Api.Tests
{
    public class BranchApiTests : IClassFixture<WebApplicationFactory<Program>>
    {
        private readonly WebApplicationFactory<Program> _factory;
        private readonly HttpClient _client;
        private readonly string _apiPath = "/api/branches";
        private readonly JsonSerializerOptions _jsonOptions = new JsonSerializerOptions
        {
            PropertyNamingPolicy = JsonNamingPolicy.CamelCase
        };

        public BranchApiTests(WebApplicationFactory<Program> factory)
        {
            _factory = factory.WithWebHostBuilder(builder =>
            {
                builder.ConfigureServices(services =>
                {
                    // Remove existing DataRepository and register test data
                    services.AddSingleton<DataRepository>(provider =>
                    {
                        var dataRepository = new DataRepository();
                        // Add test branches
                        dataRepository.Branches.Clear();
                        dataRepository.Branches.Add(new Branch
                        {
                            BranchId = 1,
                            HeadquartersId = 1,
                            Name = "Test Branch",
                            Address = "123 Test St",
                            City = "Testville",
                            State = "TS",
                            PostalCode = "12345",
                            Country = "Testland",
                            Phone = "555-TEST",
                            Email = "test@octocat.com"
                        });
                        
                        return dataRepository;
                    });
                });
            });
            
            _client = _factory.CreateClient();
        }

        [Fact]
        public async Task GetAll_ReturnsSuccessAndBranches()
        {
            // Act
            var response = await _client.GetAsync(_apiPath);
            
            // Assert
            response.EnsureSuccessStatusCode();
            var branches = await response.Content.ReadFromJsonAsync<List<Branch>>();
            Assert.NotNull(branches);
            Assert.Single(branches);
            Assert.Equal("Test Branch", branches[0].Name);
        }

        [Fact]
        public async Task GetById_NonExistingId_ReturnsNotFound()
        {
            // Act
            var response = await _client.GetAsync($"{_apiPath}/999");
            
            // Assert
            Assert.Equal(HttpStatusCode.NotFound, response.StatusCode);
        }

        [Fact]
        public async Task Post_ValidBranch_ReturnsCreatedAndBranch()
        {
            // Arrange
            var newBranch = new Branch
            {
                BranchId = 2,
                HeadquartersId = 1,
                Name = "New Branch",
                Address = "456 New St",
                City = "Newville",
                State = "NW",
                PostalCode = "67890",
                Country = "Newland",
                Phone = "555-NEW",
                Email = "new@octocat.com"
            };
            
            // Act
            var response = await _client.PostAsJsonAsync(_apiPath, newBranch);
            
            // Assert
            Assert.Equal(HttpStatusCode.Created, response.StatusCode);
            Assert.Equal($"{_apiPath}/2", response.Headers.Location?.ToString());
            
            var returnedBranch = await response.Content.ReadFromJsonAsync<Branch>();
            Assert.NotNull(returnedBranch);
            Assert.Equal(2, returnedBranch.BranchId);
            Assert.Equal("New Branch", returnedBranch.Name);
        }

        [Fact]
        public async Task Put_IdMismatch_ReturnsBadRequest()
        {
            // Arrange
            var branchWithWrongId = new Branch
            {
                BranchId = 999, // Doesn't match URL ID
                Name = "Wrong ID"
            };
            
            // Act
            var response = await _client.PutAsJsonAsync($"{_apiPath}/1", branchWithWrongId);
            
            // Assert
            Assert.Equal(HttpStatusCode.BadRequest, response.StatusCode);
        }

        [Fact]
        public async Task Delete_ExistingBranch_ReturnsNoContent()
        {
            // Act
            var response = await _client.DeleteAsync($"{_apiPath}/1");
            
            // Assert
            Assert.Equal(HttpStatusCode.NoContent, response.StatusCode);
            
            // Verify the branch was actually deleted
            var getResponse = await _client.GetAsync($"{_apiPath}/1");
            Assert.Equal(HttpStatusCode.NotFound, getResponse.StatusCode);
        }
    }
}