package com.mops.registrar.web.page.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.services.user.UserService;

/**
 * Web controller responsible for editing an existing {@link MopsUser}
 * 
 * @author dysmith
 * 
 */
@Controller
@RequestMapping(value = "/admin/user/edit/{entityId}")
public class AdminEditUserController {
    @Autowired
    private UserService userService = null;

    /**
     * Displays a view used to edit the {@link RegistrationInformation} {@link MopsUser}
     * 
     * @param entityId
     *            The ID of the {@link MopsUser} we should edit
     * @param model
     *            Contains information used by the view
     * @return The JSP used to edit the user
     */
    @RequestMapping(value = "/registrationInformation", method = RequestMethod.GET)
    public String editRegistrationInformation(@PathVariable("entityId") String entityId, Model model) {
        MopsUser mopsUser = this.userService.getUserByEntityId(entityId);
        return buildRegistrationInformationPage(model, mopsUser.getRegistrationInformation());
    }

    /**
     * Processes the edit registration information, edits the user, and displays a view based on the result.
     * 
     * @param entityId
     *            The ID of the {@link MopsUser} we should edit
     * @param registrationInformation
     *            The new or edited {@link RegistrationInformation}
     * @param bindingResult
     *            The result of the binding of the registration information input to the {@link RegistrationInformation}
     *            object
     * @param model
     *            Contains information used by the view
     * @return The JSP used to display the next page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processEditRegistrationInformation(@PathVariable("entityId") String entityId,
            @Valid @ModelAttribute("registrationInformation") RegistrationInformation registrationInformation,
            BindingResult bindingResult, Model model) {
        // first cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show the page again (so they can fix the errors)
            return buildRegistrationInformationPage(model, registrationInformation);
        }

        // update the user in our registry, which will return us the updated user
        MopsUser mopsUser = this.userService.updateRegistrationInformation(entityId, registrationInformation);
        // add it to the model for the jsp
        model.addAttribute("user", mopsUser);
        // show edit success page
        return "admin/user/editSuccess";
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
        model.addAttribute("registrationInformation", registrationInformation);
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
}