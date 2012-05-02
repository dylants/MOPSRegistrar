package com.mops.registrar.services.admin;

import java.util.List;

import com.mops.registrar.entities.AdminUser;

/**
 * Service for interacting with {@link AdminUser}s
 * 
 * @author dylants
 * 
 */
public interface AdminUserService {

    /**
     * Returns all available {@link AdminUser}s
     * 
     * @return All available {@link AdminUser}s
     */
    public List<AdminUser> getAdminUsers();

    /**
     * Returns the {@link AdminUser} specified by the unique <code>entityId</code>
     * 
     * @param entityId
     *            The unique entity ID of the {@link AdminUser}
     * @return The {@link AdminUser} if found, else {@literal null}
     */
    public AdminUser getAdminUserByEntityId(String entityId);

    /**
     * Returns the {@link AdminUser} specified by the unique <code>username</code>
     * 
     * @param username
     *            The unique username of the {@link AdminUser}
     * @return The {@link AdminUser} if found, else {@literal null}
     */
    public AdminUser getAdminUserByUsername(String username);

    /**
     * Adds a new {@link AdminUser} to the repository
     * 
     * @param adminUser
     *            The {@link AdminUser} to add
     * @return The added {@link AdminUser}
     */
    public AdminUser addAdminUser(AdminUser adminUser);

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
     * Updates the password of an existing {@link AdminUser}, specified by the <code>entityId</code>.
     * 
     * @param entityId
     *            The entity ID of the {@link AdminUser} to update
     * @param password
     *            The updated password
     * @return The updated {@link AdminUser}
     */
    public AdminUser updatePassword(String entityId, String password);

    /**
     * Generates a password hash of the <code>clearTextPassword</code>
     * 
     * @param clearTextPassword
     *            The clear text password
     * @param adminUser
     *            The {@link AdminUser} which owns this password
     * @return The password hash
     */
    public String generatePasswordHash(String clearTextPassword, AdminUser adminUser);
}