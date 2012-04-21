package com.mops.registrar.services.user.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mops.registrar.entities.User;
import com.mops.registrar.repositories.user.UserRepository;
import com.mops.registrar.services.baseuser.AbstractBaseUserService;
import com.mops.registrar.services.user.UserService;

/**
 * Persistent based {@link UserService} which utilizes the {@link UserRepository}
 * 
 * @author dylants
 * 
 */
public class DatabaseUserService extends AbstractBaseUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByEntityId(String entityId) {
        return this.userRepository.findByEntityId(entityId);
    }

    @Override
    public User getUserByEmailAddress(String emailAddress) {
        return this.userRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public User getUserByFirstNameLastName(String firstName, String lastName) {
        List<User> users = this.userRepository.findByFirstNameLastName(firstName, lastName);
        // since we don't know much else, return the first one
        if ((users != null) && (users.size() > 0)) {
            return users.get(0);
        } else {
            // none found
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        // before writing the the database, process the password fields
        processPassword(user);
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(String entityId, User user) {
        /*
         * In this circumstance, we want to replace the old user with the new user. What we need to do is copy the
         * entityId into the new user, and save it.
         */
        user.setEntityId(entityId);
        // if the user updated their password (in addition to anything else)
        if (StringUtils.isNotBlank(user.getClearTextPassword())) {
            // process the password fields
            processPassword(user);
        } else {
            // else we should just copy the old user's password hash over (no change was made)
            User oldUser = this.userRepository.findByEntityId(entityId);
            user.setPasswordHash(oldUser.getPasswordHash());
        }
        return this.userRepository.save(user);
    }

    @Override
    public boolean verifyPassword(String password, User user) {
        /*
         * So we're not storing the actual password, but a hash. Because of this, we must verify the password the user
         * entered against this hash. We'll use the password encoder we used to store the password to verify the
         * password.
         */

        // Get the salt (if available)
        Object salt = null;
        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(user);
        }

        return this.passwordEncoder.isPasswordValid(user.getPasswordHash(), password, salt);
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