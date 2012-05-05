package com.mops.registrar.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

/**
 * This object contains the username and password of a user, implementing Spring Security's {@link UserDetails}.
 * 
 * @author dylants
 * 
 */
public abstract class BaseUser extends BaseEntity implements UserDetails, Serializable {
    private static final long serialVersionUID = 2970952793117692589L;

    private String username = null;
    private String passwordHash = null;
    /*
     * Spring security elements
     */
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    /**
     * Default constructor (implies ANONYMOUS {@link GrantedAuthority})
     * 
     */
    public BaseUser() {
        this(Sets.newHashSet(Authorities.ROLE_ANONYMOUS.getGrantedAuthority()));
    }

    /**
     * Constructs an {@link AbstractBaseUser} supplying the {@link GrantedAuthority}s
     * 
     * @param authorities
     *            The {@link GrantedAuthority} of the {@link AbstractBaseUser}
     */
    public BaseUser(Set<GrantedAuthority> authorities) {
        // Spring Security details
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

    /**
     * This is Spring Security's {@link UserDetails} method, which should return the password we compare against and
     * never return null. Because of this, we'll return the password hash here instead of the actual password. The
     * {@link PasswordEncoder} will be used to compare, and will hash the user's entered password when verifying they
     * match.
     */
    @Override
    public String getPassword() {
        return getPasswordHash();
    }

    @Override
    public String getUsername() {
        return this.username;
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

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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