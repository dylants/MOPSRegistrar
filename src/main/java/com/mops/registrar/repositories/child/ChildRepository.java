package com.mops.registrar.repositories.child;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mops.registrar.entities.Child;

/**
 * Represents the database repository that hosts the {@link Child} entities
 * 
 * @author dylants
 * 
 */
public interface ChildRepository extends MongoRepository<Child, String>, CustomChildRepository {

    /**
     * Locates a {@link Child} by it's entity ID.
     * 
     * @param entityId
     *            The entity ID of the {@link Child}
     * @return The {@link Child} if found, else {@literal null}
     */
    public Child findByEntityId(String entityId);
}
