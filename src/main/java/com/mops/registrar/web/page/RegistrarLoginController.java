package com.mops.registrar.web.page;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.User;

/**
 * Controller that handles logging in a {@link User} or {@link AdminUser}
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "login")
public class RegistrarLoginController {
    public static final String ERROR_PARAMETER_NAME = "error";

    /**
     * Displays the login page. This page will POST directly to Spring Security to perform the actual login function.
     * 
     * @param request
     *            The {@link HttpServletRequest}
     * @param model
     *            The {@link Model} used to pass information to the view
     * @return The view to display
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showLogin(HttpServletRequest request, Model model) {
        // check to see if we have any errors
        boolean loginError = request.getParameter(ERROR_PARAMETER_NAME) != null;
        if (loginError) {
            // for now, let's hard code the error message to help the user
            String errorMessage = "Error logging in, please try again";
            model.addAttribute("errorMessage", errorMessage);
        }

        // show the login view
        return "login";
    }
}