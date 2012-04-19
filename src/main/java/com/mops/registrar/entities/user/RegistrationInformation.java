package com.mops.registrar.entities.user;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Represents the information filled out during registration
 * 
 * @author dylants
 * 
 */
public class RegistrationInformation {

    private Address address;
    private String phoneNumber;
    @DateTimeFormat(iso=ISO.DATE)
    private Date dateOfBirth;
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
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     *            the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumer) {
        this.phoneNumber = phoneNumer;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth
     *            the dateOfBirth to set
     */
    public void setDateOfBirth(Date birthDate) {
        this.dateOfBirth = birthDate;
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