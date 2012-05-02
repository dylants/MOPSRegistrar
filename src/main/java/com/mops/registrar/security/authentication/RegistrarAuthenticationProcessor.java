package com.mops.registrar.security.authentication;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * Locates and returns the {@link MopsUser} object stored within the supplied {@link Principal}, else returns
     * {@literal null} if none is found
     * 
     * @param principal
     *            The {@link Principal} of the current user
     * @return The {@link MopsUser} object, else {@literal null} if none foundO
     */
    public MopsUser deriveMopsUserFromPrincipal(Principal principal);

    /**
     * Locates and returns the {@link AdminUser} object stored within the supplied {@link Principal}, else returns
     * {@literal null} if none is found
     * 
     * @param principal
     *            The {@link Principal} of the current user
     * @return The {@link AdminUser} object, else {@literal null} if none foundO
     */
    public AdminUser deriveAdminUserFromPrincipal(Principal principal);
}