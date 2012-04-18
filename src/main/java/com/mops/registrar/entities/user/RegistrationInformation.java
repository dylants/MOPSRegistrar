package com.mops.registrar.entities.user;

import java.util.Date;
import java.util.Set;

/**
 * Represents the information filled out during registration
 * 
 * @author dylants
 * 
 */
public class RegistrationInformation {

    private Address address;
    private String phoneNumer;
    private Date birthDate;
    private Set<Child> children;

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
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the phoneNumer
     */
    public String getPhoneNumer() {
        return phoneNumer;
    }

    /**
     * @param phoneNumer
     *            the phoneNumer to set
     */
    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate
     *            the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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