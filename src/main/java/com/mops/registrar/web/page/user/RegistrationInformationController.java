package com.mops.registrar.web.page.user;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.security.authentication.RegistrarAuthenticationProcessor;
import com.mops.registrar.services.user.UserService;

/**
 * Handles processing of the Registration Information for a MOPS user.
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "user/profile/registrationInformation")
public class RegistrationInformationController {
    private static final String REGISTRATION_INFORMATION_MODEL_NAME = "registrationInformation";

    @Autowired
    private UserService userService;
    @Autowired
    private RegistrarAuthenticationProcessor registrarAuthenticationProcessor;

    /**
     * Returns the registration information view
     * 
     * @param principal
     *            The current {@link Principal}
     * @param model
     *            The {@link Model} used to pass information to the view
     * @return The registration information view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String registrationInformation(Principal principal, Model model) {
        // get the MopsUser from the principal
        MopsUser mopsUser = this.registrarAuthenticationProcessor.deriveMopsUserFromPrincipal(principal);

        // show them the registration information page
        return buildRegistrationInformationPage(model, mopsUser.getRegistrationInformation());
    }

    /**
     * Processes the add/edit registration information request, edit the user, and display a view based on the result.
     * 
     * @param registrationInformation
     *            The new or edited {@link RegistrationInformation}
     * @param bindingResult
     *            The result of the binding of the registration information input to the {@link RegistrationInformation}
     *            object
     * @param model
     *            Contains information used by the view
     * @param principal
     *            The current {@link Principal}
     * @return The JSP used to display the next page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processRegistrationInformation(
            @Valid @ModelAttribute(REGISTRATION_INFORMATION_MODEL_NAME) RegistrationInformation registrationInformation,
            BindingResult bindingResult, Model model, Principal principal) {
        // cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show the page again (so they can fix the errors)
            return buildRegistrationInformationPage(model, registrationInformation);
        }

        // get the MopsUser from the principal
        MopsUser mopsUser = this.registrarAuthenticationProcessor.deriveMopsUserFromPrincipal(principal);

        // find this user's entity ID
        String entityId = mopsUser.getEntityId();
        // update the user in our registry, which will return us the updated user
        mopsUser = this.userService.updateRegistrationInformation(entityId, registrationInformation);
        // add it to the model for the jsp
        model.addAttribute("user", mopsUser);
        // show edit success page
        return "user/profile";
    }

    /**
     * Populates the {@link Model} and returns the view for the registration information page
     * 
     * @param model
     *            The {@link Model} we must populate
     * @param registrationInformation
     *            The current {@link RegistrationInformation}
     * @return The JSP used to display the registration information page
     */
    protected String buildRegistrationInformationPage(Model model, RegistrationInformation registrationInformation) {
        // determine if this is a new user or existing user by information they've already provided
        boolean isNew = false;
        if (registrationInformation.getFirstName() == null) {
            isNew = true;
        }
        model.addAttribute("isNew", isNew);
        model.addAttribute(REGISTRATION_INFORMATION_MODEL_NAME, registrationInformation);
        return "user/profile/registrationInformation";
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