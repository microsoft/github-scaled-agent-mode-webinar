using Microsoft.AspNetCore.Cors.Infrastructure;
using Microsoft.OpenApi.Models;
using OctoCat.Api.Models;
using OctoCat.Api.Data;
using OctoCat.Api.Controllers;

// Make the Program class accessible to the test project
[assembly: System.Runtime.CompilerServices.InternalsVisibleTo("api-tests")]

// Adding a public Program class to enable WebApplicationFactory in tests
namespace OctoCat.Api
{
    public class Program
    {
        // This method is required for WebApplicationFactory to work
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);

            // Add services to the container
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { 
                    Title = "OctoCAT Supply Chain API", 
                    Version = "v1",
                    Description = "REST API documentation for OctoCAT Supply Chain Management" 
                });
            });

            // Add CORS for frontend
            builder.Services.AddCors(options =>
            {
                options.AddPolicy("AllowFrontend", policy =>
                {
                    policy.WithOrigins(
                            "http://localhost:5137")
                        .SetIsOriginAllowedToAllowWildcardSubdomains() // Allow Codespace domains
                        .WithMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .AllowAnyHeader()
                        .AllowCredentials();
                });
            });

            // Add repository services for data access
            builder.Services.AddSingleton<DataRepository>();

            var app = builder.Build();

            // Configure the HTTP request pipeline
            if (app.Environment.IsDevelopment())
            {
                app.UseSwagger();
                app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "OctoCAT API v1"));
            }

            app.UseCors("AllowFrontend");

            // Configure API routes
            app.MapGet("/", () => "OctoCAT Supply Chain Management API")
               .WithName("Root");

            // Register API endpoints using the generic controller pattern
            RegisterEndpoints(app);

            app.Run();
        }

        // Register all API endpoints using our generic controller pattern
        private static void RegisterEndpoints(WebApplication app)
        {
            var dataRepo = app.Services.GetRequiredService<DataRepository>();
            
            // Register Product endpoints
            GenericController<Product, int>.RegisterEndpoints(
                app, 
                dataRepo.ProductRepository, 
                "products", 
                "Products", 
                "Product");
            
            // Register Supplier endpoints
            GenericController<Supplier, int>.RegisterEndpoints(
                app, 
                dataRepo.SupplierRepository, 
                "suppliers", 
                "Suppliers", 
                "Supplier");
            
            // Register Headquarters endpoints
            GenericController<Headquarters, int>.RegisterEndpoints(
                app, 
                dataRepo.HeadquartersRepository, 
                "headquarters", 
                "Headquarters", 
                "Headquarters");
            
            // Register Branch endpoints
            GenericController<Branch, int>.RegisterEndpoints(
                app, 
                dataRepo.BranchRepository, 
                "branches", 
                "Branches", 
                "Branch");
            
            // Register Order endpoints
            GenericController<Order, int>.RegisterEndpoints(
                app, 
                dataRepo.OrderRepository, 
                "orders", 
                "Orders", 
                "Order");
            
            // Register OrderDetail endpoints
            GenericController<OrderDetail, int>.RegisterEndpoints(
                app, 
                dataRepo.OrderDetailRepository, 
                "order-details", 
                "OrderDetails", 
                "OrderDetail");
            
            // Register Delivery endpoints
            GenericController<Delivery, int>.RegisterEndpoints(
                app, 
                dataRepo.DeliveryRepository, 
                "deliveries", 
                "Deliveries", 
                "Delivery");
            
            // Register OrderDetailDelivery endpoints
            GenericController<OrderDetailDelivery, int>.RegisterEndpoints(
                app, 
                dataRepo.OrderDetailDeliveryRepository, 
                "order-detail-deliveries", 
                "OrderDetailDeliveries", 
                "OrderDetailDelivery");
        }
    }
}
