package com.mops.registrar.security.authentication;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.MopsUser;

/**
 * Provides authentication APIs for the Registrar application
 * 
 * @author dylants
 * 
 */
public interface RegistrarAuthenticationProcessor {

    /**
     * After a {@link MopsUser} has registered within the Registrar application, this method will use that
     * {@link MopsUser} to authenticate the current session with its credentials.
     * 
     * @param request
     *            The {@link HttpServletRequest}
     * @param response
     *            The {@link HttpServletResponse}
     * @param mopsUser
     *            The {@link MopsUser} to login
     */
    public void loginNewlyRegisteredMOPSUser(HttpServletRequest request, HttpServletResponse response, MopsUser mopsUser);

    /**
     * Locates and returns the {@link MopsUser} object stored within the supplied {@link Principal}
     * 
     * @param principal
     *            The {@link Principal} of the current user
     * @return The {@link MopsUser} object
     * @throws AccessDeniedException
     *             If the {@link MopsUser} is not found (since where we call this we're relying on the {@link MopsUser}
     *             to exist, else there was some sort of authorization failure. This allows us to fall back to the login
     *             page, if for some reason a user was able to get to a page which utilized this code.
     */
    public MopsUser deriveMopsUserFromPrincipal(Principal principal) throws AccessDeniedException;

    /**
     * Locates and returns the {@link AdminUser} object stored within the supplied {@link Principal}
     * 
     * @param principal
     *            The {@link Principal} of the current user
     * @return The {@link AdminUser} object
     * @throws AccessDeniedException
     *             If the {@link AdminUser} is not found (since where we call this we're relying on the {@link AdminUser}
     *             to exist, else there was some sort of authorization failure. This allows us to fall back to the login
     *             page, if for some reason a user was able to get to a page which utilized this code.
     */
    public AdminUser deriveAdminUserFromPrincipal(Principal principal) throws AccessDeniedException;
}