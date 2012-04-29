package com.mops.registrar.repositories.user;

import java.util.List;

import com.mops.registrar.entities.MOPSUser;
import com.mops.registrar.entities.RegistrationInformation;

/**
 * A custom {@link MOPSUser} repository used to define custom queries.
 * 
 * @author dylants
 * 
 */
public interface CustomMOPSUserRepository {

    /**
     * Finds a {@link MOPSUser} by first name and last name
     * 
     * @param firstName
     *            The {@link MOPSUser}'s first name
     * @param lastName
     *            The {@link MOPSUser}'s last name
     * @return The resulting {@link List} of {@link MOPSUser}s
     */
    public List<MOPSUser> findByFirstNameLastName(String firstName, String lastName);

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
     * Updates the password *hash* of an existing {@link MOPSUser}, specified by the <code>entityId</code>. Remember, we
     * don't store passwords in the repository, instead we store a hash of the password. This update would update that
     * hash rather than any clear text password.
     * 
     * @param entityId
     *            The entity ID of the {@link MOPSUser} to update
     * @param passwordHash
     *            The updated password hash.
     * @return The updated {@link MOPSUser}
     */
    public MOPSUser updatePasswordHash(String entityId, String passwordHash);
}