using System;

namespace OctoCat.Api.Models
{
    public class Delivery : IEntity<int>
    {
        public int DeliveryId { get; set; }
        public int SupplierId { get; set; }
        public string TrackingNumber { get; set; } = string.Empty;
        public DateTime DeliveryDate { get; set; }
        public string Status { get; set; } = string.Empty;
        public string Notes { get; set; } = string.Empty;

        // Implement IEntity interface
        public int Id 
        { 
            get => DeliveryId;
            set => DeliveryId = value;
        }
    }
}