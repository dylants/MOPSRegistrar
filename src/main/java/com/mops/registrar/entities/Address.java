package com.mops.registrar.entities;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Represents an address
 * 
 * @author dylants
 * 
 */
public class Address implements Serializable {

    private static final long serialVersionUID = -4417818291082477019L;

    @NotBlank
    protected String streetAddress;
    @NotBlank
    protected String city;
    @NotBlank
    protected String state;
    @NotBlank
    protected String zipCode;

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress
     *            the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}