package com.mops.registrar.web.validator.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

import com.mops.registrar.web.page.user.CreateAccountModel;

/**
 * A custom validator used to validate the {@link CreateAccountModel} object. This validator runs the JSR 303 validation
 * logic first, then runs the custom validation logic. Both are combined in the {@link Errors} object to be consumed by
 * the view later.
 * 
 * @author dylants
 * 
 */
@Component
public class CreateAccountValidator extends CustomValidatorBean {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateAccountModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);

        performCustomValidation((CreateAccountModel) target, errors);

        if (errors.hasErrors()) {
            errors.reject("global.errors.form");
        }
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        super.validate(target, errors, validationHints);

        performCustomValidation((CreateAccountModel) target, errors);

        if (errors.hasErrors()) {
            errors.reject("global.errors.form");
        }
    }

    /**
     * Perform our custom validation logic to verify the {@link CreateAccountModel}
     * 
     * @param createAccountModel
     *            The {@link CreateAccountModel} object to verify
     * @param errors
     *            {@link Errors} object used to store validation errors
     */
    protected void performCustomValidation(CreateAccountModel createAccountModel, Errors errors) {
        /*
         * Make sure the password and confirm password match
         */
        String password = createAccountModel.getPassword();
        String confirmPassword = createAccountModel.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            errors.rejectValue("password", "NotMatch.createAccountModel.password");
        }
    }
}