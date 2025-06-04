namespace OctoCat.Api.Models
{
    /// <summary>
    /// Base interface for all entity models
    /// </summary>
    public interface IEntity<TKey>
    {
        /// <summary>
        /// Gets or sets the unique identifier for the entity
        /// </summary>
        TKey Id { get; set; }
    }
}