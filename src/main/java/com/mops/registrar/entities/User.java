package com.mops.registrar.entities;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

/**
 * A User contains an email address along with a password, and first and last name. The User also has an associated
 * RegistrationInformation object, which contains additional data collected during the registration process. The User is
 * also used as the {@link UserDetails} for Spring Security, in the case of a "registered user".
 * 
 * @author dylants
 */
public class User extends BaseUser implements UserDetails, CredentialsContainer, Comparable<User> {

    // for Serializable
    private static final long serialVersionUID = -6102304126936444301L;

    @NotBlank
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    protected String emailAddress = null;
    @NotBlank
    protected String firstName = null;
    @NotBlank
    protected String lastName = null;
    protected RegistrationInformation registrationInformation = null;

    /**
     * Default constructor
     */
    public User() {
        // A User's GrantedAuthority is ROLE_REGISTERED_USER
        super(Sets.newHashSet(Authorities.ROLE_REGISTERED_USER.getGrantedAuthority()));
    }

    @Override
    public String getUsername() {
        // in our case, we have no "username" so use the email address instead
        return this.emailAddress;
    }

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