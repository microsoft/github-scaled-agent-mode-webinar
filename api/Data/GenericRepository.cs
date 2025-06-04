using System;
using System.Collections.Generic;
using System.Linq;
using OctoCat.Api.Models;

namespace OctoCat.Api.Data
{
    /// <summary>
    /// Generic repository implementation for CRUD operations on entities
    /// </summary>
    public class GenericRepository<T, TKey> : IGenericRepository<T, TKey> where T : class, IEntity<TKey>
    {
        private readonly ICollection<T> _entities;
        private readonly Func<T, TKey> _getIdFunc;

        public GenericRepository(ICollection<T> entities)
        {
            _entities = entities;
            _getIdFunc = entity => entity.Id;
        }

        public IEnumerable<T> GetAll()
        {
            return _entities;
        }

        public T? GetById(TKey id)
        {
            return _entities.FirstOrDefault(e => EqualityComparer<TKey>.Default.Equals(_getIdFunc(e), id));
        }

        public T Add(T entity)
        {
            _entities.Add(entity);
            return entity;
        }

        public bool Update(T entity)
        {
            var existingEntity = GetById(entity.Id);
            if (existingEntity == null)
                return false;

            // Remove the old entity and add the updated one
            _entities.Remove(existingEntity);
            _entities.Add(entity);
            return true;
        }

        public bool Delete(TKey id)
        {
            var entity = GetById(id);
            if (entity == null)
                return false;

            _entities.Remove(entity);
            return true;
        }
    }
}