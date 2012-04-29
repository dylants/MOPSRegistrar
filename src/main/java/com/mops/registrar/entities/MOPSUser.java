package com.mops.registrar.entities;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

/**
 * A {@link MOPSUser} contains a RegistrationInformation object, which contains the {@link MOPSUser}'s first name, last
 * name, and additional data collected during the registration process. The {@link MOPSUser} also extends
 * {@link BaseUser} object, which acts as the {@link UserDetails} for Spring Security. Finally, the {@link MOPSUser}
 * links all the {@link Child} objects owned by the {@link MOPSUser}.
 * 
 * @author dylants
 */
public class MOPSUser extends BaseUser implements Comparable<MOPSUser> {

    private static final long serialVersionUID = 4203546994668744168L;

    @Valid
    private RegistrationInformation registrationInformation = new RegistrationInformation();
    @DBRef
    private Set<Child> children = new HashSet<Child>();

    /**
     * Default constructor
     */
    public MOPSUser() {
        // A MOPSUser's GrantedAuthority is ROLE_REGISTERED_USER
        super(Sets.newHashSet(Authorities.ROLE_REGISTERED_USER.getGrantedAuthority()));
    }

    @Override
    public int compareTo(MOPSUser mopsUser) {
        // sort based off last name
        if ((this.registrationInformation != null) && (mopsUser != null)
                && (mopsUser.getRegistrationInformation() != null)) {
            String myLastName = this.registrationInformation.getLastName();
            String yourLastName = mopsUser.getRegistrationInformation().getLastName();

            if (myLastName != null) {
                return myLastName.compareTo(yourLastName);
            } else {
                // unknown
                return 0;
            }
        } else {
            // unknown
            return 0;
        }
    }

    @Override
    public String getUsername() {
        // for a MOPS user, the email address is the username
        if (this.registrationInformation != null) {
            return this.registrationInformation.getEmailAddress();
        }
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        // for a MOPS user, the email address is the username
        if (this.registrationInformation != null) {
            this.registrationInformation.setEmailAddress(username);
        }
        super.setUsername(username);
    }

    @Override
    public String toString() {
        if (this.registrationInformation != null) {
            return "MOPSUser username: " + this.getUsername() + " firstName: "
                    + this.registrationInformation.getFirstName() + " lastName: "
                    + this.registrationInformation.getLastName();
        } else {
            return super.toString();
        }
    }

    /**
     * Adds a {@link Child} to the {@link Set}
     * 
     * @param child
     *            The {@link Child} to add
     */
    public void addChild(Child child) {
        this.children.add(child);
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

    /**
     * @return the children
     */
    public Set<Child> getChildren() {
        return children;
    }

    /**
     * @param children
     *            the children to set
     */
    public void setChildren(Set<Child> children) {
        this.children = children;
    }

}