package com.mops.registrar.entities;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

/**
 * Represents an Administrative user within the system, who can access restricted resources. This user exists outside of
 * the normal registration process, and as such, does not contain the extra information a normal registered {@link User}
 * would contain.
 * 
 * @author dylants
 * 
 */
public class AdminUser extends BaseUser implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 4376388347985824942L;

    /**
     * Default constructor
     */
    public AdminUser() {
        // An AdminUser has ROLE_ADMIN_USER, ROLE_REGISTERED_USER, and ROLE_ANONYMOUS access
        super(Sets.newHashSet(Authorities.ROLE_ADMIN_USER.getGrantedAuthority(),
                Authorities.ROLE_REGISTERED_USER.getGrantedAuthority(),
                Authorities.ROLE_ANONYMOUS.getGrantedAuthority()));
    }

    @Override
    public String toString() {
        return "AdminUser username: " + this.username;
    }
}