package com.mops.registrar.web.validator.admin;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mops.registrar.entities.User;
import com.mops.registrar.web.validator.user.UserValidator;

/**
 * The {@link Validator} that should be used when validating the {@link User} on Admin pages
 * 
 * @author dylants
 * 
 */
@Component
public class UserValidatorForAdmin extends UserValidator {

    @Override
    protected void performCustomValidation(User user, Errors errors) {
        /*
         * On the Admin edit page, supplying the password is optional. However, if they do enter one in, we need to
         * verify it matches the other.
         */
        String password = user.getClearTextPassword();
        String confirmPassword = user.getClearTextConfirmPassword();
        if (!password.equals(confirmPassword)) {
            errors.rejectValue("password", "NotMatch.user.password");
        }

    }

}
