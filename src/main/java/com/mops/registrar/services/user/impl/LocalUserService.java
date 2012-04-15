package com.mops.registrar.services.user.impl;

import java.util.ArrayList;
import java.util.List;

import com.mops.registrar.elements.user.User;
import com.mops.registrar.services.user.UserService;

/**
 * Local in-memory {@link UserService} using a simple {@link ArrayList} to store the {@link User}s
 * 
 */
public class LocalUserService implements UserService {

    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return this.users;
    }

    public User getUser(String userName) {
        User returnUser = null;

        for (User user : this.users) {
            if (user.getUserName().equals(userName)) {
                returnUser = user;
            }
        }

        return returnUser;
    }

    public User getUser(String firstName, String lastName) {
        User returnUser = null;

        for (User user : this.users) {
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                returnUser = user;
            }
        }

        return returnUser;
    }

    public void addUser(User user) {
        // only add the user if not null
        if (user != null) {
            this.users.add(user);
        }
    }

}
