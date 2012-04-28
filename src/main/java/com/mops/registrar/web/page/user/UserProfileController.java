package com.mops.registrar.web.page.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.User;
import com.mops.registrar.security.authentication.RegistrarAuthenticationProcessor;

@Controller
@RequestMapping(value = "/user/profile")
public class UserProfileController {

    @Autowired
    private RegistrarAuthenticationProcessor registrarAuthenticationProcessor;

    /**
     * Displays the profile page for the user
     * 
     * @param model
     *            Contains information needed to display the view
     * @return The JSP used to display the profile page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String profile(Principal principal, Model model) {
        // attempt to get the User
        User user = this.registrarAuthenticationProcessor.deriveUserFromPrincipal(principal);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/profile";
        }

        // if we're here, something went wrong, send them back to home
        // TODO logging
        return "home";
    }

    /**
     * @return the registrarAuthenticationProcessor
     */
    public RegistrarAuthenticationProcessor getRegistrarAuthenticationProcessor() {
        return registrarAuthenticationProcessor;
    }

    /**
     * @param registrarAuthenticationProcessor
     *            the registrarAuthenticationProcessor to set
     */
    public void setRegistrarAuthenticationProcessor(RegistrarAuthenticationProcessor registrarAuthenticationProcessor) {
        this.registrarAuthenticationProcessor = registrarAuthenticationProcessor;
    }

}