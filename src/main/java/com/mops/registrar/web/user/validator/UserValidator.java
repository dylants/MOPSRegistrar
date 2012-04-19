package com.mops.registrar.web.user.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

import com.mops.registrar.entities.user.User;

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
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        super.validate(target, errors, validationHints);

        performCustomValidation((User) target, errors);
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
        String password = user.getPassword();
        if (StringUtils.isBlank(password)) {
            errors.rejectValue("password", "NotBlank.user.password");
        }
        String confirmPassword = user.getConfirmPassword();
        if (StringUtils.isBlank(confirmPassword)) {
            errors.rejectValue("confirmPassword", "NotBlank.user.confirmPassword");
        }
        /*
         * Make sure the password and confirm password match
         */
        if (!password.equals(confirmPassword)) {
            errors.rejectValue("password", "NotMatch.user.password");
        }
    }
}