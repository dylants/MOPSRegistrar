package com.mops.registrar.web.validator.admin;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mops.registrar.entities.MOPSUser;
import com.mops.registrar.web.validator.user.UserValidator;

/**
 * The {@link Validator} that should be used when validating an existing {@link MOPSUser}
 * 
 * @author dylants
 * 
 */
@Component
public class UserValidatorForEditRegistrationInformation extends UserValidator {

    @Override
    protected void performCustomValidation(MOPSUser mopsUser, Errors errors) {
        // no additional validation needed at this time...
    }

}
