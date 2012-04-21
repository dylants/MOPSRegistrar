package com.mops.registrar.entities;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Transient;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

/**
 * Base class that all "user" objects should extend. This adds the {@link UserDetails} Spring Security behavior which
 * allows these {@link BaseUser} objects to be used as credentials when accessing secured resources. {@link BaseUser}
 * provides the
 * 
 * @author dylants
 * 
 */
public abstract class BaseUser extends BaseEntity implements UserDetails, CredentialsContainer {

    // for Serializable
    private static final long serialVersionUID = 8248514813772388434L;

    // username validation done per validator
    protected String username;
    // password validation done per validator
    @Transient
    protected String clearTextPassword = null;
    // confirm password validation done per validator
    @Transient
    protected String clearTextConfirmPassword = null;
    protected String passwordHash = null;
    /*
     * Spring security elements
     */
    protected final Set<GrantedAuthority> authorities;
    protected final boolean accountNonExpired;
    protected final boolean accountNonLocked;
    protected final boolean credentialsNonExpired;
    protected final boolean enabled;

    /**
     * Default constructor (implies ANONYMOUS {@link GrantedAuthority})
     */
    public BaseUser() {
        this(Sets.newHashSet(Authorities.ROLE_ANONYMOUS.getGrantedAuthority()));
    }

    /**
     * Constructs a {@link BaseUser} supplying the {@link GrantedAuthority}s
     * 
     * @param authorities
     *            The {@link GrantedAuthority} of the {@link BaseUser}
     */
    public BaseUser(Set<GrantedAuthority> authorities) {
        // we need the id for password hashing, so always generate one on new
        this.entityId = UUID.randomUUID().toString();
        this.authorities = authorities;
        // provide some defaults for spring security elements
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * This is Spring Security's {@link UserDetails} method, which should return the password we compare against and
     * never return null. Because of this, we'll return the password hash here instead of the actual password. The
     * {@link PasswordEncoder} will be used to compare, and will hash the user's entered password when verifying they
     * match.<br/>
     * <br/>
     * Note: This does NOT return the actual password. See {@link BaseUser#getClearTextPassword()} for the actual
     * password (which is not persisted).
     * 
     */
    @Override
    public String getPassword() {
        return getPasswordHash();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void eraseCredentials() {
        // clear both the password and confirm password (just in case)
        this.clearTextPassword = null;
        this.clearTextConfirmPassword = null;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the clearTextPassword
     */
    public String getClearTextPassword() {
        return clearTextPassword;
    }

    /**
     * @param clearTextPassword
     *            the clearTextPassword to set
     */
    public void setClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    /**
     * @return the clearTextConfirmPassword
     */
    public String getClearTextConfirmPassword() {
        return clearTextConfirmPassword;
    }

    /**
     * @param clearTextConfirmPassword
     *            the clearTextConfirmPassword to set
     */
    public void setClearTextConfirmPassword(String clearTextConfirmPassword) {
        this.clearTextConfirmPassword = clearTextConfirmPassword;
    }

    /**
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash
     *            the passwordHash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}