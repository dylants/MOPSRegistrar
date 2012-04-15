package com.mops.registrar.services.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mops.registrar.elements.user.User;
import com.mops.registrar.repositories.user.UserRepository;
import com.mops.registrar.services.user.UserService;

public class DatabaseUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUser(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    public User getUser(String firstName, String lastName) {
        List<User> users = this.userRepository.findByFirstNameLastName(firstName, lastName);
        // since we don't know much else, return the first one
        if ((users != null) && (users.size() > 0)) {
            return users.get(0);
        } else {
            // none found
            return null;
        }
    }

    public void addUser(User user) {
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
}