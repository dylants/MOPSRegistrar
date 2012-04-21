package com.mops.registrar.web.validator.user;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

import com.mops.registrar.entities.User;

/**
 * A custom validator used to validate the {@link User} object. This validator runs the JSR 303 validation logic first,
 * then runs the custom validation logic. Both are combined in the {@link Errors} object to be consumed by the view
 * later.
 * 
 * @author dylants
 * 
 */
@Component
public class UserValidator extends CustomValidatorBean {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);

        performCustomValidation((User) target, errors);

        if (errors.hasErrors()) {
            errors.reject("global.user.form");
        }
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        super.validate(target, errors, validationHints);

        performCustomValidation((User) target, errors);

        if (errors.hasErrors()) {
            errors.reject("global.user.form");
        }
    }

    /**
     * Perform our custom validation logic to verify the {@link User}
     * 
     * @param user
     *            The {@link User} object to verify
     * @param errors
     *            {@link Errors} object used to store validation errors
     */
    protected void performCustomValidation(User user, Errors errors) {
        /*
         * Validate the password and confirm password fields are not blank
         */
        String clearTextPassword = user.getClearTextPassword();
        if (StringUtils.isBlank(clearTextPassword)) {
            errors.rejectValue("clearTextPassword", "NotBlank.user.clearTextPassword");
        }
        String clearTextConfirmPassword = user.getClearTextConfirmPassword();
        if (StringUtils.isBlank(clearTextConfirmPassword)) {
            errors.rejectValue("clearTextConfirmPassword", "NotBlank.user.clearTextConfirmPassword");
        }
        /*
         * Make sure the password and confirm password match
         */
        if (!clearTextPassword.equals(clearTextConfirmPassword)) {
            errors.rejectValue("clearTextPassword", "NotMatch.user.clearTextPassword");
        }
    }
}