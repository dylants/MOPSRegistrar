package com.mops.registrar.security.authentication;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Service;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.services.admin.AdminUserService;
import com.mops.registrar.services.user.UserService;

/**
 * Default implementation of the {@link RegistrarAuthenticationProcessor}
 * 
 * @author dylants
 * 
 */
@Service
public class DefaultRegistrarAuthenticationProcessor implements RegistrarAuthenticationProcessor {

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
    @Autowired
    private SessionAuthenticationStrategy sessionStrategy;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminUserService adminUserService;

    @Override
    public void loginNewlyRegisteredMOPSUser(HttpServletRequest request, HttpServletResponse response, MopsUser mopsUser) {
        /*
         * This uses generally the same logic as an AbstractAuthenticationProcessingFilter, however we know the
         * information provided is correct (since they've just registered this user) and can be used as an
         * Authentication token. With that in mind, we just build the token, perform any needed additional logic when
         * moving to a logged in state, and we're done.
         */

        // first build the Authentication token
        Authentication authenticationToken = buildAuthenticationToken(request, response, mopsUser);

        // now perform any additional session logic based on an authentication
        sessionStrategy.onAuthentication(authenticationToken, request, response);

        // store the authentication token in the SecurityContextHolder (which gives this session that authentication)
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    /**
     * Builds an {@link Authentication} token from the <code>mopsUser</code> object
     * 
     * @param request
     *            The {@link HttpServletRequest}
     * @param response
     *            The {@link HttpServletResponse}
     * @param mopsUser
     *            The newly created {@link MopsUser} used to build the {@link Authentication} token
     * @return The authenticated {@link Authentication}
     */
    protected Authentication buildAuthenticationToken(HttpServletRequest request, HttpServletResponse response,
            MopsUser mopsUser) {
        /*
         * Since the user has just registered this MopsUser object, we can authenticate it based on the information they
         * provided. So, use the user's information to build the authenticated Authentication token, using the
         * UsernamePasswordAuthenticationToken as our model.
         * 
         * Note that even though the clear text password retrieved from the MopsUser at this point is most likely null,
         * we should not need to store it in the Authentication token. Since authentication has already taken place, we
         * would normally clear the credentials so as to not keep sensitive information around in the session. So we
         * just store null as the credentials in this Authentication token.
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(mopsUser,
                null, mopsUser.getAuthorities());

        // add the additional details of the authentication token
        authenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));

        // return what we've built
        return authenticationToken;
    }

    @Override
    public MopsUser deriveMopsUserFromPrincipal(Principal principal) throws AccessDeniedException {
        // first find out if it's of type Authentication
        if (principal instanceof Authentication) {
            // now get the principal from the... principal
            Authentication authentication = (Authentication) principal;
            Object authenticationPrincipal = authentication.getPrincipal();
            // see if it's of type MopsUser
            if (authenticationPrincipal instanceof MopsUser) {
                /*
                 * If it is, it's best to pull the MopsUser from the database, rather than returning this one. Spring
                 * Security can leave a stale MOPS user laying around, and just to make sure the users of this method
                 * get the latest and greatest, pull it from the service using the entityId stored in this MOPS user.
                 */
                MopsUser mopsUser = (MopsUser) authenticationPrincipal;
                String entityId = mopsUser.getEntityId();
                mopsUser = this.userService.getUserByEntityId(entityId);
                return mopsUser;
            }
        }

        // else, doesn't exist, throw AccessDeniedException so we fail out in those calls
        throw new AccessDeniedException("Unable to locate MopsUser in Principal");
    }

    @Override
    public AdminUser deriveAdminUserFromPrincipal(Principal principal) throws AccessDeniedException {
        // first find out if it's of type Authentication
        if (principal instanceof Authentication) {
            // now get the principal from the... principal
            Authentication authentication = (Authentication) principal;
            Object authenticationPrincipal = authentication.getPrincipal();
            // see if it's of type MopsUser
            if (authenticationPrincipal instanceof AdminUser) {
                /*
                 * If it is, it's best to pull the AdminUser from the database, rather than returning this one. Spring
                 * Security can leave a stale Admin user laying around, and just to make sure the users of this method
                 * get the latest and greatest, pull it from the service using the entityId stored in this Admin user.
                 */
                AdminUser adminUser = (AdminUser) authenticationPrincipal;
                String entityId = adminUser.getEntityId();
                adminUser = this.adminUserService.getAdminUserByEntityId(entityId);
                return adminUser;
            }
        }

        // else, doesn't exist, throw AccessDeniedException so we fail out in those calls
        throw new AccessDeniedException("Unable to locate AdminUser in Principal");
    }

    /**
     * @return the authenticationDetailsSource
     */
    public AuthenticationDetailsSource<HttpServletRequest, ?> getAuthenticationDetailsSource() {
        return authenticationDetailsSource;
    }

    /**
     * @param authenticationDetailsSource
     *            the authenticationDetailsSource to set
     */
    public void setAuthenticationDetailsSource(
            AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    /**
     * @return the sessionStrategy
     */
    public SessionAuthenticationStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    /**
     * @param sessionStrategy
     *            the sessionStrategy to set
     */
    public void setSessionStrategy(SessionAuthenticationStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
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