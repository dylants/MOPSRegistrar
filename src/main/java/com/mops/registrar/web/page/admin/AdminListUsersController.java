package com.mops.registrar.web.page.admin;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.services.user.UserService;

/**
 * Web controller used to display the {@link MopsUser}s available.
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/admin/user/list")
public class AdminListUsersController {
    @Autowired
    private UserService userService = null;

    /**
     * Lists the {@link MopsUser}s in the system
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to list the {@link MopsUser}s
     */
    @RequestMapping(method = RequestMethod.GET)
    public String listUsers(Model model) {
        List<MopsUser> mopsUsers = this.userService.getUsers();

        // by default, sort the list by last name
        Collections.sort(mopsUsers);

        model.addAttribute("mopsUsers", mopsUsers);
        return "admin/user/list";
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService
     *            the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}