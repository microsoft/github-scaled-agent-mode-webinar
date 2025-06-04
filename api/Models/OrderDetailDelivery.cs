namespace OctoCat.Api.Models
{
    public class OrderDetailDelivery : IEntity<int>
    {
        public int OrderDetailDeliveryId { get; set; }
        public int OrderDetailId { get; set; }
        public int DeliveryId { get; set; }
        public int QuantityDelivered { get; set; }
        public string Notes { get; set; } = string.Empty;
        
        // Implement IEntity interface
        public int Id 
        { 
            get => OrderDetailDeliveryId;
            set => OrderDetailDeliveryId = value;
        }
    }
}