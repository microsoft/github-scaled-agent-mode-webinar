namespace OctoCat.Api.Models
{
    public class Branch : IEntity<int>
    {
        public int BranchId { get; set; }
        public int HeadquartersId { get; set; }
        public string Name { get; set; } = string.Empty;
        public string Address { get; set; } = string.Empty;
        public string City { get; set; } = string.Empty;
        public string State { get; set; } = string.Empty;
        public string PostalCode { get; set; } = string.Empty;
        public string Country { get; set; } = string.Empty;
        public string Phone { get; set; } = string.Empty;
        public string Email { get; set; } = string.Empty;

        // Implement IEntity interface
        public int Id 
        { 
            get => BranchId;
            set => BranchId = value;
        }
    }
}