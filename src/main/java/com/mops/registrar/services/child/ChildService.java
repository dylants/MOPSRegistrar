package com.mops.registrar.services.child;

import java.util.Set;

import com.mops.registrar.entities.Child;

/**
 * Provides a service to interact with the {@link Child} entity
 * 
 * @author dylants
 * 
 */
public interface ChildService {

    /**
     * Returns the {@link Child} that contains the entity ID, or {@literal null} if none exist
     * 
     * @param entityId
     *            The entity ID of the child
     * @return The {@link Child} that contains the entity ID, or {@literal null} if none exist
     */
    public Child findChildByEntityId(String entityId);

    /**
     * Returns a {@link Set} of {@link Child} entities which answer to the entity IDs specified
     * 
     * @param entityIds
     *            The entity IDs of the {@link Child} entities to locate
     * @return A {@link Set} of {@link Child} entities
     */
    public Set<Child> findChildren(Set<String> entityIds);

    /**
     * Adds a {@link Child} entity
     * 
     * @param child
     *            The {@link Child} to add
     * @return The added {@link Child}
     */
    public Child addChild(Child child);
}
