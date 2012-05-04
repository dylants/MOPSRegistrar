package com.mops.registrar.web.page.user.child;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.Child;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.security.authentication.RegistrarAuthenticationProcessor;
import com.mops.registrar.services.child.ChildService;

/**
 * This controller is responsible for displaying the current list of {@link Child} entities for a given {@link MopsUser}
 * while also allowing the {@link MopsUser} to add a new {@link Child} or edit an existing {@link Child}.
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/user/profile/child")
public class ChildHomeController {

    @Autowired
    private RegistrarAuthenticationProcessor registrarAuthenticationProcessor;

    @Autowired
    private ChildService childService;

    /**
     * Displays the {@link Child} home page for a {@link MopsUser}
     * 
     * @param principal
     *            The current {@link Principal}
     * @param model
     *            The {@link Model} used to send data to the view
     * @return The JSP to display the {@link Child} home page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String childHome(Principal principal, Model model) {
        // get the current user
        MopsUser mopsUser = this.registrarAuthenticationProcessor.deriveMopsUserFromPrincipal(principal);

        // retrieve the list of children
        Set<String> childrenEntityIds = mopsUser.getChildrenEntityIds();

        // if there are any, retrieve them from the service
        if (childrenEntityIds.size() > 0) {
            Set<Child> children = this.childService.findChildren(childrenEntityIds);
            if ((children != null) && (children.size() > 0)) {
                // add the existing children to the model, but first sort it
                List<Child> childrenList = new ArrayList<Child>(children);
                Collections.sort(childrenList);
                model.addAttribute("children", childrenList);
            }
        }

        return "child/home";
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

}