namespace OctoCat.Api.Models
{
    public class Supplier : IEntity<int>
    {
        public int SupplierId { get; set; }
        public string Name { get; set; } = string.Empty;
        public string ContactName { get; set; } = string.Empty;
        public string ContactEmail { get; set; } = string.Empty;
        public string Phone { get; set; } = string.Empty;
        public string Address { get; set; } = string.Empty;
        public string City { get; set; } = string.Empty;
        public string State { get; set; } = string.Empty;
        public string PostalCode { get; set; } = string.Empty;
        public string Country { get; set; } = string.Empty;

        // Implement IEntity interface
        public int Id 
        { 
            get => SupplierId;
            set => SupplierId = value;
        }
    }
}