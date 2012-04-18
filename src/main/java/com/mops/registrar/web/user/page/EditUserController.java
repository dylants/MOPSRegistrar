package com.mops.registrar.web.user.page;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.user.User;
import com.mops.registrar.services.user.UserService;
import com.mops.registrar.web.user.validator.UserValidator;

/**
 * Web controller responsible for editing an existing {@link User}
 * 
 * @author dysmith
 * 
 */
@Controller
@RequestMapping(value = "/user/edit/{entityId}")
public class EditUserController {
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
     * Displays a view used to edit a {@link User}
     * 
     * @param entityId
     *            The ID of the {@link User} we should edit
     * @param model
     *            Contains information used by the view
     * @return The JSP used to edit the user
     */
    @RequestMapping(method = RequestMethod.GET)
    public String editUser(@PathVariable("entityId") String entityId, Model model) {
        User user = this.userService.getUserByEntityId(entityId);
        model.addAttribute("user", user);
        model.addAttribute("heading", "Edit Registration Information");
        model.addAttribute("submitButtonText", "Submit");
        return "user/userForm";
    }

    /**
     * Processes the edit user request, edit the user and displaying a view based on the result.
     * 
     * @param user
     *            The edited {@link User}
     * @param bindingResult
     *            The result of the binding of the user input to the {@link User} object
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the next page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processEditUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        // first cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // return them back to the edit page
            return "user/userForm";
        }

        // else update the user in our registry
        this.userService.addUser(user);
        model.addAttribute("user", user);
        // send them home
        return "user/home";
    }
}