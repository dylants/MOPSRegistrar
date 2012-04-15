package com.mops.registrar.services.user;

import java.util.List;

import com.mops.registrar.elements.user.User;

/**
 * Simple service for accessing {@link User}s
 * 
 */
public interface UserService {

    /**
     * Returns all the available {@link User}s
     * 
     * @return All available {@link User}s
     */
    public List<User> getUsers();

    /**
     * Returns the {@link User} with the given <code>userName</code>, or {@literal null} if none found.
     * 
     * @param userName
     *            The {@link User}s user name
     * @return The {@link User} if found, else {@literal null}
     */
    public User getUser(String userName);

    /**
     * Returns the {@link User} specified by the <code>firstName</code> and <code>lastName</code>, or {@literal null} if
     * none exist.
     * 
     * @param firstName
     *            The {@link User}s first name
     * @param lastName
     *            The {@link User}s last name
     * @return The {@link User} if found, else {@literal null}.
     */
    public User getUser(String firstName, String lastName);

    /**
     * Adds a {@link User}
     * 
     * @param user
     *            The {@link User} to add
     */
    public void addUser(User user);
}
