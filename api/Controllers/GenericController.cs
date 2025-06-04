using System;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using OctoCat.Api.Data;
using OctoCat.Api.Models;

namespace OctoCat.Api.Controllers
{
    /// <summary>
    /// Generic controller service for registering CRUD endpoints for any entity
    /// </summary>
    public static class GenericController<T, TKey> where T : class, IEntity<TKey>
    {
        /// <summary>
        /// Registers standard REST API endpoints for an entity
        /// </summary>
        /// <param name="app">The web application to register endpoints with</param>
        /// <param name="repository">The repository for entity operations</param>
        /// <param name="routePrefix">The route prefix for this entity (e.g. 'products', 'suppliers')</param>
        /// <param name="tag">The OpenAPI tag for this entity</param>
        /// <param name="entityName">The name of the entity for operation naming</param>
        public static void RegisterEndpoints(
            WebApplication app,
            IGenericRepository<T, TKey> repository,
            string routePrefix,
            string tag,
            string entityName)
        {
            // Get all entities
            app.MapGet($"/api/{routePrefix}", () =>
                Results.Ok(repository.GetAll()))
                .WithName($"GetAll{entityName}s")
                .WithTags(tag)
                .WithOpenApi();

            // Get entity by ID
            app.MapGet($"/api/{routePrefix}/{{id}}", (TKey id) =>
                {
                    var entity = repository.GetById(id);
                    return entity == null ? Results.NotFound() : Results.Ok(entity);
                })
                .WithName($"Get{entityName}ById")
                .WithTags(tag)
                .WithOpenApi();

            // Create a new entity
            app.MapPost($"/api/{routePrefix}", (T entity) =>
                {
                    repository.Add(entity);
                    return Results.Created($"/api/{routePrefix}/{entity.Id}", entity);
                })
                .WithName($"Create{entityName}")
                .WithTags(tag)
                .WithOpenApi();

            // Update an entity
            app.MapPut($"/api/{routePrefix}/{{id}}", (TKey id, T entity) =>
                {
                    if (!EqualityComparer<TKey>.Default.Equals(id, entity.Id))
                        return Results.BadRequest($"ID in URL does not match {entityName} ID");

                    var result = repository.Update(entity);
                    return result ? Results.Ok(entity) : Results.NotFound();
                })
                .WithName($"Update{entityName}")
                .WithTags(tag)
                .WithOpenApi();

            // Delete an entity
            app.MapDelete($"/api/{routePrefix}/{{id}}", (TKey id) =>
                {
                    var result = repository.Delete(id);
                    return result ? Results.NoContent() : Results.NotFound();
                })
                .WithName($"Delete{entityName}")
                .WithTags(tag)
                .WithOpenApi();
        }
    }
}