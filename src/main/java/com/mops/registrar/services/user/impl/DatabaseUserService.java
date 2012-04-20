package com.mops.registrar.services.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.mops.registrar.entities.User;
import com.mops.registrar.repositories.user.UserRepository;
import com.mops.registrar.services.user.UserService;

/**
 * Persistent based {@link UserService} which utilizes the {@link UserRepository}
 * 
 * @author dylants
 * 
 */
public class DatabaseUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SaltSource saltSource;

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
    public User updateUser(String entityId, User user) {
        /*
         * In this circumstance, we want to replace the old user with the new user. What we need to do is copy the
         * entityId into the new user, and save it.
         */
        user.setEntityId(entityId);
        return this.userRepository.save(user);
    }

    @Override
    public User addUser(User user) {
        // always remember to encode the password prior to writing it to the database
        String password = user.getPassword();
        Object salt = null;
//        if (this.saltSource != null) {
//            salt = this.saltSource.getSalt(user);
//        }
        String encodedPassword = this.passwordEncoder.encodePassword(password, salt);
        user.setPassword(encodedPassword);

        return this.userRepository.save(user);
    }

    @Override
    public boolean verifyPassword(String password, User user) {
        // TODO Auto-generated method stub
        return false;
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

    /**
     * @return the passwordEncoder
     */
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * @param passwordEncoder
     *            the passwordEncoder to set
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @return the saltSource
     */
    public SaltSource getSaltSource() {
        return saltSource;
    }

    /**
     * @param saltSource
     *            the saltSource to set
     */
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }
}