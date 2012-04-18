package com.mops.registrar.repositories.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mops.registrar.entities.user.User;

/**
 * Represents the database repository that hosts the {@link User}s
 * 
 * @author dylants
 * 
 */
public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {

    /**
     * Locates a {@link User} by it's unique <code>entityId</code>
     * 
     * @param entityId
     *            The {@link User}'s entity ID
     * @return The {@link User} if found, else {@literal null}
     */
    public User findByEntityId(String entityId);

    /**
     * Locates a {@link User} by it's unique <code>emailAddress</code>
     * 
     * @param emailAddress
     *            The {@link User}'s email address
     * @return The {@link User} if found, else {@literal null}
     */
    public User findByEmailAddress(String emailAddress);
}
