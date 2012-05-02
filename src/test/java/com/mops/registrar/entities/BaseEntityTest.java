package com.mops.registrar.entities;

import org.junit.Assert;
import org.junit.Test;

public class BaseEntityTest {

    @Test
    public void testBaseEntity_Default() {
        BaseEntity baseEntity = new TestBaseEntity();

        Assert.assertNotNull("entityId must not be null", baseEntity.getEntityId());
    }

    @Test
    public void testBaseEntity_WithEntityId() {
        String entityId = "987654321";

        BaseEntity baseEntity = new TestBaseEntity(entityId);

        Assert.assertEquals("entity Id must match", entityId, baseEntity.getEntityId());
    }

    private class TestBaseEntity extends BaseEntity {
        protected TestBaseEntity() {
        }

        protected TestBaseEntity(String entityId) {
            super(entityId);
        }
    }
}
