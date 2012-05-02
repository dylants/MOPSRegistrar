package com.mops.registrar.services.user;

import java.util.List;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.entities.RegistrationInformation;

/**
 * Provides methods for interacting with the {@link MopsUser}
 * 
 * @author dylants
 * 
 */
public interface UserService {

    /**
     * Returns all the available {@link MopsUser}s
     * 
     * @return All available {@link MopsUser}s
     */
    public List<MopsUser> getUsers();

    /**
     * Returns the {@link MopsUser} with the given <code>entityId</code>, or {@literal null} if none found.
     * 
     * @param entityId
     *            The {@link MopsUser}'s entity ID (unique ID)
     * @return The {@link MopsUser} if found, else {@literal null}
     */
    public MopsUser getUserByEntityId(String entityId);

    /**
     * Returns the {@link MopsUser} with the given <code>username</code>, or {@literal null} if none found.
     * 
     * @param username
     *            The {@link MopsUser}'s username (email address)
     * @return The {@link MopsUser} if found, else {@literal null}
     */
    public MopsUser getUserByUsername(String username);

    /**
     * Returns the {@link MopsUser} specified by the <code>firstName</code> and <code>lastName</code>, or
     * {@literal null} if none exist.
     * 
     * @param firstName
     *            The {@link MopsUser}s first name
     * @param lastName
     *            The {@link MopsUser}s last name
     * @return The {@link MopsUser} if found, else {@literal null}.
     */
    public MopsUser getUserByFirstNameLastName(String firstName, String lastName);

    /**
     * Adds a new {@link MopsUser}
     * 
     * @param mopsUser
     *            The {@link MopsUser} to add
     * @return the added {@link MopsUser}
     */
    public MopsUser addUser(MopsUser mopsUser);

    /**
     * Updates the username (email address) of an existing {@link MopsUser}, specified by the <code>entityId</code>.
     * 
     * @param entityId
     *            The entity ID of the {@link MopsUser} to update
     * @param username
     *            The updated username (email address)
     * @return The updated {@link MopsUser}
     */
    public MopsUser updateUsername(String entityId, String username);

    /**
     * Updates the password of an existing {@link MopsUser}, specified by the <code>entityId</code>.
     * 
     * @param entityId
     *            The entity ID of the {@link MopsUser} to update
     * @param password
     *            The updated password
     * @return The updated {@link MopsUser}
     */
    public MopsUser updatePassword(String entityId, String password);

    /**
     * Updates the {@link RegistrationInformation} of an existing {@link MopsUser}, specified by the
     * <code>entityId</code>. This will replace the existing {@link RegistrationInformation} with the passed in
     * <code>registrationInformation</code>.
     * 
     * @param entityId
     *            The entity ID of an existing {@link MopsUser}
     * @param registrationInformation
     *            The {@link RegistrationInformation} to update
     * @return The updated {@link MopsUser}
     */
    public MopsUser updateRegistrationInformation(String entityId, RegistrationInformation registrationInformation);

    /**
     * Verifies the password matches that of the given {@link MopsUser}
     * 
     * @param password
     *            The password entered
     * @param mopsUser
     *            The {@link MopsUser} to check against
     * @return true iff the password matches that of the given {@link MopsUser}
     */
    public boolean verifyPassword(String password, MopsUser mopsUser);

    /**
     * Generates a password hash of the <code>clearTextPassword</code>
     * 
     * @param clearTextPassword
     *            The clear text password
     * @param mopsUser
     *            The {@link MopsUser} which owns this password
     * @return The password hash
     */
    public String generatePasswordHash(String clearTextPassword, MopsUser mopsUser);
}