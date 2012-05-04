package com.mops.registrar.services.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.repositories.user.UserRepository;
import com.mops.registrar.services.user.UserService;

/**
 * Persistent based {@link UserService} which utilizes the {@link UserRepository}
 * 
 * @author dylants
 * 
 */
public class DatabaseUserService extends AbstractUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<MopsUser> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public MopsUser getUserByEntityId(String entityId) {
        return this.userRepository.findByEntityId(entityId);
    }

    @Override
    public MopsUser getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public MopsUser addUser(MopsUser mopsUser) {
        return this.userRepository.save(mopsUser);
    }

    @Override
    public MopsUser updateUsername(String entityId, String username) {
        return this.userRepository.updateUsername(entityId, username);
    }

    @Override
    public MopsUser updatePassword(String entityId, String password) {
        // first we have to get the user
        MopsUser mopsUser = getUserByEntityId(entityId);

        // then we generate the hash from the clear text password
        String passwordHash = generatePasswordHash(password, mopsUser);

        // finally we update the password hash
        return this.userRepository.updatePasswordHash(entityId, passwordHash);
    }

    @Override
    public MopsUser updateRegistrationInformation(String entityId, RegistrationInformation registrationInformation) {
        return this.userRepository.updateRegistrationInformation(entityId, registrationInformation);
    }

    @Override
    public boolean verifyPassword(String password, MopsUser mopsUser) {
        return this.verifyBaseUserPassword(password, mopsUser);
    }

    @Override
    public MopsUser addChildEntityId(String mopsUserEntityId, String childEntityId) {
        return this.userRepository.addChildEntityId(mopsUserEntityId, childEntityId);
    }

    /**
     * @return the userRepository
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * @param userRepository
     *            the userRepository to set
     */
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}