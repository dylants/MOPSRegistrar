package com.mops.registrar.entities;

import org.springframework.data.annotation.Id;

/**
 * An abstract implementation of {@link BaseEntity}
 * 
 * @author dylants
 * 
 */
public abstract class AbstractBaseEntity implements BaseEntity {

    @Id
    protected String entityId = null;

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
