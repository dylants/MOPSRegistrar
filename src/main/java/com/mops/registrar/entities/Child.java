package com.mops.registrar.entities;

import java.util.Date;

/**
 * A child of {@link User}
 * 
 * @author dylants
 * 
 */
public class Child {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String fatherFirstName;
    private String fatherLastName;
    private String doctorName;
    private String doctorPhoneNumber;
    private String allergyInformation;

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
     * @return the fatherFirstName
     */
    public String getFatherFirstName() {
        return fatherFirstName;
    }

    /**
     * @param fatherFirstName
     *            the fatherFirstName to set
     */
    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
    }

    /**
     * @return the fatherLastName
     */
    public String getFatherLastName() {
        return fatherLastName;
    }

    /**
     * @param fatherLastName
     *            the fatherLastName to set
     */
    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    /**
     * @return the doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @param doctorName
     *            the doctorName to set
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * @return the doctorPhoneNumber
     */
    public String getDoctorPhoneNumber() {
        return doctorPhoneNumber;
    }

    /**
     * @param doctorPhoneNumber
     *            the doctorPhoneNumber to set
     */
    public void setDoctorPhoneNumber(String doctorPhoneNumber) {
        this.doctorPhoneNumber = doctorPhoneNumber;
    }

    /**
     * @return the allergyInformation
     */
    public String getAllergyInformation() {
        return allergyInformation;
    }

    /**
     * @param allergyInformation
     *            the allergyInformation to set
     */
    public void setAllergyInformation(String allergyInformation) {
        this.allergyInformation = allergyInformation;
    }
}