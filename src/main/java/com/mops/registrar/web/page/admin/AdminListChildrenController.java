package com.mops.registrar.web.page.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.Child;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.services.child.ChildService;
import com.mops.registrar.services.user.UserService;

/**
 * Web controller responsible for displaying {@link Child}ren
 * 
 * @author dylants
 * 
 */
@Controller
public class AdminListChildrenController {
    @Autowired
    UserService userService;
    @Autowired
    ChildService childService;

    @RequestMapping(value = "/admin/user/{userEntityId}/child/list", method = RequestMethod.GET)
    public String listChildrenOfUser(@PathVariable(value = "userEntityId") String userEntityId, Model model) {
        // first get the MOPS user
        MopsUser mopsUser = this.userService.getUserByEntityId(userEntityId);
        // from the MOPS user, retrieve the Child elements
        Set<String> childEntityIds = mopsUser.getChildrenEntityIds();
        if ((childEntityIds != null) && (childEntityIds.size() > 0)) {
            Set<Child> children = this.childService.findChildren(childEntityIds);
            if ((children != null) && (children.size() > 0)) {
                // add the existing children to the model, but first sort it
                List<Child> childrenList = new ArrayList<Child>(children);
                Collections.sort(childrenList);
                model.addAttribute("children", childrenList);
            }
        }
        return "admin/child/list";
    }

    @RequestMapping(value = "/admin/child/list", method = RequestMethod.GET)
    public String listAllChildren(Model model) {
        Set<Child> children = this.childService.findAllChildren();
        // add the existing children to the model, but first sort it
        List<Child> childrenList = new ArrayList<Child>(children);
        Collections.sort(childrenList);
        model.addAttribute("children", childrenList);

        return "admin/child/list";
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