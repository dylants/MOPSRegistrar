package com.mops.registrar.entities;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents the information filled out during registration of a {@link MopsUser}
 * 
 * @author dylants
 * 
 */
public class RegistrationInformation implements Serializable {

    private static final long serialVersionUID = 2531120783702330139L;

    // information about the user
    @NotBlank
    private String firstName = null;
    private String middleInitial = null;
    @NotBlank
    private String lastName = null;
    @Valid
    private Address address;
    @NotBlank
    private String homePhoneNumber;
    @NotBlank
    private String cellPhoneNumber;
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateOfBirth;

    // additional data
    private boolean attendedMopsBefore = false;
    private String attendedMopsBeforeLocation = null;
    private boolean registeredMopsToMomConnection = false;
    private boolean attendChurch = false;
    private String attendChurchLocation = null;
    private String howDidYouHearAboutMops = null;
    private String husbandsName = null;

    // admin only data
    private String amountPaid;
    private String additionalNotes;

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
     * @return the middleInitial
     */
    public String getMiddleInitial() {
        return middleInitial;
    }

    /**
     * @param middleInitial
     *            the middleInitial to set
     */
    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
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
     * @return the homePhoneNumber
     */
    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    /**
     * @param homePhoneNumber
     *            the homePhoneNumber to set
     */
    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    /**
     * @return the cellPhoneNumber
     */
    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    /**
     * @param cellPhoneNumber
     *            the cellPhoneNumber to set
     */
    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
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
     * @return the attendedMopsBefore
     */
    public boolean isAttendedMopsBefore() {
        return attendedMopsBefore;
    }

    /**
     * @param attendedMopsBefore
     *            the attendedMopsBefore to set
     */
    public void setAttendedMopsBefore(boolean attendedMopsBefore) {
        this.attendedMopsBefore = attendedMopsBefore;
    }

    /**
     * @return the attendedMopsBeforeLocation
     */
    public String getAttendedMopsBeforeLocation() {
        return attendedMopsBeforeLocation;
    }

    /**
     * @param attendedMopsBeforeLocation
     *            the attendedMopsBeforeLocation to set
     */
    public void setAttendedMopsBeforeLocation(String attendedMopsBeforeLocation) {
        this.attendedMopsBeforeLocation = attendedMopsBeforeLocation;
    }

    /**
     * @return the registeredMopsToMomConnection
     */
    public boolean isRegisteredMopsToMomConnection() {
        return registeredMopsToMomConnection;
    }

    /**
     * @param registeredMopsToMomConnection
     *            the registeredMopsToMomConnection to set
     */
    public void setRegisteredMopsToMomConnection(boolean registeredMopsToMomConnection) {
        this.registeredMopsToMomConnection = registeredMopsToMomConnection;
    }

    /**
     * @return the attendChurch
     */
    public boolean isAttendChurch() {
        return attendChurch;
    }

    /**
     * @param attendChurch
     *            the attendChurch to set
     */
    public void setAttendChurch(boolean attendChurch) {
        this.attendChurch = attendChurch;
    }

    /**
     * @return the attendChurchLocation
     */
    public String getAttendChurchLocation() {
        return attendChurchLocation;
    }

    /**
     * @param attendChurchLocation
     *            the attendChurchLocation to set
     */
    public void setAttendChurchLocation(String attendChurchLocation) {
        this.attendChurchLocation = attendChurchLocation;
    }

    /**
     * @return the howDidYouHearAboutMops
     */
    public String getHowDidYouHearAboutMops() {
        return howDidYouHearAboutMops;
    }

    /**
     * @param howDidYouHearAboutMops
     *            the howDidYouHearAboutMops to set
     */
    public void setHowDidYouHearAboutMops(String howDidYouHearAboutMops) {
        this.howDidYouHearAboutMops = howDidYouHearAboutMops;
    }

    /**
     * @return the husbandsName
     */
    public String getHusbandsName() {
        return husbandsName;
    }

    /**
     * @param husbandsName
     *            the husbandsName to set
     */
    public void setHusbandsName(String husbandsName) {
        this.husbandsName = husbandsName;
    }

    /**
     * @return the amountPaid
     */
    public String getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid
     *            the amountPaid to set
     */
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return the additionalNotes
     */
    public String getAdditionalNotes() {
        return additionalNotes;
    }

    /**
     * @param additionalNotes
     *            the additionalNotes to set
     */
    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}