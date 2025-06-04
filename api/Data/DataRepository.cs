using System;
using System.Collections.Generic;
using System.Linq;
using OctoCat.Api.Models;

namespace OctoCat.Api.Data
{
    public class DataRepository
    {
        // In-memory data collections for each entity
        public List<Product> Products { get; private set; } = new List<Product>();
        public List<Supplier> Suppliers { get; private set; } = new List<Supplier>();
        public List<Headquarters> Headquarters { get; private set; } = new List<Headquarters>();
        public List<Branch> Branches { get; private set; } = new List<Branch>();
        public List<Order> Orders { get; private set; } = new List<Order>();
        public List<OrderDetail> OrderDetails { get; private set; } = new List<OrderDetail>();
        public List<Delivery> Deliveries { get; private set; } = new List<Delivery>();
        public List<OrderDetailDelivery> OrderDetailDeliveries { get; private set; } = new List<OrderDetailDelivery>();

        // Generic repositories for each entity
        public IGenericRepository<Product, int> ProductRepository { get; private set; }
        public IGenericRepository<Supplier, int> SupplierRepository { get; private set; }
        public IGenericRepository<Headquarters, int> HeadquartersRepository { get; private set; }
        public IGenericRepository<Branch, int> BranchRepository { get; private set; }
        public IGenericRepository<Order, int> OrderRepository { get; private set; }
        public IGenericRepository<OrderDetail, int> OrderDetailRepository { get; private set; }
        public IGenericRepository<Delivery, int> DeliveryRepository { get; private set; }
        public IGenericRepository<OrderDetailDelivery, int> OrderDetailDeliveryRepository { get; private set; }

        public DataRepository()
        {
            // Initialize with sample data for demonstration
            SeedData();
            
            // Initialize repositories
            ProductRepository = new GenericRepository<Product, int>(Products);
            SupplierRepository = new GenericRepository<Supplier, int>(Suppliers);
            HeadquartersRepository = new GenericRepository<Headquarters, int>(Headquarters);
            BranchRepository = new GenericRepository<Branch, int>(Branches);
            OrderRepository = new GenericRepository<Order, int>(Orders);
            OrderDetailRepository = new GenericRepository<OrderDetail, int>(OrderDetails);
            DeliveryRepository = new GenericRepository<Delivery, int>(Deliveries);
            OrderDetailDeliveryRepository = new GenericRepository<OrderDetailDelivery, int>(OrderDetailDeliveries);
        }

        private void SeedData()
        {
            // Clear existing data
            Products.Clear();
            Suppliers.Clear();
            Headquarters.Clear();
            Branches.Clear();
            Orders.Clear();
            OrderDetails.Clear();
            Deliveries.Clear();
            OrderDetailDeliveries.Clear();

            // Seed Suppliers
            Suppliers.AddRange(new List<Supplier>
            {
                new Supplier { SupplierId = 1, Name = "OctoCAT Electronics", ContactName = "Alex Johnson", ContactEmail = "alex@octocat.com", Phone = "555-123-4567", Address = "123 Tech Blvd", City = "San Francisco", State = "CA", PostalCode = "94105", Country = "USA" },
                new Supplier { SupplierId = 2, Name = "GitHub Hardware Inc", ContactName = "Sam Smith", ContactEmail = "sam@octocat.com", Phone = "555-987-6543", Address = "456 Code Ave", City = "Seattle", State = "WA", PostalCode = "98104", Country = "USA" }
            });

            // Seed Products
            Products.AddRange(new List<Product>
            {
                new Product { ProductId = 1, SupplierId = 1, Name = "OctoCAT Webcam Pro", Description = "High-definition webcam for developers", Price = 89.99m, Sku = "OCT-WC-001", Unit = "Each", ImgName = "webcam.png" },
                new Product { ProductId = 2, SupplierId = 1, Name = "Copilot Microphone", Description = "Voice-activated AI assistant microphone", Price = 129.99m, Sku = "OCT-MIC-002", Unit = "Each", ImgName = "mic.png" },
                new Product { ProductId = 3, SupplierId = 2, Name = "GitHub Power Tool", Description = "Electric screwdriver with Git-themed bits", Price = 49.99m, Sku = "GH-PT-001", Unit = "Each", ImgName = "powertool.png" },
                new Product { ProductId = 4, SupplierId = 2, Name = "Dev SSD Drive", Description = "1TB solid state drive for developers", Price = 159.99m, Sku = "GH-SSD-002", Unit = "Each", ImgName = "harddrive.png" }
            });

            // Seed Headquarters
            Headquarters.AddRange(new List<Headquarters>
            {
                new Headquarters { HeadquartersId = 1, Name = "OctoCAT Global HQ", Address = "1 OctoCAT Plaza", City = "San Francisco", State = "CA", PostalCode = "94105", Country = "USA", Phone = "555-OC-CORP", Email = "hq@octocat.com" }
            });

            // Seed Branches
            Branches.AddRange(new List<Branch>
            {
                new Branch { BranchId = 1, HeadquartersId = 1, Name = "West Coast Branch", Address = "200 Pacific Hwy", City = "San Diego", State = "CA", PostalCode = "92101", Country = "USA", Phone = "555-OC-WEST", Email = "west@octocat.com" },
                new Branch { BranchId = 2, HeadquartersId = 1, Name = "East Coast Branch", Address = "300 Atlantic Ave", City = "Boston", State = "MA", PostalCode = "02110", Country = "USA", Phone = "555-OC-EAST", Email = "east@octocat.com" }
            });

            // Seed Orders
            Orders.AddRange(new List<Order>
            {
                new Order { OrderId = 1, BranchId = 1, OrderNumber = "ORD-2023-001", OrderDate = DateTime.Now.AddDays(-30), TotalAmount = 679.95m, Status = "Delivered", Notes = "Q2 equipment order" },
                new Order { OrderId = 2, BranchId = 2, OrderNumber = "ORD-2023-002", OrderDate = DateTime.Now.AddDays(-15), TotalAmount = 1299.90m, Status = "In Transit", Notes = "Office expansion order" }
            });

            // Seed Order Details
            OrderDetails.AddRange(new List<OrderDetail>
            {
                new OrderDetail { OrderDetailId = 1, OrderId = 1, ProductId = 1, Quantity = 5, UnitPrice = 89.99m, TotalPrice = 5 * 89.99m, Status = "Delivered" },
                new OrderDetail { OrderDetailId = 2, OrderId = 1, ProductId = 3, Quantity = 3, UnitPrice = 49.99m, TotalPrice = 3 * 49.99m, Status = "Delivered" },
                new OrderDetail { OrderDetailId = 3, OrderId = 2, ProductId = 2, Quantity = 10, UnitPrice = 129.99m, TotalPrice = 10 * 129.99m, Status = "Processing" }
            });

            // Seed Deliveries
            Deliveries.AddRange(new List<Delivery>
            {
                new Delivery { DeliveryId = 1, SupplierId = 1, TrackingNumber = "OCT-TRK-001", DeliveryDate = DateTime.Now.AddDays(-25), Status = "Delivered", Notes = "Standard delivery" },
                new Delivery { DeliveryId = 2, SupplierId = 2, TrackingNumber = "GH-TRK-002", DeliveryDate = DateTime.Now.AddDays(-10), Status = "In Transit", Notes = "Expedited shipping" }
            });

            // Seed Order Detail Deliveries
            OrderDetailDeliveries.AddRange(new List<OrderDetailDelivery>
            {
                new OrderDetailDelivery { OrderDetailDeliveryId = 1, OrderDetailId = 1, DeliveryId = 1, QuantityDelivered = 5, Notes = "Complete delivery" },
                new OrderDetailDelivery { OrderDetailDeliveryId = 2, OrderDetailId = 2, DeliveryId = 2, QuantityDelivered = 3, Notes = "Complete delivery" }
            });
        }

        // Keep existing collections for backward compatibility
        // All operations now delegate to the generic repositories
    }
}
