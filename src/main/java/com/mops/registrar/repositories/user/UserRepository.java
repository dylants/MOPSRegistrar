package com.mops.registrar.repositories.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mops.registrar.entities.MopsUser;

/**
 * Represents the database repository that hosts the {@link MopsUser}s
 * 
 * @author dylants
 * 
 */
public interface UserRepository extends MongoRepository<MopsUser, String>, CustomUserRepository {

    /**
     * Locates a {@link MopsUser} by it's unique <code>entityId</code>
     * 
     * @param entityId
     *            The {@link MopsUser}'s entity ID
     * @return The {@link MopsUser} if found, else {@literal null}
     */
    public MopsUser findByEntityId(String entityId);

    /**
     * Locates a {@link MopsUser} by it's unique <code>username</code>
     * 
     * @param username
     *            The {@link MopsUser}'s username (email address)
     * @return The {@link MopsUser} if found, else {@literal null}
     */
    public MopsUser findByUsername(String username);
}
