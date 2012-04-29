package com.mops.registrar.repositories.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mops.registrar.entities.MOPSUser;

/**
 * Represents the database repository that hosts the {@link MOPSUser}s
 * 
 * @author dylants
 * 
 */
public interface MOPSUserRepository extends MongoRepository<MOPSUser, String>, CustomMOPSUserRepository {

    /**
     * Locates a {@link MOPSUser} by it's unique <code>entityId</code>
     * 
     * @param entityId
     *            The {@link MOPSUser}'s entity ID
     * @return The {@link MOPSUser} if found, else {@literal null}
     */
    public MOPSUser findByEntityId(String entityId);

    /**
     * Locates a {@link MOPSUser} by it's unique <code>emailAddress</code>
     * 
     * @param emailAddress
     *            The {@link MOPSUser}'s email address
     * @return The {@link MOPSUser} if found, else {@literal null}
     */
    public MOPSUser findByEmailAddress(String emailAddress);
}
