package com.mops.registrar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.services.admin.AdminUserService;
import com.mops.registrar.services.user.UserService;

/**
 * A {@link UserDetailsService} for our Registrar Application. Currently this is used to load up a {@link UserDetails}
 * object from both the {@link MopsUser} repository as well as the {@link AdminUser} repository.
 * 
 * @author dylants
 * 
 */
public class RegistrarUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
         * We've got two types of users: a normal registered user and an admin user. First look in the UserService, and
         * if we don't find anything there, look in the AdminUserService
         */
        UserDetails userDetails = null;

        // look in the MopsUser repository
        userDetails = this.userService.getUserByUsername(username);
        if (userDetails != null) {
            // we found it, return this one
            return userDetails;
        }

        // look in the AdminUser repository
        userDetails = this.adminUserService.getAdminUserByUsername(username);
        if (userDetails != null) {
            // we found it, return this one
            return userDetails;
        }

        // if we're here, we've not found a UserDetails...
        throw new UsernameNotFoundException("Username: \"" + username + "\" not found");
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService
     *            the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return the adminUserService
     */
    public AdminUserService getAdminUserService() {
        return adminUserService;
    }

    /**
     * @param adminUserService
     *            the adminUserService to set
     */
    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }
}