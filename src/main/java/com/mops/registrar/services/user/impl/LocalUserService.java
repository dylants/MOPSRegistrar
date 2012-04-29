package com.mops.registrar.services.user.impl;

import java.util.ArrayList;
import java.util.List;

import com.mops.registrar.entities.MOPSUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.services.baseuser.AbstractBaseUserService;
import com.mops.registrar.services.user.UserService;

/**
 * Local in-memory {@link UserService} using a simple {@link ArrayList} to store the {@link MOPSUser}s
 * 
 * @author dylants
 * 
 */
public class LocalUserService extends AbstractBaseUserService implements UserService {

    private List<MOPSUser> mopsUsers = new ArrayList<MOPSUser>();

    @Override
    public List<MOPSUser> getUsers() {
        return this.mopsUsers;
    }

    @Override
    public MOPSUser getUserByEntityId(String entityId) {
        MOPSUser returnUser = null;

        for (MOPSUser mopsUser : this.mopsUsers) {
            if (mopsUser.getEntityId().equals(entityId)) {
                returnUser = mopsUser;
            }
        }

        return returnUser;
    }

    @Override
    public MOPSUser getUserByEmailAddress(String emailAddress) {
        MOPSUser returnUser = null;

        for (MOPSUser mopsUser : this.mopsUsers) {
            if (mopsUser.getRegistrationInformation().getEmailAddress().equals(emailAddress)) {
                returnUser = mopsUser;
            }
        }

        return returnUser;
    }

    @Override
    public MOPSUser getUserByFirstNameLastName(String firstName, String lastName) {
        MOPSUser returnUser = null;

        for (MOPSUser mopsUser : this.mopsUsers) {
            if (mopsUser.getRegistrationInformation().getFirstName().equals(firstName)
                    && mopsUser.getRegistrationInformation().getLastName().equals(lastName)) {
                returnUser = mopsUser;
            }
        }

        return returnUser;
    }

    @Override
    public MOPSUser addUser(MOPSUser mopsUser) {
        // only add the user if not null
        if (mopsUser != null) {
            this.mopsUsers.add(mopsUser);
            return mopsUser;
        } else {
            return null;
        }
    }

    @Override
    public MOPSUser updateEmailAddress(String entityId, String emailAddress) {
        MOPSUser mopsUser = getUserByEntityId(entityId);
        // sanity check
        if (mopsUser == null) {
            return null;
        }

        // update the email address
        mopsUser.getRegistrationInformation().setEmailAddress(emailAddress);

        return mopsUser;
    }

    @Override
    public MOPSUser updatePassword(String entityId, String password) {
        MOPSUser mopsUser = getUserByEntityId(entityId);
        // sanity check
        if (mopsUser == null) {
            return null;
        }

        // update the password
        mopsUser.setClearTextPassword(password);
        processPassword(mopsUser);

        return mopsUser;
    }

    @Override
    public MOPSUser updateRegistrationInformation(String entityId, RegistrationInformation registrationInformation) {
        MOPSUser mopsUser = getUserByEntityId(entityId);
        // sanity check
        if (mopsUser == null) {
            return null;
        }

        // update the registration information
        mopsUser.setRegistrationInformation(registrationInformation);

        return mopsUser;
    }

    @Override
    public boolean verifyPassword(String password, MOPSUser mopsUser) {
        return this.verifyBaseUserPassword(password, mopsUser);
    }

}