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
     * Updates an existing {@link AdminUser} specified by the <code>entityId</code> with that contained in the
     * </code>adminUser</code> object
     * 
     * @param entityId
     *            The entity ID of the {@link AdminUser} to update
     * @param adminUser
     *            The {@link AdminUser} information that will be used to update the {@link AdminUser}
     * @return The updated {@link AdminUser}
     */
    public AdminUser updateAdminUser(String entityId, AdminUser adminUser);
}
