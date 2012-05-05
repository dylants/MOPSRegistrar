package com.mops.registrar.web.page.user.child;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.Child;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.security.authentication.RegistrarAuthenticationProcessor;
import com.mops.registrar.services.child.ChildService;

/**
 * Responsible for editing {@link Child} entities
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/user/profile/child/edit/{entityId}")
public class EditChildController {
    private static final String CHILD_MODEL_NAME = "child";

    @Autowired
    private ChildService childService;

    @Autowired
    private RegistrarAuthenticationProcessor registrarAuthenticationProcessor;

    /**
     * Displays the edit {@link Child} page
     * 
     * @param principal
     *            The current {@link Principal}
     * @param entityId
     *            The entity ID of the {@link Child}
     * @param model
     *            The {@link Model} used to pass information to the view
     * @return The JSP to display
     */
    @RequestMapping(method = RequestMethod.GET)
    public String editChild(Principal principal, @PathVariable("entityId") String entityId, Model model) {
        // get the current user
        MopsUser mopsUser = this.registrarAuthenticationProcessor.deriveMopsUserFromPrincipal(principal);

        // find the Child for this entity ID
        Child child = this.childService.findChildByEntityId(entityId);

        // build the edit child page
        return buildEditChildPage(mopsUser, child, model);
    }

    /**
     * Processes the edit {@link Child} request
     * 
     * @param principal
     *            The current {@link Principal}
     * @param child
     *            The {@link Child} to edit
     * @param bindingResult
     *            The result of the binding
     * @param model
     *            The {@link Model} used to send information to the view
     * @return The next page to display
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processEditChild(Principal principal, @Valid @ModelAttribute(CHILD_MODEL_NAME) Child child,
            BindingResult bindingResult, Model model) {
        // get the current user
        MopsUser mopsUser = this.registrarAuthenticationProcessor.deriveMopsUserFromPrincipal(principal);

        // cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show the page again (so they can fix the errors)
            return buildEditChildPage(mopsUser, child, model);
        }

        // TODO child service to edit the child

        // go back to the child home page
        return "redirect:/page/user/profile/child";
    }

    /**
     * Builds up the edit {@link Child} page view
     * 
     * @param mopsUser
     *            The current {@link MopsUser}
     * @param child
     *            The {@link Child} to edit
     * @param model
     *            The {@link Model} used to send information to the view
     * @return The JSP used to display the edit {@link Child} page
     */
    protected String buildEditChildPage(MopsUser mopsUser, Child child, Model model) {
        // this is an existing child
        model.addAttribute("isNew", false);

        // add the user and child to the model
        model.addAttribute("user", mopsUser);
        model.addAttribute(CHILD_MODEL_NAME, child);

        // return the page
        return "child/childForm";
    }

    /**
     * @return the childService
     */
    public ChildService getChildService() {
        return childService;
    }

    /**
     * @param childService
     *            the childService to set
     */
    public void setChildService(ChildService childService) {
        this.childService = childService;
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