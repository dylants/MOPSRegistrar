package com.mops.registrar.web.user.page.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.user.User;

/**
 * Web controller that handles requests to the Admin home page
 * 
 * @author dylants
 *
 */
@Controller
@RequestMapping(value = "/user/admin/home")
public class AdminHomeController {

    /**
     * Displays the {@link User} Admin home page
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the Admin home view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        return "user/admin/home";
    }
}
