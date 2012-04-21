package com.mops.registrar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // in our case, the username is the email address
        return this.userService.getUserByEmailAddress(username);
    }

}
