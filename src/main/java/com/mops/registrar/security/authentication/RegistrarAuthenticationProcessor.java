package com.mops.registrar.security.authentication;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mops.registrar.entities.User;

/**
 * Provides authentication APIs for the Registrar application
 * 
 * @author dylants
 * 
 */
public interface RegistrarAuthenticationProcessor {

    /**
     * After a {@link User} has registered within the Registrar application, this method will use that {@link User} to
     * authenticate the current session with its credentials.
     * 
     * @param request
     * @param response
     * @param user
     */
    public void loginNewlyRegisteredUser(HttpServletRequest request, HttpServletResponse response, User user);

    /**
     * Locates and returns the {@link User} object stored within the supplied {@link Principal}, else returns
     * {@literal null} if none is found
     * 
     * @param principal
     *            The {@link Principal} of the current user
     * @return The {@link User} object, else {@literal null} if none foundO
     */
    public User deriveUserFromPrincipal(Principal principal);
}
