package com.mops.registrar.services.admin.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.repositories.admin.AdminUserRepository;
import com.mops.registrar.services.admin.AdminUserService;
import com.mops.registrar.services.baseuser.AbstractBaseUserService;

/**
 * An implementation of {@link AdminUserService} which uses the {@link AdminUserRepository} to interact with the
 * database
 * 
 * @author dylants
 * 
 */
public class DatabaseAdminUserService extends AbstractBaseUserService implements AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public List<AdminUser> getAdminUsers() {
        return this.adminUserRepository.findAll();
    }

    @Override
    public AdminUser getAdminUserByEntityId(String entityId) {
        return this.adminUserRepository.findByEntityId(entityId);
    }

    @Override
    public AdminUser getAdminUserByUsername(String username) {
        return this.adminUserRepository.findByUsername(username);
    }

    @Override
    public AdminUser addAdminUser(AdminUser adminUser) {
        // before writing the the database, process the password fields
        processPassword(adminUser);
        return this.adminUserRepository.save(adminUser);
    }

    @Override
    public AdminUser updateAdminUser(String entityId, AdminUser adminUser) {
        /*
         * In this circumstance, we want to replace the old admin user with the new admin user. What we need to do is
         * copy the entityId into the new admin user, and save it.
         */
        adminUser.setEntityId(entityId);
        // if the adminUser updated their password (in addition to anything else)
        if (StringUtils.isNotBlank(adminUser.getClearTextPassword())) {
            // process the password fields
            processPassword(adminUser);
        } else {
            // else we should just copy the old adminUser's password hash over (no change was made)
            AdminUser oldAdminUser = this.adminUserRepository.findByEntityId(entityId);
            adminUser.setPasswordHash(oldAdminUser.getPasswordHash());
        }
        return this.adminUserRepository.save(adminUser);
    }

    /**
     * @return the adminUserRepository
     */
    public AdminUserRepository getAdminUserRepository() {
        return adminUserRepository;
    }

    /**
     * @param adminUserRepository
     *            the adminUserRepository to set
     */
    public void setAdminUserRepository(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }
}