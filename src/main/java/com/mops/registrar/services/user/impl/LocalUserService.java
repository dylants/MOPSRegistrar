package com.mops.registrar.services.user.impl;

import java.util.ArrayList;
import java.util.List;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.services.user.UserService;

/**
 * Local in-memory {@link UserService} using a simple {@link ArrayList} to store the {@link MopsUser}s
 * 
 * @author dylants
 * 
 */
public class LocalUserService extends AbstractUserService implements UserService {

    private List<MopsUser> mopsUsers = new ArrayList<MopsUser>();

    @Override
    public List<MopsUser> getUsers() {
        return this.mopsUsers;
    }

    @Override
    public MopsUser getUserByEntityId(String entityId) {
        MopsUser returnUser = null;

        for (MopsUser mopsUser : this.mopsUsers) {
            if (mopsUser.getEntityId().equals(entityId)) {
                returnUser = mopsUser;
            }
        }

        return returnUser;
    }

    @Override
    public MopsUser getUserByUsername(String username) {
        MopsUser returnUser = null;

        for (MopsUser mopsUser : this.mopsUsers) {
            if (mopsUser.getUsername().equals(username)) {
                returnUser = mopsUser;
            }
        }

        return returnUser;
    }

    @Override
    public MopsUser getUserByFirstNameLastName(String firstName, String lastName) {
        MopsUser returnUser = null;

        for (MopsUser mopsUser : this.mopsUsers) {
            if (mopsUser.getRegistrationInformation().getFirstName().equals(firstName)
                    && mopsUser.getRegistrationInformation().getLastName().equals(lastName)) {
                returnUser = mopsUser;
            }
        }

        return returnUser;
    }

    @Override
    public MopsUser addUser(MopsUser mopsUser) {
        // only add the user if not null
        if (mopsUser != null) {
            this.mopsUsers.add(mopsUser);
            return mopsUser;
        } else {
            return null;
        }
    }

    @Override
    public MopsUser updateUsername(String entityId, String username) {
        MopsUser mopsUser = getUserByEntityId(entityId);
        // sanity check
        if (mopsUser == null) {
            return null;
        }

        // update the username
        mopsUser.setUsername(username);

        return mopsUser;
    }

    @Override
    public MopsUser updatePassword(String entityId, String password) {
        MopsUser mopsUser = getUserByEntityId(entityId);
        // sanity check
        if (mopsUser == null) {
            return null;
        }

        // generate the hash from the clear text password
        String passwordHash = generatePasswordHash(password, mopsUser);

        // update the password
        mopsUser.setPasswordHash(passwordHash);

        return mopsUser;
    }

    @Override
    public MopsUser updateRegistrationInformation(String entityId, RegistrationInformation registrationInformation) {
        MopsUser mopsUser = getUserByEntityId(entityId);
        // sanity check
        if (mopsUser == null) {
            return null;
        }

        // update the registration information
        mopsUser.setRegistrationInformation(registrationInformation);

        return mopsUser;
    }

    @Override
    public boolean verifyPassword(String password, MopsUser mopsUser) {
        return this.verifyBaseUserPassword(password, mopsUser);
    }

}