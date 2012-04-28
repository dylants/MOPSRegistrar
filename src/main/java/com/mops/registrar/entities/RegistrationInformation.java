package com.mops.registrar.entities;

import java.util.Date;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents the information filled out during registration
 * 
 * @author dylants
 * 
 */
public class RegistrationInformation {

    // required data
    @Valid
    protected Address address;
    @NotBlank
    protected String homePhoneNumber;
    @NotBlank
    protected String cellPhoneNumber;
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    protected Date dateOfBirth;

    // additional data
    protected boolean attendedMopsBefore = false;
    protected String attendedMopsBeforeLocation = null;
    protected boolean registeredMopsToMomConnection = false;
    protected boolean attendChurch = false;
    protected String attendChurchLocation = null;
    protected String howDidYouHearAboutMops = null;
    protected String husbandsName = null;

    // children
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