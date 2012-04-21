package com.mops.registrar.entities;

import org.springframework.data.annotation.Id;

/**
 * A base level element all elements should extend
 * 
 * @author dysmith
 * 
 */
public abstract class BaseEntity {

    @Id
    protected String entityId = null;

    /**
     * @return the entityId
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * @param entityId
     *            the entityId to set
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}