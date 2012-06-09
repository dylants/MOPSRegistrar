package com.mops.registrar.services.child.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.mops.registrar.entities.Child;
import com.mops.registrar.repositories.child.ChildRepository;
import com.mops.registrar.services.child.ChildService;

/**
 * Database implementation of {@link ChildService}, using the {@link ChildRepository} to store the {@link Child}
 * entities.
 * 
 * @author dylants
 * 
 */
public class DatabaseChildService implements ChildService {

    @Autowired
    private ChildRepository childRepository;

    @Override
    public Child findChildByEntityId(String entityId) {
        return this.childRepository.findByEntityId(entityId);
    }

    @Override
    public Set<Child> findChildren(Set<String> entityIds) {
        return this.childRepository.findAllChildrenByEntityId(entityIds);
    }

    @Override
    public Child addChild(Child child) {
        return this.childRepository.save(child);
    }

    @Override
    public Set<Child> findAllChildren() {
        return new HashSet<Child>(this.childRepository.findAll());
    }

    @Override
    public Child updateChild(String entityId, Child child) {
        return this.childRepository.updateChild(entityId, child);
    }

    /**
     * @return the childRepository
     */
    public ChildRepository getChildRepository() {
        return childRepository;
    }

    /**
     * @param childRepository
     *            the childRepository to set
     */
    public void setChildRepository(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

}