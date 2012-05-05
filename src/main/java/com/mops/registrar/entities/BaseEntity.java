package com.mops.registrar.entities;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;

/**
 * A base level entity all entities should extend
 * 
 * @author dylants
 * 
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5516054816840998629L;

    @Id
    private String entityId = null;

    /**
     * Default constructor which generates a unique entity ID
     */
    public BaseEntity() {
        // by default, generate a random UUID for the entity ID
        this.entityId = UUID.randomUUID().toString();
    }

    /**
     * Creates a {@link BaseEntity} specifying the entity ID
     * 
     * @param entityId
     *            The entity ID of this {@link BaseUser}
     */
    public BaseEntity(String entityId) {
        this.entityId = entityId;
    }

    /**
     * @return the entityId
     */
    public String getEntityId() {
        return entityId;
    }
}