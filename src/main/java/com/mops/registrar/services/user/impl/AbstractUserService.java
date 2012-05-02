package com.mops.registrar.services.user.impl;

import com.mops.registrar.entities.BaseUser;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.services.baseuser.AbstractBaseUserService;
import com.mops.registrar.services.user.UserService;

/**
 * Abstract implementation of {@link UserService}
 * 
 * @author dylants
 * 
 */
public abstract class AbstractUserService extends AbstractBaseUserService implements UserService {

    @Override
    public String generatePasswordHash(String clearTextPassword, MopsUser mopsUser) {
        return generatePasswordHash(clearTextPassword, (BaseUser) mopsUser);
    }

}
