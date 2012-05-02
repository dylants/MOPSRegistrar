package com.mops.registrar.repositories.admin;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mops.registrar.entities.AdminUser;

/**
 * Represents the database repository that hosts the {@link AdminUser}s
 * 
 * @author dylants
 * 
 */
public interface AdminUserRepository extends MongoRepository<AdminUser, String>, CustomAdminUserRepository {

    /**
     * Locates an {@link AdminUser} by it's <code>entityId</code>
     * 
     * @param entityId
     *            The unique entity ID of the {@link AdminUser}
     * @return The {@link AdminUser} found, else {@literal null}
     */
    public AdminUser findByEntityId(String entityId);

    /**
     * Locates an {@link AdminUser} by it's <code>username</code>
     * 
     * @param username
     *            The unique username of the {@link AdminUser}
     * @return The {@link AdminUser} found, else {@literal null}
     */
    public AdminUser findByUsername(String username);
}
