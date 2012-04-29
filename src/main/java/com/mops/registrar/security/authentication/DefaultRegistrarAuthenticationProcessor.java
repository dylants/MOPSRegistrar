package com.mops.registrar.security.authentication;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Service;

import com.mops.registrar.entities.MOPSUser;

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

    @Override
    public void loginNewlyRegisteredMOPSUser(HttpServletRequest request, HttpServletResponse response, MOPSUser mopsUser) {
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
     *            The newly created {@link MOPSUser} used to build the {@link Authentication} token
     * @return The authenticated {@link Authentication}
     */
    protected Authentication buildAuthenticationToken(HttpServletRequest request, HttpServletResponse response,
            MOPSUser mopsUser) {
        /*
         * Since the user has just registered this MOPSUser object, we can authenticate it based on the information they
         * provided. So, use the user's information to build the authenticated Authentication token, using the
         * UsernamePasswordAuthenticationToken as our model.
         * 
         * Note that even though the clear text password retreived from the MOPSUser at this point is most likely null,
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
    public MOPSUser deriveMOPSUserFromPrincipal(Principal principal) {
        // first find out if it's of type Authentication
        if (principal instanceof Authentication) {
            // now get the principal from the... principal
            Authentication authentication = (Authentication) principal;
            Object authenticationPrincipal = authentication.getPrincipal();
            // see if it's of type MOPSUser
            if (authenticationPrincipal instanceof MOPSUser) {
                MOPSUser mopsUser = (MOPSUser) authenticationPrincipal;
                return mopsUser;
            }
        }

        // else, doesn't exist, return null
        return null;
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

}