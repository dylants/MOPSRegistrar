package com.mops.registrar.services.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.repositories.user.UserRepository;
import com.mops.registrar.services.user.UserService;

/**
 * A mock {@link UserRepository} used to test the {@link UserService}
 * 
 * @author dysmith
 * 
 */
public class MockUserRepository implements UserRepository {
    private List<MopsUser> mockRepository = new ArrayList<MopsUser>();

    @Override
    public List<MopsUser> save(Iterable<? extends MopsUser> entities) {
        for (MopsUser mopsUser : entities) {
            mockRepository.add(mopsUser);
        }
        return mockRepository;
    }

    @Override
    public List<MopsUser> findAll() {
        return mockRepository;
    }

    @Override
    public List<MopsUser> findAll(Sort sort) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public Page<MopsUser> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public MopsUser save(MopsUser entity) {
        mockRepository.add(entity);
        return entity;
    }

    @Override
    public MopsUser findOne(String id) {
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
    public void delete(MopsUser entity) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public void delete(Iterable<? extends MopsUser> entities) {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("not implemented in the mock");
    }

    @Override
    public List<MopsUser> findByFirstNameLastName(String firstName, String lastName) {
        List<MopsUser> returnUsers = new ArrayList<MopsUser>();
        for (MopsUser mopsUser : this.mockRepository) {
            if ((firstName.equals(mopsUser.getRegistrationInformation().getFirstName()))
                    && (lastName.equals(mopsUser.getRegistrationInformation().getLastName()))) {
                returnUsers.add(mopsUser);
            }
        }
        return returnUsers;
    }

    @Override
    public MopsUser updateRegistrationInformation(String entityId, RegistrationInformation registrationInformation) {
        MopsUser mopsUser = findByEntityId(entityId);
        mopsUser.setRegistrationInformation(registrationInformation);
        return mopsUser;
    }

    @Override
    public MopsUser updateUsername(String entityId, String username) {
        MopsUser mopsUser = findByEntityId(entityId);
        mopsUser.setUsername(username);
        return mopsUser;
    }

    @Override
    public MopsUser updatePasswordHash(String entityId, String passwordHash) {
        MopsUser mopsUser = findByEntityId(entityId);
        mopsUser.setPasswordHash(passwordHash);
        return mopsUser;
    }

    @Override
    public MopsUser addChildEntityId(String mopsUserEntityId, String childEntityId) {
        MopsUser mopsUser = findByEntityId(mopsUserEntityId);
        mopsUser.getChildrenEntityIds().add(childEntityId);
        return mopsUser;
    }

    @Override
    public MopsUser findByEntityId(String entityId) {
        for (MopsUser mopsUser : this.mockRepository) {
            if (entityId.equals(mopsUser.getEntityId())) {
                return mopsUser;
            }
        }
        return null;
    }

    @Override
    public MopsUser findByUsername(String username) {
        for (MopsUser mopsUser : this.mockRepository) {
            if (username.equals(mopsUser.getUsername())) {
                return mopsUser;
            }
        }
        return null;
    }
}