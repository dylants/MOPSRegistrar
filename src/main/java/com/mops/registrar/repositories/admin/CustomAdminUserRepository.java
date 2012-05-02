package com.mops.registrar.repositories.admin;

import com.mops.registrar.entities.AdminUser;

/**
 * A custom {@link AdminUserRepository} used to define custom queries or commands
 * 
 * @author dysmith
 * 
 */
public interface CustomAdminUserRepository {

    /**
     * Updates the username of an existing {@link AdminUser}, specified by the <code>entityId</code>.
     * 
     * @param entityId
     *            The entity ID of the {@link AdminUser} to update
     * @param username
     *            The updated username
     * @return The updated {@link AdminUser}
     */
    public AdminUser updateUsername(String entityId, String username);

    /**
     * Updates the password *hash* of an existing {@link AdminUser}, specified by the <code>entityId</code>. Remember,
     * we don't store passwords in the repository, instead we store a hash of the password. This update would update
     * that hash rather than any clear text password.
     * 
     * @param entityId
     *            The entity ID of the {@link AdminUser} to update
     * @param passwordHash
     *            The updated password hash.
     * @return The updated {@link AdminUser}
     */
    public AdminUser updatePasswordHash(String entityId, String passwordHash);
}
