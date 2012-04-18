package com.mops.registrar.services.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mops.registrar.entities.user.User;
import com.mops.registrar.repositories.user.UserRepository;
import com.mops.registrar.security.CryptUtil;
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
    private CryptUtil cryptUtil;

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
    public void addUser(User user) {
        // always remember to encode the password prior to writing it to the database
        String password = user.getPassword();
        String encodedPassword = this.cryptUtil.encode(password);
        user.setPassword(encodedPassword);

        this.userRepository.save(user);
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
     * @return the cryptUtil
     */
    public CryptUtil getCryptUtil() {
        return cryptUtil;
    }

    /**
     * @param cryptUtil
     *            the cryptUtil to set
     */
    public void setCryptUtil(CryptUtil cryptUtil) {
        this.cryptUtil = cryptUtil;
    }
}