package com.mops.registrar.services.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mops.registrar.entities.MOPSUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.repositories.user.MOPSUserRepository;
import com.mops.registrar.services.baseuser.AbstractBaseUserService;
import com.mops.registrar.services.user.UserService;

/**
 * Persistent based {@link UserService} which utilizes the {@link MOPSUserRepository}
 * 
 * @author dylants
 * 
 */
public class DatabaseUserService extends AbstractBaseUserService implements UserService {

    @Autowired
    private MOPSUserRepository mopsUserRepository;

    @Override
    public List<MOPSUser> getUsers() {
        return this.mopsUserRepository.findAll();
    }

    @Override
    public MOPSUser getUserByEntityId(String entityId) {
        return this.mopsUserRepository.findByEntityId(entityId);
    }

    @Override
    public MOPSUser getUserByEmailAddress(String emailAddress) {
        return this.mopsUserRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public MOPSUser getUserByFirstNameLastName(String firstName, String lastName) {
        List<MOPSUser> mopsUsers = this.mopsUserRepository.findByFirstNameLastName(firstName, lastName);
        // since we don't know much else, return the first one
        if ((mopsUsers != null) && (mopsUsers.size() > 0)) {
            return mopsUsers.get(0);
        } else {
            // none found
            return null;
        }
    }

    @Override
    public MOPSUser addUser(MOPSUser mopsUser) {
        // before writing the the database, process the password fields
        processPassword(mopsUser);
        return this.mopsUserRepository.save(mopsUser);
    }

    @Override
    public MOPSUser updateEmailAddress(String entityId, String emailAddress) {
        return this.mopsUserRepository.updateEmailAddress(entityId, emailAddress);
    }

    @Override
    public MOPSUser updatePassword(String entityId, String password) {
        // first we have to get the user
        MOPSUser mopsUser = getUserByEntityId(entityId);

        // then we generate the hash from the clear text password
        String passwordHash = generatePasswordHash(password, mopsUser);

        // finally we update the password hash
        return this.mopsUserRepository.updatePasswordHash(entityId, passwordHash);
    }

    @Override
    public MOPSUser updateRegistrationInformation(String entityId, RegistrationInformation registrationInformation) {
        return this.mopsUserRepository.updateRegistrationInformation(entityId, registrationInformation);
    }

    @Override
    public boolean verifyPassword(String password, MOPSUser mopsUser) {
        return this.verifyBaseUserPassword(password, mopsUser);
    }

    /**
     * @return the mopsUserRepository
     */
    public MOPSUserRepository getUserRepository() {
        return mopsUserRepository;
    }

    /**
     * @param mopsUserRepository
     *            the mopsUserRepository to set
     */
    public void setUserRepository(MOPSUserRepository mopsUserRepository) {
        this.mopsUserRepository = mopsUserRepository;
    }
}