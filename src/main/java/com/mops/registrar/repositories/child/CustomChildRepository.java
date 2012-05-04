package com.mops.registrar.repositories.child;

import java.util.Set;

import com.mops.registrar.entities.Child;

/**
 * Defines custom {@link Child} queries and commands
 * 
 * @author dylants
 * 
 */
public interface CustomChildRepository {

    /**
     * Finds all the {@link Child} elements that contain the entity IDs specified in the {@link Set}
     * 
     * @param entityIds
     *            The entity IDs of the {@link Child} entities to return
     * @return A {@link Set} of {@link Child} elements that contain an entity ID specified in the <code>entityIds</code>
     */
    public Set<Child> findAllChildrenByEntityId(Set<String> entityIds);
}
