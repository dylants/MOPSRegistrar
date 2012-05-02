package com.mops.registrar.web.page.user;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class CreateAccountModel {

    @NotBlank
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String emailAddress = null;
    @NotBlank
    private String password = null;
    @NotBlank
    private String confirmPassword = null;

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress
     *            the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword
     *            the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
