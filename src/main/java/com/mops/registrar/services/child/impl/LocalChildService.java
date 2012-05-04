package com.mops.registrar.services.child.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mops.registrar.entities.Child;
import com.mops.registrar.services.child.ChildService;

/**
 * Local implementation of {@link ChildService}, using a {@link List} to store the {@link Child} entities
 * 
 * @author dylants
 * 
 */
public class LocalChildService implements ChildService {

    List<Child> children = new ArrayList<Child>();

    @Override
    public Child findChildByEntityId(String entityId) {
        Child returnChild = null;

        for (Child child : this.children) {
            if (child.getEntityId().equals(entityId)) {
                returnChild = child;
                break;
            }
        }

        return returnChild;
    }

    @Override
    public Set<Child> findChildren(Set<String> entityIds) {
        Set<Child> children = new HashSet<Child>();
        for (String entityId : entityIds) {
            Child child = findChildByEntityId(entityId);
            if (child != null) {
                children.add(child);
            }
        }

        return children;
    }

    @Override
    public Child addChild(Child child) {
        this.children.add(child);
        return child;
    }

}
