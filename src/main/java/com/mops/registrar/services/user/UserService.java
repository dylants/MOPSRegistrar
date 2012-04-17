package com.mops.registrar.services.user;

import java.util.List;

import com.mops.registrar.elements.user.User;
import com.mops.registrar.repositories.user.UserRepository;

/**
 * Provides methods for interacting with the {@link User} through the {@link UserRepository}
 * 
 * @author dylants
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
     * Returns the {@link User} with the given <code>emailAddress</code>, or {@literal null} if none found.
     * 
     * @param emailAddress
     *            The {@link User}'s email address
     * @return The {@link User} if found, else {@literal null}
     */
    public User getUser(String emailAddress);

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
