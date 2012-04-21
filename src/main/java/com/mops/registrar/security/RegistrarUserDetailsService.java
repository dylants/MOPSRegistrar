package com.mops.registrar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mops.registrar.services.admin.AdminUserService;
import com.mops.registrar.services.user.UserService;

/**
 * A {@link UserDetailsService} for our Registrar Application
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

        // in the case of User's, the username is the email address
        userDetails = this.userService.getUserByEmailAddress(username);
        if (userDetails != null) {
            // we found it, return this one
            return userDetails;
        }

        // look in the AdminUser repository
        userDetails = this.adminUserService.getAdminUserByUsername(username);

        // return what we've got, null is okay
        return userDetails;
    }

}