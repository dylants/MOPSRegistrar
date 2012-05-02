package com.mops.registrar.services.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.repositories.admin.AdminUserRepository;
import com.mops.registrar.services.admin.AdminUserService;

/**
 * An implementation of {@link AdminUserService} which uses the {@link AdminUserRepository} to interact with the
 * database
 * 
 * @author dylants
 * 
 */
public class DatabaseAdminUserService extends AbstractAdminUserService implements AdminUserService {

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
        return this.adminUserRepository.save(adminUser);
    }

    @Override
    public AdminUser updateUsername(String entityId, String username) {
        return this.adminUserRepository.updateUsername(entityId, username);
    }

    @Override
    public AdminUser updatePassword(String entityId, String password) {
        // first we have to get the user
        AdminUser adminUser = getAdminUserByEntityId(entityId);

        // then we generate the hash from the clear text password
        String passwordHash = generatePasswordHash(password, adminUser);

        // finally we update the password hash
        return this.adminUserRepository.updatePasswordHash(entityId, passwordHash);
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