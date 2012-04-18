package com.mops.registrar.web.page;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.elements.user.User;
import com.mops.registrar.services.user.UserService;
import com.mops.registrar.web.validator.UserValidator;

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
    @Autowired
    private UserValidator userValidator = null;

    /**
     * Configures the {@link UserValidator} to be used when validating {@link User} type objects.
     * 
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.userValidator);
    }

    /**
     * Displays the {@link User} home page
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the home view
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        return "user/home";
    }

    /**
     * Displays a view used to register a {@link User}
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to register the user
     */
    @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
    public String registerUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/registerUser";
    }

    /**
     * Processes the register user request, registering the user and displaying a view based on the result.
     * 
     * @param user
     *            The {@link User} to register
     * @param bindingResult
     *            The result of the binding of the user input to the {@link User} object
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the next page
     */
    @RequestMapping(value = "/processRegisterUser", method = RequestMethod.PUT)
    public String processRegisterUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        // first cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // return them back to the registration page
            return "user/registerUser";
        }

        // else add the user to our registry
        // TODO what happens if this fails?
        this.userService.addUser(user);
        model.addAttribute("user", user);
        return "user/registrationSuccess";
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