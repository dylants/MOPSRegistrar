package com.mops.registrar.entities;

/**
 * A base level element all elements should implement
 * 
 * @author dysmith
 * 
 */
public interface BaseEntity {

    /**
     * Returns the entity ID of the {@link BaseEntity}
     * 
     * @return The entity ID of the {@link BaseEntity}
     */
    public String getEntityId();

    /**
     * Sets the entity ID of the {@link BaseEntity}
     * 
     * @param entityId
     *            The entity ID to set
     */
    public void setEntityId(String entityId);
}