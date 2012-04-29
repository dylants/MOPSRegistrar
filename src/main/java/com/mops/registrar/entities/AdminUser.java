package com.mops.registrar.entities;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

/**
 * Represents an Administrative user within the system, who can access restricted resources. This user exists outside of
 * the normal registration process, and as such, does not contain the extra information a normal registered
 * {@link MOPSUser} would contain.
 * 
 * @author dylants
 * 
 */
public class AdminUser extends BaseUser {

    private static final long serialVersionUID = 415399535255120380L;

    /**
     * Default constructor
     */
    public AdminUser() {
        // An AdminUser has ROLE_ADMIN_USER access
        super(Sets.newHashSet(Authorities.ROLE_ADMIN_USER.getGrantedAuthority()));
    }

    @Override
    public String toString() {
        return "AdminUser username: " + this.getUsername();
    }

}