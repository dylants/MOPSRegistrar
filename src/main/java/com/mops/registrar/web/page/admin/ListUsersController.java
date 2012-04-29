package com.mops.registrar.web.page.admin;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.MOPSUser;
import com.mops.registrar.services.user.UserService;

/**
 * Web controller used to display the {@link MOPSUser}s available.
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/admin/user/list")
public class ListUsersController {
    @Autowired
    private UserService userService = null;

    /**
     * Lists the {@link MOPSUser}s in the system
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to list the {@link MOPSUser}s
     */
    @RequestMapping(method = RequestMethod.GET)
    public String listUsers(Model model) {
        List<MOPSUser> mopsUsers = this.userService.getUsers();

        // by default, sort the list by last name
        Collections.sort(mopsUsers);

        model.addAttribute("users", mopsUsers);
        return "admin/user/list";
    }

}