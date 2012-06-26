package com.mops.registrar.services.child.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mops.registrar.entities.Child;
import com.mops.registrar.repositories.child.ChildRepository;

public class MockChildRepository implements ChildRepository {
    private List<Child> mockRepository = new ArrayList<Child>();

    @Override
    public List<Child> save(Iterable<? extends Child> entities) {
        for (Child child : entities) {
            mockRepository.add(child);
        }
        return mockRepository;
    }

    @Override
    public List<Child> findAll() {
        return mockRepository;
    }

    @Override
    public List<Child> findAll(Sort sort) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public Page<Child> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public Child save(Child entity) {
        this.mockRepository.add(entity);
        return entity;
    }

    @Override
    public Child findOne(String id) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public boolean exists(String id) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public void delete(Child entity) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public void delete(Iterable<? extends Child> entities) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public Set<Child> findAllChildrenByEntityId(Set<String> entityIds) {
        Set<Child> children = new HashSet<Child>();
        for (String entityId : entityIds) {
            Child child = findByEntityId(entityId);
            if (child != null) {
                children.add(child);
            }
        }

        return children;
    }

    @Override
    public Child updateChild(String entityId, Child child) {
        Child originalChild = findByEntityId(entityId);
        // sanity check
        if (originalChild == null) {
            return null;
        }

        // copy the properties from the new one to the old one to overwrite what we have access to set
        try {
            BeanUtils.copyProperties(originalChild, child);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return originalChild;
    }

    @Override
    public Child findByEntityId(String entityId) {
        for (Child child : this.mockRepository) {
            if (child.getEntityId().equals(entityId)) {
                return child;
            }
        }

        // none match, return null
        return null;
    }

}
