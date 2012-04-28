package com.mops.registrar.web.page.user;

import java.security.Principal;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.User;
import com.mops.registrar.security.authentication.RegistrarAuthenticationProcessor;
import com.mops.registrar.services.user.UserService;
import com.mops.registrar.web.validator.admin.UserValidatorForEditRegistrationInformation;

/**
 * Builds and processes the Edit Registration Information page
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "user/profile/editRegistrationInformation")
public class EditRegistrationInformationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidatorForEditRegistrationInformation userValidator;
    @Autowired
    private RegistrarAuthenticationProcessor registrarAuthenticationProcessor;

    /**
     * Configures the {@link Validator} to be used when validating {@link User} type objects.
     * 
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.userValidator);
    }

    /**
     * Returns the edit registration information view
     * 
     * @param principal
     *            The current {@link Principal}
     * @param model
     *            The {@link Model} used to pass information to the view
     * @return The edit registration information view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String editRegistrationInformation(Principal principal, Model model) {
        // get the User from the principal
        User user = this.registrarAuthenticationProcessor.deriveUserFromPrincipal(principal);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/userForm";
        }

        // if we're here, something went wrong, send them back to home
        // TODO logging
        return "home";
    }

    /**
     * Processes the edit registration information request, edit the user and displaying a view based on the result.
     * 
     * @param user
     *            The edited {@link User}
     * @param bindingResult
     *            The result of the binding of the user input to the {@link User} object
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the next page
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String processEditUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        // first cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show the user form again (so they can fix the errors)
            model.addAttribute("user", user);
            return "user/userForm";
        }

        // find this user's entity ID
        String entityId = user.getEntityId();
        // update the user in our registry, which will return us the updated user
        user = this.userService.updateUser(entityId, user);
        // add it to the model for the jsp
        model.addAttribute("user", user);
        // show edit success page
        return "user/profile";
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
     * @return the userValidator
     */
    public UserValidatorForEditRegistrationInformation getUserValidator() {
        return userValidator;
    }

    /**
     * @param userValidator
     *            the userValidator to set
     */
    public void setUserValidator(UserValidatorForEditRegistrationInformation userValidator) {
        this.userValidator = userValidator;
    }

    /**
     * @return the registrarAuthenticationProcessor
     */
    public RegistrarAuthenticationProcessor getRegistrarAuthenticationProcessor() {
        return registrarAuthenticationProcessor;
    }

    /**
     * @param registrarAuthenticationProcessor
     *            the registrarAuthenticationProcessor to set
     */
    public void setRegistrarAuthenticationProcessor(RegistrarAuthenticationProcessor registrarAuthenticationProcessor) {
        this.registrarAuthenticationProcessor = registrarAuthenticationProcessor;
    }

}