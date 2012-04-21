package com.mops.registrar.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * The available {@link GrantedAuthority}s in this application
 * 
 * @author dylants
 * 
 */
public enum Authorities {

    // Anonymous (not logged in)
    ROLE_ANONYMOUS(new SimpleGrantedAuthority("ROLE_ANONYMOUS")),
    // Registered User (any logged in registered user)
    ROLE_REGISTERED_USER(new SimpleGrantedAuthority("ROLE_REGISTERED_USER")),
    // Admin user
    ROLE_ADMIN_USER(new SimpleGrantedAuthority("ROLE_ADMIN_USER"));

    private final GrantedAuthority grantedAuthority;

    private Authorities(GrantedAuthority grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

    /**
     * @return the grantedAuthority
     */
    public GrantedAuthority getGrantedAuthority() {
        return grantedAuthority;
    }
}