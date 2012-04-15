package com.mops.registrar.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mops.registrar.elements.user.User;
import com.mops.registrar.services.user.UserService;

/**
 * A REST web service controller used to access the {@link User}s
 * 
 * @author dylants
 */
@RequestMapping(value = "service/user")
@Controller
public class UserWebServiceController {
    @Autowired
    private UserService userService = null;

    /**
     * Returns all users available
     * 
     * @return all users available
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return this.userService.getUsers();
    }

    /**
     * Returns the {@link User} found by <code>userName</code>
     * 
     * @param userName
     *            The {@link User}s user name
     * @return The {@link User} found, else {@literal null}
     */
    @RequestMapping(value = "/userName", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@RequestParam("userName") String userName) {
        return this.userService.getUser(userName);
    }

    /**
     * Returns the (first) {@link User} found by <code>firstName</code> and <code>lastName</code>
     * 
     * @param firstName
     *            The {@link User}s first name
     * @param lastName
     *            The {@link User}s last name
     * @return The (first) {@link User} found, else {@literal null}
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return this.userService.getUser(firstName, lastName);
    }

    /**
     * Adds a {@link User} to our {@link UserService}
     * 
     * @param userName
     *            The user's user name
     * @param firstName
     *            The user's first name
     * @param lastName
     *            The user's last name
     * @return The constructed {@link User}
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseBody
    public User addUser(@RequestParam("userName") String userName, @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        User user = new User(userName, firstName, lastName);
        this.userService.addUser(user);
        return user;
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