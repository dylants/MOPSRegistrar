package com.mops.registrar.entities;

import java.util.UUID;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

/**
 * Represents an Administrator, and currently extends {@link User}. The difference being the privileges that the
 * {@link AdminUser} has over the normal registered {@link User}
 * 
 * @author dylants
 * 
 */
public class AdminUser extends User {

    private static final long serialVersionUID = 4376388347985824942L;

    /**
     * Default constructor
     */
    public AdminUser() {
        // we need the id for password hashing, so always generate one on new AdminUser's
        this.entityId = UUID.randomUUID().toString();
        // provide some defaults for spring security elements
        this.authorities = Sets.newHashSet(Authorities.ROLE_ADMIN_USER.getGrantedAuthority(),
                Authorities.ROLE_REGISTERED_USER.getGrantedAuthority(),
                Authorities.ROLE_ANONYMOUS.getGrantedAuthority());
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
