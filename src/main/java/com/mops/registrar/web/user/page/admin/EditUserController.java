package com.mops.registrar.web.user.page.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mops.registrar.entities.user.User;
import com.mops.registrar.security.CryptUtil;
import com.mops.registrar.services.user.UserService;
import com.mops.registrar.web.user.validator.UserValidator;
import com.mops.registrar.web.user.validator.admin.UserValidatorForAdmin;

/**
 * Web controller responsible for editing an existing {@link User}
 * 
 * @author dysmith
 * 
 */
@Controller
@RequestMapping(value = "/user/admin/edit/{entityId}")
public class EditUserController {
    @Autowired
    private UserService userService = null;
    @Autowired
    private UserValidatorForAdmin userValidatorForAdmin = null;
    @Autowired
    private CryptUtil cryptUtil = null;

    /**
     * Configures the {@link UserValidator} to be used when validating {@link User} type objects.
     * 
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.userValidatorForAdmin);
    }

    /**
     * Displays a view used to edit a {@link User}
     * 
     * @param entityId
     *            The ID of the {@link User} we should edit
     * @param model
     *            Contains information used by the view
     * @param request
     *            The {@link HttpServletRequest}
     * @param session
     *            The {@link HttpSession}
     * @return The JSP used to edit the user
     */
    @RequestMapping(method = RequestMethod.GET)
    public String editUser(@PathVariable("entityId") String entityId, Model model, HttpServletRequest request,
            HttpSession session) {
        User user = this.userService.getUserByEntityId(entityId);

        /*
         * Because editing the password field(s) are optional, they may not supply one through the edit. If this is the
         * case, we don't want to blow away the user's original password. So keep the User in the session so we can pull
         * this out later if needed.
         */
        session.setAttribute("registrar.user.originalUser", user);

        populateModel(model, user, request);
        return "user/userForm";
    }

    /**
     * Processes the edit user request, edit the user and displaying a view based on the result.
     * 
     * @param user
     *            The edited {@link User}
     * @param bindingResult
     *            The result of the binding of the user input to the {@link User} object
     * @param model
     *            Contains information used by the view
     * @param request
     *            The {@link HttpServletRequest}
     * @param session
     *            The {@link HttpSession}
     * @return The JSP used to display the next page
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String processEditUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model,
            HttpServletRequest request, HttpSession session) {
        // first cover binding errors (invalid input)
        if (bindingResult.hasErrors()) {
            // show the user form again (so they can fix the errors)
            populateModel(model, user, request);
            return "user/userForm";
        }

        /*
         * If they supply a password, use it. If they don't, use the original password.
         */
        if (StringUtils.isNotBlank(user.getPassword())) {
            // use this password in the update, no additional work required
        } else {
            // update this user with the original password
            User originalUser = (User) session.getAttribute("registrar.user.originalUser");
            if (originalUser != null) {
                String password = originalUser.getPassword();
                String plainTextPassword = cryptUtil.decode(password);
                user.setPassword(plainTextPassword);
            }
        }

        // else update the user in our registry
        this.userService.addUser(user);
        model.addAttribute("user", user);
        // show edit success page
        return "user/admin/editSuccess";
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
        model.addAttribute("heading", "Edit Registration Information");
        model.addAttribute("submitButtonText", "Submit");
        model.addAttribute("homeUrl", request.getContextPath() + "/page/user/admin/home");
    }
}