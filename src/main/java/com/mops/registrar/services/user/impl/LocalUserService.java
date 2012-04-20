package com.mops.registrar.services.user.impl;

import java.util.ArrayList;
import java.util.List;

import com.mops.registrar.entities.User;
import com.mops.registrar.services.user.UserService;

/**
 * Local in-memory {@link UserService} using a simple {@link ArrayList} to store the {@link User}s
 * 
 * @author dylants
 * 
 */
public class LocalUserService implements UserService {

    private List<User> users = new ArrayList<User>();

    @Override
    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public User getUserByEntityId(String entityId) {
        User returnUser = null;

        for (User user : this.users) {
            if (user.getEntityId().equals(entityId)) {
                returnUser = user;
            }
        }

        return returnUser;
    }

    @Override
    public User getUserByEmailAddress(String emailAddress) {
        User returnUser = null;

        for (User user : this.users) {
            if (user.getEmailAddress().equals(emailAddress)) {
                returnUser = user;
            }
        }

        return returnUser;
    }

    @Override
    public User getUserByFirstNameLastName(String firstName, String lastName) {
        User returnUser = null;

        for (User user : this.users) {
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                returnUser = user;
            }
        }

        return returnUser;
    }

    @Override
    public User updateUser(String entityId, User user) {
        /*
         * The purpose here is to replace the existing user with the information found in user. So really we can just
         * set the ID on the newer object, and replace it in our local List of Users.
         */
        // remove the old
        User oldUser = getUserByEntityId(entityId);
        this.users.remove(oldUser);
        // update the new
        user.setEntityId(entityId);
        // and add the new
        this.users.add(user);

        // return the new
        return user;
    }

    @Override
    public void addUser(User user) {
        // only add the user if not null
        if (user != null) {
            this.users.add(user);
        }
    }
}