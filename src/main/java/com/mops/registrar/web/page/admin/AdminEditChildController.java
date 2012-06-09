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

import com.mops.registrar.entities.Child;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.services.child.ChildService;
import com.mops.registrar.services.user.UserService;

/**
 * Controller responsible for allowing an Admin user the ability to edit {@link Child} entities
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/admin/child/edit/{entityId}")
public class AdminEditChildController {
    private static final String CHILD_MODEL_NAME = "child";

    @Autowired
    private UserService userService;
    @Autowired
    private ChildService childService;

    /**
     * Shows the edit child view
     * 
     * @param entityId
     *            The entity ID of the {@link Child}
     * @param model
     *            The model used to pass information to the view
     * @return The JSP for the edit {@link Child} page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String editChild(@PathVariable("entityId") String entityId, Model model) {
        // First find the Child and associated MOPS user
        Child child = this.childService.findChildByEntityId(entityId);
        String mopsUserId = child.getMopsUserEntityId();
        MopsUser mopsUser = this.userService.getUserByEntityId(mopsUserId);

        return buildEditChildPage(child, mopsUser, model);
    }

    /**
     * Processes the request to edit the {@link Child} entity
     * 
     * @param entityId
     *            The entity ID of the child to edit
     * @param child
     *            The updated {@link Child}
     * @param bindingResult
     *            The result of the binding
     * @param model
     *            The model used to pass information to the view
     * @return The JSP to display the edit child page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processEditChild(@PathVariable("entityId") String entityId,
            @Valid @ModelAttribute(CHILD_MODEL_NAME) Child child, BindingResult bindingResult, Model model) {
        // First find the original Child and associated MOPS user
        Child originalChild = this.childService.findChildByEntityId(entityId);
        String mopsUserId = originalChild.getMopsUserEntityId();
        MopsUser mopsUser = this.userService.getUserByEntityId(mopsUserId);

        // cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show the page again (so they can fix the errors)
            return buildEditChildPage(child, mopsUser, model);
        }

        // update the Child
        this.childService.updateChild(entityId, child);

        // go back to the admin home page
        return "redirect:/page/admin/home";
    }

    /**
     * Builds up the edit {@link Child} page view
     * 
     * @param child
     *            The {@link Child} to edit
     * @param mopsUser
     *            The associated {@link MopsUser}
     * @param model
     *            The {@link Model} used to send information to the view
     * @return The JSP used to display the edit {@link Child} page
     */
    protected String buildEditChildPage(Child child, MopsUser mopsUser, Model model) {
        // this is an existing child
        model.addAttribute("isNew", false);

        // add the child and user to the model
        model.addAttribute(CHILD_MODEL_NAME, child);
        model.addAttribute("user", mopsUser);

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
}