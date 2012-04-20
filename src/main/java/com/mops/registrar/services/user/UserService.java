package com.mops.registrar.services.user;

import java.util.List;

import com.mops.registrar.entities.User;
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
     * Returns the {@link User} with the given <code>entityId</code>, or {@literal null} if none found.
     * 
     * @param entityId
     *            The {@link User}'s entity ID (unique ID)
     * @return The {@link User} if found, else {@literal null}
     */
    public User getUserByEntityId(String entityId);

    /**
     * Returns the {@link User} with the given <code>emailAddress</code>, or {@literal null} if none found.
     * 
     * @param emailAddress
     *            The {@link User}'s email address
     * @return The {@link User} if found, else {@literal null}
     */
    public User getUserByEmailAddress(String emailAddress);

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
    public User getUserByFirstNameLastName(String firstName, String lastName);

    /**
     * Adds a {@link User}
     * 
     * @param user
     *            The {@link User} to add
     * @return the added {@link User}
     */
    public User addUser(User user);

    /**
     * Updates the {@link User} specified by the <code>entityId</code> with the data contained in the <code>user</code>
     * object.
     * 
     * @param entityId
     *            The entity ID of the {@link User} that should be updated
     * @param user
     *            Data contained within this object will replace the data contained in the {@link User}
     * @return the updated {@link User}
     */
    public User updateUser(String entityId, User user);

    /**
     * Verifies the password matches that of the given {@link User}
     * 
     * @param password
     *            The password entered
     * @param user
     *            The {@link User} to check against
     * @return true iff the password matches that of the given {@link User}
     */
    public boolean verifyPassword(String password, User user);
}