package com.mops.registrar.entities;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

/**
 * A User contains an email address along with a password, and first and last name. The User also has an associated
 * RegistrationInformation object, which contains additional data collected during the registration process. The User is
 * also used as the {@link UserDetails} for Spring Security.
 * 
 * @author dylants
 */
public class User extends BaseEntity implements UserDetails, CredentialsContainer, Comparable<User> {

    // for Serializable
    private static final long serialVersionUID = -6102304126936444301L;

    @NotBlank
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    protected String emailAddress = null;
    // password validation done per validator
    @Transient
    protected String clearTextPassword = null;
    // confirm password validation done per validator
    @Transient
    protected String clearTextConfirmPassword = null;
    protected String passwordHash = null;
    @NotBlank
    protected String firstName = null;
    @NotBlank
    protected String lastName = null;
    protected RegistrationInformation registrationInformation = null;
    /*
     * Spring security elements
     */
    protected Set<GrantedAuthority> authorities;
    protected boolean accountNonExpired;
    protected boolean accountNonLocked;
    protected boolean credentialsNonExpired;
    protected boolean enabled;

    /**
     * Default constructor
     */
    public User() {
        // we need the id for password hashing, so always generate one on new User's
        this.entityId = UUID.randomUUID().toString();
        // provide some defaults for spring security elements
        this.authorities = Sets.newHashSet(Authorities.ROLE_REGISTERED_USER.getGrantedAuthority(),
                Authorities.ROLE_ANONYMOUS.getGrantedAuthority());
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    @Override
    public boolean isNew() {
        /*
         * Because we're always generating an entity ID to use for password hashing, it won't be null on new User's.
         * This means we need to pick another field to trigger off of, so let's pick the emailAddress/username, which
         * must NOT be null
         */
        return (this.emailAddress == null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * This is Spring Security's {@link UserDetails} method, which should return the password we compare against and
     * never return null. Because of this, we'll return the password hash here instead of the actual password. The
     * {@link PasswordEncoder} will be used to compare, and will hash the user's entered password when verifying they
     * match.<br/>
     * <br/>
     * Note: This does NOT return the actual password. See {@link User#getClearTextPassword()} for the actual password
     * (which is not persisted).
     * 
     */
    @Override
    public String getPassword() {
        return getPasswordHash();
    }

    @Override
    public String getUsername() {
        return this.emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void eraseCredentials() {
        // clear both the password and confirm password (just in case)
        this.clearTextPassword = null;
        this.clearTextConfirmPassword = null;
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
     * @return the clearTextPassword
     */
    public String getClearTextPassword() {
        return clearTextPassword;
    }

    /**
     * @param clearTextPassword
     *            the clearTextPassword to set
     */
    public void setClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    /**
     * @return the clearTextConfirmPassword
     */
    public String getClearTextConfirmPassword() {
        return clearTextConfirmPassword;
    }

    /**
     * @param clearTextConfirmPassword
     *            the clearTextConfirmPassword to set
     */
    public void setClearTextConfirmPassword(String clearTextConfirmPassword) {
        this.clearTextConfirmPassword = clearTextConfirmPassword;
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