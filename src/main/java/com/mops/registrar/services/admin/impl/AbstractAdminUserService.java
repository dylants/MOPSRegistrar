package com.mops.registrar.services.admin.impl;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.BaseUser;
import com.mops.registrar.services.admin.AdminUserService;
import com.mops.registrar.services.baseuser.AbstractBaseUserService;

/**
 * An abstract implementation of {@link AdminUserService}
 * 
 * @author dylants
 * 
 */
public abstract class AbstractAdminUserService extends AbstractBaseUserService implements AdminUserService {

    @Override
    public String generatePasswordHash(String clearTextPassword, AdminUser adminUser) {
        return generatePasswordHash(clearTextPassword, (BaseUser) adminUser);
    }

}
