package com.mops.registrar.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.services.user.UserService;

/**
 * A REST web service controller used to access the {@link MopsUser}s
 * 
 * @author dylants
 */
@RequestMapping(value = "/service")
@Controller
public class UserWebServiceController {
    @Autowired
    private UserService userService = null;

    /**
     * Returns all {@link MopsUser}s available
     * 
     * @return all {@link MopsUser}s available
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<MopsUser> getUsers() {
        return this.userService.getUsers();
    }

    /**
     * Returns the {@link MopsUser} found by the unique <code>entityId</code>
     * 
     * @param entityId
     *            The {@link MopsUser}'s unique entity ID
     * @return The {@link MopsUser} found, else {@literal null}
     */
    @RequestMapping(value = "/user/{entityId}", method = RequestMethod.GET)
    @ResponseBody
    public MopsUser getUser(@PathVariable("entityId") String entityId) {
        return this.userService.getUserByEntityId(entityId);
    }

    /**
     * Returns the (first) {@link MopsUser} found by <code>firstName</code> and <code>lastName</code>
     * 
     * @param firstName
     *            The {@link MopsUser}s first name
     * @param lastName
     *            The {@link MopsUser}s last name
     * @return The (first) {@link MopsUser} found, else {@literal null}
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public MopsUser getUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return this.userService.getUserByFirstNameLastName(firstName, lastName);
    }

    /**
     * Adds a {@link MopsUser} to our {@link UserService}
     * 
     * @param mopsUser
     *            The {@link MopsUser} to add
     * @return The constructed {@link MopsUser}
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    public MopsUser addUser(@RequestBody MopsUser mopsUser) {
        this.userService.addUser(mopsUser);
        return mopsUser;
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