package com.mops.registrar.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String home(Model model) {
        return "home";
    }
}