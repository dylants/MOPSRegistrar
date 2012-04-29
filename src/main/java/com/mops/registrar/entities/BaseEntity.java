package com.mops.registrar.entities;

import org.springframework.data.annotation.Id;

/**
 * A base level entity all entities should extend
 * 
 * @author dylants
 * 
 */
public class BaseEntity {

    @Id
    private String entityId = null;

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