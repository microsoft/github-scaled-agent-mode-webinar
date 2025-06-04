using System;

namespace OctoCat.Api.Models
{
    public class Order : IEntity<int>
    {
        public int OrderId { get; set; }
        public int BranchId { get; set; }
        public string OrderNumber { get; set; } = string.Empty;
        public DateTime OrderDate { get; set; }
        public decimal TotalAmount { get; set; }
        public string Status { get; set; } = string.Empty;
        public string Notes { get; set; } = string.Empty;

        // Implement IEntity interface
        public int Id 
        { 
            get => OrderId;
            set => OrderId = value;
        }
    }
}