package com.mops.registrar.web.page;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.security.authentication.RegistrarAuthenticationProcessor;

/**
 * MOPS Registrar Home Page Controller
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/home")
public class RegistrarHomeController {

    @Autowired
    private RegistrarAuthenticationProcessor registrarAuthenticationProcessor;

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
         * In the case the user is logged in, the Principal will be of type Authentication
         */
        // First look for a MopsUser
        MopsUser mopsUser = this.registrarAuthenticationProcessor.deriveMopsUserFromPrincipal(principal);
        if (mopsUser != null) {
            // add the first name
            // TODO allow for null first name
            model.addAttribute("firstName", mopsUser.getRegistrationInformation().getFirstName());
        } else {
            // Else see if we can find the AdminUser
            AdminUser adminUser = this.registrarAuthenticationProcessor.deriveAdminUserFromPrincipal(principal);
            if (adminUser != null) {
                // add the first name
                model.addAttribute("firstName", adminUser.getUsername());
            }
        }

        return "home";
    }
}