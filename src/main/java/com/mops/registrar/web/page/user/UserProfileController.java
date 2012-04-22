package com.mops.registrar.web.page.user;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.User;

@Controller
@RequestMapping(value = "/user/profile")
public class UserProfileController {

    /**
     * Displays the profile page for the user
     * 
     * @param model
     *            Contains information needed to display the view
     * @return The JSP used to display the profile page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String profile(Principal principal, Model model) {
        // we must be logged in to view this page
        if (principal instanceof Authentication) {
            // they are logged in!
            // now get the principal from the... principal
            Authentication authentication = (Authentication) principal;
            Object authenticationPrincipal = authentication.getPrincipal();
            // see what type of logged in user they are
            if (authenticationPrincipal instanceof User) {
                User user = (User) authenticationPrincipal;
                model.addAttribute("user", user);
                return "user/profile";
            }
        }

        // if we're here, something went wrong, send them back to home
        // TODO logging
        return "home";
    }
}
