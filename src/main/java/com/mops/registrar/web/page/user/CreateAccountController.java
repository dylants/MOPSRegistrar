package com.mops.registrar.web.page.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.security.authentication.RegistrarAuthenticationProcessor;
import com.mops.registrar.services.user.UserService;
import com.mops.registrar.web.validator.user.CreateAccountValidator;

/**
 * Web controller that handles creating new {@link MopsUser}s
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/user/createAccount")
public class CreateAccountController {
    private static final String CREATE_ACCOUNT_MODEL_NAME = "createAccountModel";

    @Autowired
    private CreateAccountValidator createAccountValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private RegistrarAuthenticationProcessor registrarAuthenticationProcessor;

    /**
     * Configures the {@link Validator} to be used when validating the {@link CreateAccountModel}
     * 
     * @param binder
     *            The {@link WebDataBinder}
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.createAccountValidator);
    }

    /**
     * Displays a view used to create a {@link MopsUser}
     * 
     * @param model
     *            Contains information used by the view
     * @return The JSP used to create the user
     */
    @RequestMapping(method = RequestMethod.GET)
    public String createAccount(Model model) {
        return buildCreateAccountPage(model, new CreateAccountModel());
    }

    /**
     * Processes the create account request, registering the user and displaying a view based on the result.
     * 
     * @param createAccountModel
     *            The {@link CreateAccountModel} containing the account information
     * @param bindingResult
     *            The result of the binding of the user input to the {@link CreateAccountModel} object
     * @param model
     *            Contains information used by the view
     * @param request
     *            The {@link HttpServletRequest}
     * @param response
     *            The {@link HttpServletResponse}
     * @return The JSP used to display the next page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processCreateAccount(
            @Valid @ModelAttribute(CREATE_ACCOUNT_MODEL_NAME) CreateAccountModel createAccountModel,
            BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse response) {
        // first cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show them the registration page again (to fix the errors)
            return buildCreateAccountPage(model, createAccountModel);
        }

        // if things are good, create the user and add it to our registry
        MopsUser mopsUser = new MopsUser();
        String username = createAccountModel.getEmailAddress();
        String passwordHash = this.userService.generatePasswordHash(createAccountModel.getPassword(), mopsUser);
        mopsUser.setUsername(username);
        mopsUser.setPasswordHash(passwordHash);
        // TODO handle if this fails
        mopsUser = this.userService.addUser(mopsUser);

        // use this MopsUser object to log them in
        this.registrarAuthenticationProcessor.loginNewlyRegisteredMOPSUser(request, response, mopsUser);

        // now redirect them to the registration information page
        return "redirect:/page/user/profile/registrationInformation";
    }

    /**
     * Populates the model with information required, and returns the view
     * 
     * @param model
     *            The {@link Model} to populate
     * @param createAccountModel
     *            The {@link CreateAccountModel}
     * @return The JSP used to display the create account view
     */
    protected String buildCreateAccountPage(Model model, CreateAccountModel createAccountModel) {
        model.addAttribute(CREATE_ACCOUNT_MODEL_NAME, createAccountModel);
        return "user/createAccount";
    }

    /**
     * @return the createAccountValidator
     */
    public CreateAccountValidator getCreateAccountValidator() {
        return createAccountValidator;
    }

    /**
     * @param createAccountValidator
     *            the createAccountValidator to set
     */
    public void setCreateAccountValidator(CreateAccountValidator createAccountValidator) {
        this.createAccountValidator = createAccountValidator;
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