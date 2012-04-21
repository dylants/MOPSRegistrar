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
public class LocalAdminUserService implements AdminUserService {

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
    public AdminUser updateAdminUser(String entityId, AdminUser adminUser) {
        // get the old admin user
        AdminUser oldAdminUser = getAdminUserByEntityId(entityId);
        // remove it from our repository
        this.adminUsers.remove(oldAdminUser);
        // update the new one with the entity ID of the old
        adminUser.setEntityId(oldAdminUser.getEntityId());
        // add it and return it
        return addAdminUser(adminUser);
    }

}