package com.mops.registrar.web.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.elements.user.User;
import com.mops.registrar.services.user.UserService;

/**
 * A web controller used to interact with the {@link User}s
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class UserWebController {
    @Autowired
    private UserService userService = null;

    /**
     * Displays the {@link User} home page
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the home view
     */
    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(Model model) {
        return "user/home";
    }

    /**
     * Displays a view used to add a {@link User}
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to add the user
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/addUser";
    }

    /**
     * Processes the add user request, adding the user and displaying a view based on the result of the add.
     * 
     * @param user
     *            The {@link User} to add
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the next page
     */
    @RequestMapping(value = "/processAddUser", method = RequestMethod.PUT)
    public String processAddUser(@ModelAttribute("user") User user, Model model) {
        // TODO what happens if this fails?
        this.userService.addUser(user);
        model.addAttribute("user", user);
        return "user/userAddSuccess";
    }

    /**
     * Lists the {@link User}s in the system
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to list the {@link User}s
     */
    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> users = this.userService.getUsers();
        model.addAttribute("users", users);
        return "user/listUsers";
    }
}