package com.mops.registrar.web.page.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.mops.registrar.entities.MOPSUser;
import com.mops.registrar.security.authentication.RegistrarAuthenticationProcessor;
import com.mops.registrar.services.user.UserService;
import com.mops.registrar.web.validator.user.UserValidator;

/**
 * Web controller that handles registering new {@link MOPSUser}s
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/user/register")
public class RegisterUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private RegistrarAuthenticationProcessor registrarAuthenticationProcessor;

    /**
     * Configures the {@link UserValidator} to be used when validating {@link MOPSUser} type objects.
     * 
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.userValidator);
    }

    /**
     * Displays a view used to register a {@link MOPSUser}
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to register the user
     */
    @RequestMapping(method = RequestMethod.GET)
    public String registerUser(Model model) {
        MOPSUser mopsUser = new MOPSUser();
        populateModel(model, mopsUser);
        return "user/userForm";
    }

    /**
     * Processes the register user request, registering the user and displaying a view based on the result.
     * 
     * @param mopsUser
     *            The {@link MOPSUser} to register
     * @param bindingResult
     *            The result of the binding of the user input to the {@link MOPSUser} object
     * @param model
     *            Contains information used by the view
     * @param request
     *            The {@link HttpServletRequest}
     * @param response
     *            The {@link HttpServletResponse}
     * @return The JSP used to display the next page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processRegisterUser(@Valid @ModelAttribute("user") MOPSUser mopsUser, BindingResult bindingResult,
            Model model, HttpServletRequest request, HttpServletResponse response) {
        // first cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show them the registration page again (to fix the errors)
            populateModel(model, mopsUser);
            return "user/userForm";
        }

        // if things are good, add the user to our registry
        this.userService.addUser(mopsUser);

        // use this MOPSUser object to log them in
        this.registrarAuthenticationProcessor.loginNewlyRegisteredMOPSUser(request, response, mopsUser);

        // show registration success page
        model.addAttribute("user", mopsUser);
        return "user/registrationSuccess";
    }

    /**
     * Populates the model with generic information required for this view
     * 
     * @param model
     *            The {@link Model} to populate
     * @param mopsUser
     *            The {@link MOPSUser}
     */
    protected void populateModel(Model model, MOPSUser mopsUser) {
        model.addAttribute("user", mopsUser);
        // it's a new MOPSUser we're dealing with, not an existing
        model.addAttribute("isNew", true);
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
    public UserValidator getUserValidator() {
        return userValidator;
    }

    /**
     * @param userValidator
     *            the userValidator to set
     */
    public void setUserValidator(UserValidator userValidator) {
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