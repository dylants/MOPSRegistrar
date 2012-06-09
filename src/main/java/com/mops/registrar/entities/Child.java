package com.mops.registrar.entities;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * A child of {@link MopsUser}
 * 
 * @author dylants
 * 
 */
public class Child extends BaseEntity implements Comparable<Child>, Serializable {
    private static final long serialVersionUID = -7034848080054411428L;

    protected static final String YEARS_OLD = "years old";
    protected static final String MONTHS_OLD = "months old";

    // this points us back at the parent of this child
    private String mopsUserEntityId;

    // information about the Child
    @NotBlank
    private String firstName;
    private String middleInitial;
    @NotBlank
    private String lastName;
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateOfBirth;

    // information about the father (we know the mother from the MOPS user)
    @NotBlank
    private String fatherFirstName;
    private String fatherMiddleInitial;
    @NotBlank
    private String fatherLastName;
    @NotBlank
    private String fatherEmergencyContactPhoneNumber;
    // default this to true (per request)
    private boolean fatherLiveAtHome = true;

    // medical information
    @NotBlank
    private String doctorName;
    @NotBlank
    private String doctorPhoneNumber;
    @Valid
    private Address doctorAddress;

    @NotBlank
    private String additionalEmergencyContactName;
    @NotBlank
    private String additionalEmergencyContactPhoneNumber;
    @NotBlank
    private String additionalEmergencyContactRelationship;

    private String siblingsNameAndBirthDate;
    private String favoriteToysSongsGamesFoods;
    private String specialNeedsAndInstructionsAllergyInformation;

    /**
     * Default constructor (for Spring)
     */
    public Child() {
    }

    /**
     * Creates a {@link Child} linked to a parent {@link MopsUser}
     * 
     * @param mopsUserEntityId
     *            The entity ID of the parent {@link MopsUser}
     */
    public Child(String mopsUserEntityId) {
        this.mopsUserEntityId = mopsUserEntityId;
    }

    @Override
    public int compareTo(Child child) {
        // sanity check
        if ((this.dateOfBirth == null) || (child.getDateOfBirth() == null)) {
            return 0;
        }

        return this.dateOfBirth.compareTo(child.getDateOfBirth());
    }

    /**
     * Returns the age of the {@link Child}, using months for less than 2, years 2 and up.
     * 
     * @return The age of the {@link Child}, using months for less than 2, years 2 and up.
     */
    public String getAge() {
        // sanity check
        if (this.dateOfBirth == null) {
            return null;
        }

        // let's see what date is now
        LocalDate now = new LocalDate();
        // then get the years between this Child's birth and now
        Years ageInYears = Years.yearsBetween(new LocalDate(this.dateOfBirth), now);
        // if the age is less than 2 years old
        if (ageInYears.isLessThan(Years.TWO)) {
            // get the months old instead
            Months ageInMonths = Months.monthsBetween(new LocalDate(this.dateOfBirth), now);
            return ageInMonths.getMonths() + " " + MONTHS_OLD;
        } else {
            // else get the years old
            return ageInYears.getYears() + " " + YEARS_OLD;
        }
    }

    /**
     * @return the mopsUserEntityId
     */
    public String getMopsUserEntityId() {
        return mopsUserEntityId;
    }

    /**
     * @param mopsUserEntityId
     *            the mopsUserEntityId to set
     */
    public void setMopsUserEntityId(String mopsUserEntityId) {
        this.mopsUserEntityId = mopsUserEntityId;
    }

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
     * @return the fatherMiddleInitial
     */
    public String getFatherMiddleInitial() {
        return fatherMiddleInitial;
    }

    /**
     * @param fatherMiddleInitial
     *            the fatherMiddleInitial to set
     */
    public void setFatherMiddleInitial(String fatherMiddleInitial) {
        this.fatherMiddleInitial = fatherMiddleInitial;
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
     * @return the fatherEmergencyContactPhoneNumber
     */
    public String getFatherEmergencyContactPhoneNumber() {
        return fatherEmergencyContactPhoneNumber;
    }

    /**
     * @param fatherEmergencyContactPhoneNumber
     *            the fatherEmergencyContactPhoneNumber to set
     */
    public void setFatherEmergencyContactPhoneNumber(String fatherEmergencyContactPhoneNumber) {
        this.fatherEmergencyContactPhoneNumber = fatherEmergencyContactPhoneNumber;
    }

    /**
     * @return the fatherLiveAtHome
     */
    public boolean isFatherLiveAtHome() {
        return fatherLiveAtHome;
    }

    /**
     * @param fatherLiveAtHome
     *            the fatherLiveAtHome to set
     */
    public void setFatherLiveAtHome(boolean fatherLiveAtHome) {
        this.fatherLiveAtHome = fatherLiveAtHome;
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
     * @return the doctorAddress
     */
    public Address getDoctorAddress() {
        return doctorAddress;
    }

    /**
     * @param doctorAddress
     *            the doctorAddress to set
     */
    public void setDoctorAddress(Address doctorAddress) {
        this.doctorAddress = doctorAddress;
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
     * @return the additionalEmergencyContactName
     */
    public String getAdditionalEmergencyContactName() {
        return additionalEmergencyContactName;
    }

    /**
     * @param additionalEmergencyContactName
     *            the additionalEmergencyContactName to set
     */
    public void setAdditionalEmergencyContactName(String additionalEmergencyContactName) {
        this.additionalEmergencyContactName = additionalEmergencyContactName;
    }

    /**
     * @return the additionalEmergencyContactPhoneNumber
     */
    public String getAdditionalEmergencyContactPhoneNumber() {
        return additionalEmergencyContactPhoneNumber;
    }

    /**
     * @param additionalEmergencyContactPhoneNumber
     *            the additionalEmergencyContactPhoneNumber to set
     */
    public void setAdditionalEmergencyContactPhoneNumber(String additionalEmergencyContactPhoneNumber) {
        this.additionalEmergencyContactPhoneNumber = additionalEmergencyContactPhoneNumber;
    }

    /**
     * @return the additionalEmergencyContactRelationship
     */
    public String getAdditionalEmergencyContactRelationship() {
        return additionalEmergencyContactRelationship;
    }

    /**
     * @param additionalEmergencyContactRelationship
     *            the additionalEmergencyContactRelationship to set
     */
    public void setAdditionalEmergencyContactRelationship(String additionalEmergencyContactRelationship) {
        this.additionalEmergencyContactRelationship = additionalEmergencyContactRelationship;
    }

    /**
     * @return the siblingsNameAndBirthDate
     */
    public String getSiblingsNameAndBirthDate() {
        return siblingsNameAndBirthDate;
    }

    /**
     * @param siblingsNameAndBirthDate
     *            the siblingsNameAndBirthDate to set
     */
    public void setSiblingsNameAndBirthDate(String siblingsNameAndBirthDate) {
        this.siblingsNameAndBirthDate = siblingsNameAndBirthDate;
    }

    /**
     * @return the favoriteToysSongsGamesFoods
     */
    public String getFavoriteToysSongsGamesFoods() {
        return favoriteToysSongsGamesFoods;
    }

    /**
     * @param favoriteToysSongsGamesFoods
     *            the favoriteToysSongsGamesFoods to set
     */
    public void setFavoriteToysSongsGamesFoods(String favoriteToysSongsGamesFoods) {
        this.favoriteToysSongsGamesFoods = favoriteToysSongsGamesFoods;
    }

    /**
     * @return the specialNeedsAndInstructionsAllergyInformation
     */
    public String getSpecialNeedsAndInstructionsAllergyInformation() {
        return specialNeedsAndInstructionsAllergyInformation;
    }

    /**
     * @param specialNeedsAndInstructionsAllergyInformation
     *            the specialNeedsAndInstructionsAllergyInformation to set
     */
    public void setSpecialNeedsAndInstructionsAllergyInformation(String specialNeedsAndInstructionsAllergyInformation) {
        this.specialNeedsAndInstructionsAllergyInformation = specialNeedsAndInstructionsAllergyInformation;
    }

}