package com.mops.registrar.elements.user;

import org.springframework.data.annotation.Id;

/**
 * A User contains one unique ID field (the email address) along with a password, and first and last name. The User also
 * has an associated RegistrationInformation object, which contains additional data collected during the registration
 * process.
 * 
 */
public class User {

    @Id
    private String emailAddress = null;
    private String password = null;
    private String firstName = null;
    private String lastName = null;

    /**
     * Constructs an empty {@link User}
     */
    public User() {
    }

    /**
     * Constructs a {@link User}
     * 
     * @param emailAddress
     *            The user's uniquely identifiable email address
     * @param password
     *            The user's password to this site
     * @param firstName
     *            The user's first name
     * @param lastName
     *            The user's last name
     */
    public User(String emailAddress, String password, String firstName, String lastName) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "User emailAddress: " + this.emailAddress + " firstName: " + this.firstName + " lastName: "
                + this.lastName;
    }
}