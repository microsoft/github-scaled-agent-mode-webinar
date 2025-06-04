using System.Collections.Generic;
using OctoCat.Api.Models;

namespace OctoCat.Api.Data
{
    /// <summary>
    /// Generic repository interface for CRUD operations on entities
    /// </summary>
    public interface IGenericRepository<T, TKey> where T : class, IEntity<TKey>
    {
        /// <summary>
        /// Gets all entities from the repository
        /// </summary>
        IEnumerable<T> GetAll();
        
        /// <summary>
        /// Gets a specific entity by its ID
        /// </summary>
        T? GetById(TKey id);
        
        /// <summary>
        /// Adds a new entity to the repository
        /// </summary>
        T Add(T entity);
        
        /// <summary>
        /// Updates an existing entity in the repository
        /// </summary>
        bool Update(T entity);
        
        /// <summary>
        /// Removes an entity from the repository
        /// </summary>
        bool Delete(TKey id);
    }
}