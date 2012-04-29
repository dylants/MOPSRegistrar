package com.mops.registrar.services.user;

import java.util.List;

import com.mops.registrar.entities.MOPSUser;
import com.mops.registrar.entities.RegistrationInformation;

/**
 * Provides methods for interacting with the {@link MOPSUser}
 * 
 * @author dylants
 * 
 */
public interface UserService {

    /**
     * Returns all the available {@link MOPSUser}s
     * 
     * @return All available {@link MOPSUser}s
     */
    public List<MOPSUser> getUsers();

    /**
     * Returns the {@link MOPSUser} with the given <code>entityId</code>, or {@literal null} if none found.
     * 
     * @param entityId
     *            The {@link MOPSUser}'s entity ID (unique ID)
     * @return The {@link MOPSUser} if found, else {@literal null}
     */
    public MOPSUser getUserByEntityId(String entityId);

    /**
     * Returns the {@link MOPSUser} with the given <code>emailAddress</code>, or {@literal null} if none found.
     * 
     * @param emailAddress
     *            The {@link MOPSUser}'s email address
     * @return The {@link MOPSUser} if found, else {@literal null}
     */
    public MOPSUser getUserByEmailAddress(String emailAddress);

    /**
     * Returns the {@link MOPSUser} specified by the <code>firstName</code> and <code>lastName</code>, or
     * {@literal null} if none exist.
     * 
     * @param firstName
     *            The {@link MOPSUser}s first name
     * @param lastName
     *            The {@link MOPSUser}s last name
     * @return The {@link MOPSUser} if found, else {@literal null}.
     */
    public MOPSUser getUserByFirstNameLastName(String firstName, String lastName);

    /**
     * Adds a new {@link MOPSUser}
     * 
     * @param mopsUser
     *            The {@link MOPSUser} to add
     * @return the added {@link MOPSUser}
     */
    public MOPSUser addUser(MOPSUser mopsUser);

    /**
     * Updates the email address of an existing {@link MOPSUser}, specified by the <code>entityId</code>.
     * 
     * @param entityId
     *            The entity ID of the {@link MOPSUser} to update
     * @param emailAddress
     *            The updated email address
     * @return The updated {@link MOPSUser}
     */
    public MOPSUser updateEmailAddress(String entityId, String emailAddress);

    /**
     * Updates the password of an existing {@link MOPSUser}, specified by the <code>entityId</code>.
     * 
     * @param entityId
     *            The entity ID of the {@link MOPSUser} to update
     * @param password
     *            The updated password
     * @return The updated {@link MOPSUser}
     */
    public MOPSUser updatePassword(String entityId, String password);

    /**
     * Updates the {@link RegistrationInformation} of an existing {@link MOPSUser}, specified by the
     * <code>entityId</code>. This will replace the existing {@link RegistrationInformation} with the passed in
     * <code>registrationInformation</code>.
     * 
     * @param entityId
     *            The entity ID of an existing {@link MOPSUser}
     * @param registrationInformation
     *            The {@link RegistrationInformation} to update
     * @return The updated {@link MOPSUser}
     */
    public MOPSUser updateRegistrationInformation(String entityId, RegistrationInformation registrationInformation);

    /**
     * Verifies the password matches that of the given {@link MOPSUser}
     * 
     * @param password
     *            The password entered
     * @param mopsUser
     *            The {@link MOPSUser} to check against
     * @return true iff the password matches that of the given {@link MOPSUser}
     */
    public boolean verifyPassword(String password, MOPSUser mopsUser);
}