package com.mops.registrar.entities;

/**
 * Represents a {@link User}'s address
 * 
 * @author dylants
 * 
 */
public class Address {

    protected String homeAddress1;
    protected String homeAddress2;
    protected String city;
    protected String state;
    protected String zipCode;

    /**
     * @return the homeAddress1
     */
    public String getHomeAddress1() {
        return homeAddress1;
    }

    /**
     * @param homeAddress1
     *            the homeAddress1 to set
     */
    public void setHomeAddress1(String homeAddress1) {
        this.homeAddress1 = homeAddress1;
    }

    /**
     * @return the homeAddress2
     */
    public String getHomeAddress2() {
        return homeAddress2;
    }

    /**
     * @param homeAddress2
     *            the homeAddress2 to set
     */
    public void setHomeAddress2(String homeAddress2) {
        this.homeAddress2 = homeAddress2;
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