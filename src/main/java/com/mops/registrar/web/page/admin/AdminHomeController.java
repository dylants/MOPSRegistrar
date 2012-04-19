package com.mops.registrar.web.page.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Web controller that handles requests to the Admin home page
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/admin/home")
public class AdminHomeController {

    /**
     * Displays the Admin home page
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the Admin home view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        return "admin/home";
    }
}
