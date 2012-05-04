package com.mops.registrar.web.page.user.child;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.Child;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.security.authentication.RegistrarAuthenticationProcessor;
import com.mops.registrar.services.child.ChildService;
import com.mops.registrar.services.user.UserService;

/**
 * Responsible for adding {@link Child} entities to a {@link MopsUser}
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/user/profile/child/add")
public class AddChildController {
    private static final String CHILD_MODEL_NAME = "child";

    @Autowired
    private RegistrarAuthenticationProcessor registrarAuthenticationProcessor;

    @Autowired
    private ChildService childService;

    @Autowired
    private UserService userService;

    /**
     * Displays the Add {@link Child} page
     * 
     * @param principal
     *            The current {@link Principal}
     * @param model
     *            The {@link Model} used to send information to the view
     * @return The JSP to display
     */
    @RequestMapping(method = RequestMethod.GET)
    public String addChild(Principal principal, Model model) {
        // get the current user
        MopsUser mopsUser = this.registrarAuthenticationProcessor.deriveMopsUserFromPrincipal(principal);

        // create a new Child based off that current user
        Child child = new Child(mopsUser.getEntityId());

        // build the add child page
        return buildAddChildPage(mopsUser, child, model);
    }

    /**
     * Processes the Add {@link Child} request. If successful, this will add the {@link Child} to our repository while
     * also updating the current {@link MopsUser} to include this child.
     * 
     * @param principal
     *            The current {@link Principal}
     * @param child
     *            The {@link Child} to add
     * @param bindingResult
     *            {@link BindingResult} of the user input
     * @param model
     *            The {@link Model} used to send information to the view
     * @return The next page to display
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processAddChild(Principal principal, @Valid @ModelAttribute(CHILD_MODEL_NAME) Child child,
            BindingResult bindingResult, Model model) {
        // get the current user
        MopsUser mopsUser = this.registrarAuthenticationProcessor.deriveMopsUserFromPrincipal(principal);

        // cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show the page again (so they can fix the errors)
            return buildAddChildPage(mopsUser, child, model);
        }

        // make sure the entity ID is setup correctly (the child points back to it's parent)
        child.setMopsUserEntityId(mopsUser.getEntityId());
        // add the child
        child = this.childService.addChild(child);

        // update the user to include this child
        this.userService.addChildEntityId(mopsUser.getEntityId(), child.getEntityId());

        // go back to the child home page
        return "redirect:/page/user/profile/child";
    }

    /**
     * Builds up the Add {@link Child} page view
     * 
     * @param mopsUser
     *            The current {@link MopsUser}
     * @param child
     *            The {@link Child} template
     * @param model
     *            The {@link Model} used to send information to the view
     * @return The JSP used to display the add {@link Child} page
     */
    protected String buildAddChildPage(MopsUser mopsUser, Child child, Model model) {
        // this is a new child
        model.addAttribute("isNew", true);

        // add the user and child to the model
        model.addAttribute("user", mopsUser);
        model.addAttribute(CHILD_MODEL_NAME, child);

        // return the page
        return "child/childForm";
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