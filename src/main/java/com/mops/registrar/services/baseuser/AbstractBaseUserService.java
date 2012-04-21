package com.mops.registrar.services.baseuser;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.mops.registrar.entities.BaseUser;

/**
 * Provides some common methods used by {@link BaseUser} servicesO
 * 
 * @author dylants
 * 
 */
public abstract class AbstractBaseUserService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected SaltSource saltSource;

    /**
     * Performs operations necessary to store the password in the database. This includes storing a hash of the
     * password, and wiping the actual password so it is not stored in the database.
     * 
     * @param baseUser
     *            The {@link BaseUser} to process
     */
    protected void processPassword(BaseUser baseUser) {
        String plainTextPassword = baseUser.getClearTextPassword();

        // sanity check
        if (StringUtils.isBlank(plainTextPassword)) {
            return;
        }

        // Get the salt (if available)
        Object salt = null;
        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(baseUser);
        }
        // encode the password (returning a hash)
        String encodedPassword = this.passwordEncoder.encodePassword(plainTextPassword, salt);
        // store the hash which we'll use to verify the base user on subsequent requests
        baseUser.setPasswordHash(encodedPassword);

        // clear the plain text password(s)
        baseUser.setClearTextPassword(null);
        baseUser.setClearTextConfirmPassword(null);
    }

    /**
     * @return the passwordEncoder
     */
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * @param passwordEncoder
     *            the passwordEncoder to set
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @return the saltSource
     */
    public SaltSource getSaltSource() {
        return saltSource;
    }

    /**
     * @param saltSource
     *            the saltSource to set
     */
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }
}