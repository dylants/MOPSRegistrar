package com.mops.registrar.web.page.user;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.User;
import com.mops.registrar.services.user.UserService;
import com.mops.registrar.web.validator.user.UserValidator;

/**
 * Web controller that handles registering new {@link User}s
 * 
 * @author dylants
 * 
 */
@Controller
@RequestMapping(value = "/user/register")
public class RegisterUserController {
    @Autowired
    private UserService userService = null;
    @Autowired
    private UserValidator userValidator = null;

    /**
     * Configures the {@link UserValidator} to be used when validating {@link User} type objects.
     * 
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.userValidator);
    }

    /**
     * Displays a view used to register a {@link User}
     * 
     * @param model
     *            Contains information used by the view
     * @param request
     *            The {@link HttpServletRequest}
     * @return The JSP used to register the user
     */
    @RequestMapping(method = RequestMethod.GET)
    public String registerUser(Model model, HttpServletRequest request) {
        User user = new User();
        populateModel(model, user, request);
        return "user/userForm";
    }

    /**
     * Processes the register user request, registering the user and displaying a view based on the result.
     * 
     * @param user
     *            The {@link User} to register
     * @param bindingResult
     *            The result of the binding of the user input to the {@link User} object
     * @param model
     *            Contains information used by the view
     * @param request
     *            The {@link HttpServletRequest}
     * @return The JSP used to display the next page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processRegisterUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
            Model model, HttpServletRequest request) {
        // first cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show them the registration page again (to fix the errors)
            populateModel(model, user, request);
            return "user/userForm";
        }

        // else add the user to our registry
        this.userService.addUser(user);
        model.addAttribute("user", user);
        // show registration success page
        return "user/registrationSuccess";
    }

    /**
     * Populates the model with generic information required for this view
     * 
     * @param model
     *            The {@link Model} to populate
     * @param user
     *            The {@link User}
     * @param request
     *            The {@link HttpServletRequest}
     */
    protected void populateModel(Model model, User user, HttpServletRequest request) {
        model.addAttribute("user", user);
        // it's a new User we're dealing with, not an existing
        model.addAttribute("isNew", true);
        model.addAttribute("heading", "Welcome! Please register");
        model.addAttribute("submitButtonText", "Register");
        model.addAttribute("homeUrl", request.getContextPath() + "/page/home");
    }
}