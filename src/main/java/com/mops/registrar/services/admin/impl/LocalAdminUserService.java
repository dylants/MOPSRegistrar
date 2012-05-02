package com.mops.registrar.services.admin.impl;

import java.util.ArrayList;
import java.util.List;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.services.admin.AdminUserService;

/**
 * An implementation of {@link AdminUserService} which uses a local {@link List} to store the entities
 * 
 * @author dylants
 * 
 */
public class LocalAdminUserService extends AbstractAdminUserService implements AdminUserService {

    List<AdminUser> adminUsers = new ArrayList<AdminUser>();

    @Override
    public List<AdminUser> getAdminUsers() {
        return this.adminUsers;
    }

    @Override
    public AdminUser getAdminUserByEntityId(String entityId) {
        AdminUser returnAdminUser = null;

        for (AdminUser adminUser : this.adminUsers) {
            if (adminUser.getEntityId().equals(entityId)) {
                returnAdminUser = adminUser;
            }
        }

        return returnAdminUser;
    }

    @Override
    public AdminUser getAdminUserByUsername(String username) {
        AdminUser returnAdminUser = null;

        for (AdminUser adminUser : this.adminUsers) {
            if (adminUser.getUsername().equals(username)) {
                returnAdminUser = adminUser;
            }
        }

        return returnAdminUser;
    }

    @Override
    public AdminUser addAdminUser(AdminUser adminUser) {
        return this.addAdminUser(adminUser);
    }

    @Override
    public AdminUser updateUsername(String entityId, String username) {
        // get the admin user
        AdminUser adminUser = getAdminUserByEntityId(entityId);

        // update the username
        adminUser.setUsername(username);

        // and return it
        return adminUser;
    }

    @Override
    public AdminUser updatePassword(String entityId, String password) {
        // get the admin user
        AdminUser adminUser = getAdminUserByEntityId(entityId);

        // generate the hash from the clear text password
        String passwordHash = generatePasswordHash(password, adminUser);

        // update the password hash
        adminUser.setPasswordHash(passwordHash);

        // and return it
        return adminUser;
    }

}