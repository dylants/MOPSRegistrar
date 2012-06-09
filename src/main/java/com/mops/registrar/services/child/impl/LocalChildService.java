package com.mops.registrar.services.child.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.mops.registrar.entities.Child;
import com.mops.registrar.services.child.ChildService;

/**
 * Local implementation of {@link ChildService}, using a {@link List} to store the {@link Child} entities
 * 
 * @author dylants
 * 
 */
public class LocalChildService implements ChildService {

    Set<Child> children = new HashSet<Child>();

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

    @Override
    public Set<Child> findAllChildren() {
        return this.children;
    }

    @Override
    public Child updateChild(String entityId, Child child) {
        Child originalChild = findChildByEntityId(entityId);
        // sanity check
        if (originalChild == null) {
            return null;
        }

        // copy the properties from the new one to the old one to overwrite what we have access to set
        try {
            BeanUtils.copyProperties(originalChild, child);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return originalChild;
    }

}