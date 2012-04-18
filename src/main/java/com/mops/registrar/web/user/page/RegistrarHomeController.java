package com.mops.registrar.web.user.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.user.User;

/**
 * A web controller used to interact with the {@link User}s
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/user/home")
public class RegistrarHomeController {

    /**
     * Displays the {@link User} home page
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the home view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        return "user/home";
    }
}