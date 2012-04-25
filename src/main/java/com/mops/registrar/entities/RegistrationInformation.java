package com.mops.registrar.entities;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents the information filled out during registration
 * 
 * @author dylants
 * 
 */
public class RegistrationInformation {

    protected Address address;
    protected String daytimePhone;
    protected String eveningPhone;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    protected Date dateOfBirth;
    protected Set<Child> children;

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
     * @return the daytimePhone
     */
    public String getDaytimePhone() {
        return daytimePhone;
    }

    /**
     * @param daytimePhone
     *            the daytimePhone to set
     */
    public void setDaytimePhone(String phoneNumer) {
        this.daytimePhone = phoneNumer;
    }

    /**
     * @return the eveningPhone
     */
    public String getEveningPhone() {
        return eveningPhone;
    }

    /**
     * @param eveningPhone
     *            the eveningPhone to set
     */
    public void setEveningPhone(String eveningPhone) {
        this.eveningPhone = eveningPhone;
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
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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