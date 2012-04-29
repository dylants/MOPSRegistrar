package com.mops.registrar.web.page;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.MOPSUser;

/**
 * MOPS Registrar Home Page Controller
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/home")
public class RegistrarHomeController {

    /**
     * Displays the MOPS registrar home page
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the home view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(Principal principal, Model model) {
        /*
         * If the user is logged in, we display some information. To check if this is the case, key off the Principal.
         * In the case the user is logged in, the Principal will be of type AuthenticationO
         */
        if (principal instanceof Authentication) {
            // they are logged in!
            // now get the principal from the... principal
            Authentication authentication = (Authentication) principal;
            Object authenticationPrincipal = authentication.getPrincipal();
            // see what type of logged in user they are
            if (authenticationPrincipal instanceof MOPSUser) {
                MOPSUser mopsUser = (MOPSUser) authenticationPrincipal;
                // add the first name
                model.addAttribute("firstName", mopsUser.getRegistrationInformation().getFirstName());
            } else if (authenticationPrincipal instanceof AdminUser) {
                AdminUser adminUser = (AdminUser) authenticationPrincipal;
                // add the first name
                model.addAttribute("firstName", adminUser.getUsername());
            }
        }

        return "home";
    }
}