package com.mops.registrar.web.page.admin;

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

import com.mops.registrar.entities.User;
import com.mops.registrar.services.user.UserService;
import com.mops.registrar.web.validator.admin.UserValidatorForEditRegistrationInformation;
import com.mops.registrar.web.validator.user.UserValidator;

/**
 * Web controller responsible for editing an existing {@link User}
 * 
 * @author dysmith
 * 
 */
@Controller
@RequestMapping(value = "/admin/user/edit/{entityId}")
public class EditUserController {
    @Autowired
    private UserService userService = null;
    @Autowired
    private UserValidatorForEditRegistrationInformation userValidatorForEditRegistrationInformation = null;

    /**
     * Configures the {@link UserValidator} to be used when validating {@link User} type objects.
     * 
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.userValidatorForEditRegistrationInformation);
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
        populateModel(model, user);
        return "user/userForm";
    }

    /**
     * Processes the edit user request, edit the user and displaying a view based on the result.
     * 
     * @param entityId
     *            The ID of the {@link User} we should edit
     * @param user
     *            The edited {@link User}
     * @param bindingResult
     *            The result of the binding of the user input to the {@link User} object
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the next page
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String processEditUser(@PathVariable("entityId") String entityId, @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult, Model model) {
        // first cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show the user form again (so they can fix the errors)
            populateModel(model, user);
            return "user/userForm";
        }

        // update the user in our registry, which will return us the updated user
        user = this.userService.updateUser(entityId, user);
        // add it to the model for the jsp
        model.addAttribute("user", user);
        // show edit success page
        return "admin/user/editSuccess";
    }

    /**
     * Populates the model with generic information required for this view
     * 
     * @param model
     *            The {@link Model} to populate
     * @param user
     *            The {@link User}
     */
    protected void populateModel(Model model, User user) {
        model.addAttribute("user", user);
        // it's an existing User we're dealing with, not a new one
        model.addAttribute("isNew", false);
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

    /**
     * @return the userValidatorForEditRegistrationInformation
     */
    public UserValidatorForEditRegistrationInformation getUserValidatorForEditRegistrationInformation() {
        return userValidatorForEditRegistrationInformation;
    }

    /**
     * @param userValidatorForEditRegistrationInformation
     *            the userValidatorForEditRegistrationInformation to set
     */
    public void setUserValidatorForEditRegistrationInformation(
            UserValidatorForEditRegistrationInformation userValidatorForEditRegistrationInformation) {
        this.userValidatorForEditRegistrationInformation = userValidatorForEditRegistrationInformation;
    }

}