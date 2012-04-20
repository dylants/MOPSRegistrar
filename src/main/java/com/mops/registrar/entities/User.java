package com.mops.registrar.entities;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;

/**
 * A User contains an email address along with a password, and first and last name. The User also has an associated
 * RegistrationInformation object, which contains additional data collected during the registration process.
 * 
 */
public class User extends BaseEntity implements Comparable<User> {

    @NotBlank
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String emailAddress = null;
    // password validation done per validator
    @Transient
    private String password = null;
    // confirm password validation done per validator
    @Transient
    private String confirmPassword = null;
    private String passwordHash = null;
    @NotBlank
    private String firstName = null;
    @NotBlank
    private String lastName = null;
    private RegistrationInformation registrationInformation = null;

    @Override
    public int compareTo(User user) {
        // sort based off last name
        String myLastName = this.lastName;
        String yourLastName = user.getLastName();

        return myLastName.compareTo(yourLastName);
    }

    @Override
    public String toString() {
        return "User emailAddress: " + this.emailAddress + " firstName: " + this.firstName + " lastName: "
                + this.lastName;
    }

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
     * Returns the password, encoded
     * 
     * @return The password encoded.
     */
    public String getPassword() {
        return this.password;
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

    /**
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash
     *            the passwordHash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the registrationInformation
     */
    public RegistrationInformation getRegistrationInformation() {
        return registrationInformation;
    }

    /**
     * @param registrationInformation
     *            the registrationInformation to set
     */
    public void setRegistrationInformation(RegistrationInformation registrationInformation) {
        this.registrationInformation = registrationInformation;
    }

}