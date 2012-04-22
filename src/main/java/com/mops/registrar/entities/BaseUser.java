package com.mops.registrar.entities;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Base class that all "user" objects should implement. This adds the {@link UserDetails} Spring Security behavior which
 * allows these {@link BaseUser} objects to be used as credentials when accessing secured resources. {@link BaseUser}
 * provides the "username" and "password" attributes along with Spring Security attributes.
 * 
 * @author dylants
 * 
 */
public interface BaseUser extends BaseEntity, UserDetails {

    /**
     * Returns the clear text password (if available) or null (if hashed). See {@link BaseUser#getPasswordHash()}
     * 
     * @return The clear text password if available or null
     */
    public String getClearTextPassword();

    /**
     * Sets the clear text password of the {@link BaseUser}
     * 
     * @param clearTextPassword
     *            The clear text password to set
     */
    public void setClearTextPassword(String clearTextPassword);

    /**
     * Returns the confirmation entry of the clear text password, or null if not available
     * 
     * @return The confirmation entry of the clear text password, or null if not available
     */
    public String getClearTextConfirmPassword();

    /**
     * Sets the confirm clear text password of the {@link BaseUser}
     * 
     * @param clearTextConfirmPassword
     *            The clear text password to set
     */
    public void setClearTextConfirmPassword(String clearTextConfirmPassword);

    /**
     * Returns a hash of the original clear text password of the {@link BaseUser}
     * 
     * @return A hash of the original clear text password
     */
    public String getPasswordHash();

    /**
     * Sets the password hash of the {@link BaseUser}
     * 
     * @param passwordHash
     *            The password hash to set
     */
    public void setPasswordHash(String passwordHash);
}