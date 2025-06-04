namespace OctoCat.Api.Models
{
    public class OrderDetail : IEntity<int>
    {
        public int OrderDetailId { get; set; }
        public int OrderId { get; set; }
        public int ProductId { get; set; }
        public int Quantity { get; set; }
        public decimal UnitPrice { get; set; }
        public decimal TotalPrice { get; set; }
        public string Status { get; set; } = string.Empty;

        // Implement IEntity interface
        public int Id 
        { 
            get => OrderDetailId;
            set => OrderDetailId = value;
        }
    }
}