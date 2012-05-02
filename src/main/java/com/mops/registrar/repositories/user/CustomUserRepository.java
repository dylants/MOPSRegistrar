package com.mops.registrar.repositories.user;

import java.util.List;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.entities.RegistrationInformation;

/**
 * A custom {@link MopsUser} repository used to define custom queries.
 * 
 * @author dylants
 * 
 */
public interface CustomUserRepository {

    /**
     * Finds a {@link MopsUser} by first name and last name
     * 
     * @param firstName
     *            The {@link MopsUser}'s first name
     * @param lastName
     *            The {@link MopsUser}'s last name
     * @return The resulting {@link List} of {@link MopsUser}s
     */
    public List<MopsUser> findByFirstNameLastName(String firstName, String lastName);

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
     * Updates the username (email address) of an existing {@link MopsUser}, specified by the <code>entityId</code>.
     * 
     * @param entityId
     *            The entity ID of the {@link MopsUser} to update
     * @param username
     *            The updated username
     * @return The updated {@link MopsUser}
     */
    public MopsUser updateUsername(String entityId, String username);

    /**
     * Updates the password *hash* of an existing {@link MopsUser}, specified by the <code>entityId</code>. Remember, we
     * don't store passwords in the repository, instead we store a hash of the password. This update would update that
     * hash rather than any clear text password.
     * 
     * @param entityId
     *            The entity ID of the {@link MopsUser} to update
     * @param passwordHash
     *            The updated password hash.
     * @return The updated {@link MopsUser}
     */
    public MopsUser updatePasswordHash(String entityId, String passwordHash);
}