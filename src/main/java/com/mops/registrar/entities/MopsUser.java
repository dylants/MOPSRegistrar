package com.mops.registrar.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

/**
 * A {@link MopsUser} contains a RegistrationInformation object, which contains the {@link MopsUser}'s first name, last
 * name, and additional data collected during the registration process. The {@link MopsUser} also extends
 * {@link BaseUser} object, which acts as the {@link UserDetails} for Spring Security. Finally, the {@link MopsUser}
 * links to all the {@link Child} objects owned by the {@link MopsUser}, using the entity IDs as the keys.
 * 
 * @author dylants
 */
public class MopsUser extends BaseUser implements Comparable<MopsUser>, Serializable {

    private static final long serialVersionUID = 4203546994668744168L;

    private RegistrationInformation registrationInformation = new RegistrationInformation();
    private Set<String> childrenEntityIds = new HashSet<String>();

    /**
     * Creates a new {@link MopsUser}
     * 
     */
    public MopsUser() {
        // A MopsUser's GrantedAuthority is ROLE_REGISTERED_USER
        super(Sets.newHashSet(Authorities.ROLE_REGISTERED_USER.getGrantedAuthority()));

    }

    @Override
    public int compareTo(MopsUser mopsUser) {
        // sort based off last name
        if ((this.registrationInformation != null) && (mopsUser != null)
                && (mopsUser.getRegistrationInformation() != null)) {
            String myLastName = this.registrationInformation.getLastName();
            String yourLastName = mopsUser.getRegistrationInformation().getLastName();

            if ((myLastName != null) && (yourLastName != null)) {
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
    public String toString() {
        if (this.registrationInformation != null) {
            return "MopsUser username: " + this.getUsername() + " firstName: "
                    + this.registrationInformation.getFirstName() + " lastName: "
                    + this.registrationInformation.getLastName();
        } else {
            return super.toString();
        }
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
     * @return the childrenEntityIds
     */
    public Set<String> getChildrenEntityIds() {
        return childrenEntityIds;
    }

    /**
     * @param childrenEntityIds
     *            the childrenEntityIds to set
     */
    public void setChildrenEntityIds(Set<String> childrenEntityIds) {
        this.childrenEntityIds = childrenEntityIds;
    }

}