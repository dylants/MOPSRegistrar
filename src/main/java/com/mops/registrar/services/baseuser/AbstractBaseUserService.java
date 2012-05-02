package com.mops.registrar.services.baseuser;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.mops.registrar.entities.BaseUser;

/**
 * Provides some common methods used by {@link BaseUser} services
 * 
 * @author dylants
 * 
 */
public abstract class AbstractBaseUserService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected SaltSource saltSource;

    protected boolean verifyBaseUserPassword(String password, BaseUser baseUser) {
        /*
         * So we're not storing the actual password, but a hash. Because of this, we must verify the password the user
         * entered against this hash. We'll use the password encoder we used to store the password to verify the
         * password.
         */

        // Get the salt (if available)
        Object salt = null;
        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(baseUser);
        }

        return this.passwordEncoder.isPasswordValid(baseUser.getPasswordHash(), password, salt);
    }

    /**
     * Generates a password hash of the <code>clearTextPassword</code>
     * 
     * @param clearTextPassword
     *            The clear text password
     * @param baseUser
     *            The {@link BaseUser} which owns this password
     * @return The password hash
     */
    protected String generatePasswordHash(String clearTextPassword, BaseUser baseUser) {
        // sanity check
        if (StringUtils.isBlank(clearTextPassword)) {
            return null;
        }

        // Get the salt (if available)
        Object salt = null;
        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(baseUser);
        }
        // encode the password (returning a hash)
        String passwordHash = this.passwordEncoder.encodePassword(clearTextPassword, salt);

        return passwordHash;
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