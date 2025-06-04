namespace OctoCat.Api.Models
{
    public class Product : IEntity<int>
    {
        public int ProductId { get; set; }
        public int SupplierId { get; set; }
        public string Name { get; set; } = string.Empty;
        public string Description { get; set; } = string.Empty;
        public decimal Price { get; set; }
        public string Sku { get; set; } = string.Empty;
        public string Unit { get; set; } = string.Empty;
        public string ImgName { get; set; } = string.Empty;

        // Implement IEntity interface
        public int Id 
        { 
            get => ProductId;
            set => ProductId = value;
        }
    }
}