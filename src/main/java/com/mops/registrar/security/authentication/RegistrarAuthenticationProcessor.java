package com.mops.registrar.security.authentication;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mops.registrar.entities.MOPSUser;

/**
 * Provides authentication APIs for the Registrar application
 * 
 * @author dylants
 * 
 */
public interface RegistrarAuthenticationProcessor {

    /**
     * After a {@link MOPSUser} has registered within the Registrar application, this method will use that
     * {@link MOPSUser} to authenticate the current session with its credentials.
     * 
     * @param request
     *            The {@link HttpServletRequest}
     * @param response
     *            The {@link HttpServletResponse}
     * @param mopsUser
     *            The {@link MOPSUser} to login
     */
    public void loginNewlyRegisteredMOPSUser(HttpServletRequest request, HttpServletResponse response, MOPSUser mopsUser);

    /**
     * Locates and returns the {@link MOPSUser} object stored within the supplied {@link Principal}, else returns
     * {@literal null} if none is found
     * 
     * @param principal
     *            The {@link Principal} of the current user
     * @return The {@link MOPSUser} object, else {@literal null} if none foundO
     */
    public MOPSUser deriveMOPSUserFromPrincipal(Principal principal);
}
