package com.mops.registrar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;

import com.mops.registrar.entities.Address;
import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.Child;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.services.admin.AdminUserService;
import com.mops.registrar.services.child.ChildService;
import com.mops.registrar.services.user.UserService;

/**
 * Helper class that generates mostly user data when database population is needed
 * 
 * @author dylants
 * 
 */
public class GenerateData {
    private static final String contextXMLFile = ClassUtils.classPackageAsResourcePath(GenerateData.class)
            + "/generate-data-spring.xml";

    private UserService userService = null;
    private AdminUserService adminUserService = null;
    private ChildService childService = null;

    /**
     * Default constructor (loads the spring beans)
     */
    public GenerateData() {
        loadSpringContext();
    }

    /**
     * Loads up the Spring context to instantiate the required beans
     */
    protected void loadSpringContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextXMLFile);
        this.userService = context.getBean(UserService.class);
        this.adminUserService = context.getBean(AdminUserService.class);
        this.childService = context.getBean(ChildService.class);
    }

    /**
     * Generates a new {@link MopsUser} and adds it to the database
     * 
     * @param emailAddress
     * @param password
     * @param firstName
     * @param middleInitial
     * @param lastName
     * @param streetAddress
     * @param city
     * @param state
     * @param zipCode
     * @param homePhoneNumber
     * @param cellPhoneNumber
     * @param dateOfBirth
     * @param attendedMopsBefore
     * @param attendedMopsBeforeLocation
     * @param registeredMopsToMomConnection
     * @param attendedChurch
     * @param attendedChurchLocation
     * @param howDidYouHearAboutMops
     * @param husbandName
     * @param amountPaid
     * @param additionalNotes
     * @return The generated {@link MopsUser}
     */
    public MopsUser generateUser(String emailAddress, String password, String firstName, String middleInitial,
            String lastName, String streetAddress, String city, String state, String zipCode, String homePhoneNumber,
            String cellPhoneNumber, Date dateOfBirth, boolean attendedMopsBefore, String attendedMopsBeforeLocation,
            boolean registeredMopsToMomConnection, boolean attendedChurch, String attendedChurchLocation,
            String howDidYouHearAboutMops, String husbandName, String amountPaid, String additionalNotes) {

        MopsUser mopsUser = new MopsUser();
        mopsUser.setUsername(emailAddress);
        mopsUser.setPasswordHash(this.userService.generatePasswordHash(password, mopsUser));

        Address address = new Address();
        address.setStreetAddress(streetAddress);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);

        RegistrationInformation registrationInformation = new RegistrationInformation();
        registrationInformation.setFirstName(firstName);
        registrationInformation.setMiddleInitial(middleInitial);
        registrationInformation.setLastName(lastName);
        registrationInformation.setAddress(address);
        registrationInformation.setHomePhoneNumber(homePhoneNumber);
        registrationInformation.setCellPhoneNumber(cellPhoneNumber);
        registrationInformation.setDateOfBirth(dateOfBirth);

        registrationInformation.setAttendedMopsBefore(attendedMopsBefore);
        registrationInformation.setAttendedMopsBeforeLocation(attendedMopsBeforeLocation);
        registrationInformation.setRegisteredMopsToMomConnection(registeredMopsToMomConnection);
        registrationInformation.setAttendChurch(attendedChurch);
        registrationInformation.setAttendChurchLocation(attendedChurchLocation);
        registrationInformation.setHowDidYouHearAboutMops(howDidYouHearAboutMops);
        registrationInformation.setHusbandsName(husbandName);
        
        registrationInformation.setAmountPaid(amountPaid);
        registrationInformation.setAdditionalNotes(additionalNotes);

        mopsUser.setRegistrationInformation(registrationInformation);

        return this.userService.addUser(mopsUser);
    }

    /**
     * Generates a new {@link AdminUser} and adds it to the database
     * 
     * @param username
     * @param password
     * @return The generated {@link AdminUser}
     */
    public AdminUser generateAdminUser(String username, String password) {

        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        adminUser.setPasswordHash(this.adminUserService.generatePasswordHash(password, adminUser));

        return this.adminUserService.addAdminUser(adminUser);
    }

    /**
     * Generates a {@link Child} entity and adds it to the database
     * 
     * @param mopsUser
     * @param firstName
     * @param middleInitial
     * @param lastName
     * @param dateOfBirth
     * @param fatherFirstName
     * @param fatherMiddleInitial
     * @param fatherLastName
     * @param fatherEmergencyContactPhoneNumber
     * @param fatherLiveAtHome
     * @param doctorName
     * @param doctorStreetAddress
     * @param doctorCity
     * @param doctorState
     * @param doctorZipCode
     * @param doctorPhoneNumber
     * @param additionalEmergencyContactName
     * @param additionalEmergencyContactPhoneNumber
     * @param additionalEmergencyContactRelationship
     * @param siblingsNameAndBirthDate
     * @param favoriteToysSongsGamesFoods
     * @param specialNeedsAndInstructionsAllergyInformation
     * @return The generated {@link Child}
     */
    public Child generateChild(MopsUser mopsUser, String firstName, String middleInitial, String lastName,
            Date dateOfBirth, String fatherFirstName, String fatherMiddleInitial, String fatherLastName,
            String fatherEmergencyContactPhoneNumber, boolean fatherLiveAtHome, String doctorName,
            String doctorStreetAddress, String doctorCity, String doctorState, String doctorZipCode,
            String doctorPhoneNumber, String additionalEmergencyContactName,
            String additionalEmergencyContactPhoneNumber, String additionalEmergencyContactRelationship,
            String siblingsNameAndBirthDate, String favoriteToysSongsGamesFoods,
            String specialNeedsAndInstructionsAllergyInformation) {

        Child child = new Child(mopsUser.getEntityId());
        child.setFirstName(firstName);
        child.setMiddleInitial(middleInitial);
        child.setLastName(lastName);
        child.setDateOfBirth(dateOfBirth);

        child.setFatherFirstName(fatherFirstName);
        child.setFatherMiddleInitial(fatherMiddleInitial);
        child.setFatherLastName(fatherLastName);
        child.setFatherEmergencyContactPhoneNumber(fatherEmergencyContactPhoneNumber);
        child.setFatherLiveAtHome(fatherLiveAtHome);

        child.setDoctorName(doctorName);
        Address doctorAddress = new Address();
        doctorAddress.setStreetAddress(doctorStreetAddress);
        doctorAddress.setCity(doctorCity);
        doctorAddress.setState(doctorState);
        doctorAddress.setZipCode(doctorZipCode);
        child.setDoctorAddress(doctorAddress);
        child.setDoctorPhoneNumber(doctorPhoneNumber);

        child.setAdditionalEmergencyContactName(additionalEmergencyContactName);
        child.setAdditionalEmergencyContactPhoneNumber(additionalEmergencyContactPhoneNumber);
        child.setAdditionalEmergencyContactRelationship(additionalEmergencyContactRelationship);

        child.setSiblingsNameAndBirthDate(siblingsNameAndBirthDate);
        child.setFavoriteToysSongsGamesFoods(favoriteToysSongsGamesFoods);
        child.setSpecialNeedsAndInstructionsAllergyInformation(specialNeedsAndInstructionsAllergyInformation);

        // add it to the child repository
        child = this.childService.addChild(child);

        // link it with the mops user
        this.userService.addChildEntityId(mopsUser.getEntityId(), child.getEntityId());

        // return the child
        return child;
    }

    public static void main(String[] args) throws Exception {
        GenerateData generateData = new GenerateData();
        generateData.loadSpringContext();

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        // generate User's and their Children
        MopsUser janeDoe = generateData.generateUser("janedoe@email.com", "janedoe", "Jane", "Q", "Doe",
                "94920 Street", "Austin", "Texas", "75839", "512-993-2901", "512-585-5993",
                dateFormat.parse("03/12/81"), true, "Austin MOPS", false, true, "Church of Austin", "From a friend",
                "John Doe", "70", "Check #2413");
        generateData.generateChild(janeDoe, "Billy", "T", "Doe", dateFormat.parse("08/10/11"), "John", "Q", "Doe",
                "512-932-1239", true, "Doctor Hamilton", "9209 Doctor Street", "Austin", "Texas", "78787",
                "512-230-2991", "Suzanne Murphy", "512-382-9311", "Friend", "Suzy 09/08/08",
                "Loves kites, trains, and strawberries", "none");
        generateData.generateChild(janeDoe, "Suzy", "Q", "Doe", dateFormat.parse("09/08/08"), "John", "Q", "Doe",
                "512-932-1239", true, "Doctor Hamilton", "9209 Doctor Street", "Austin", "Texas", "78787",
                "512-230-2991", "Suzanne Murphy", "512-382-9311", "Friend", "Billy 08/10/11",
                "Playing princess, loves fruit snacks", "none");

        MopsUser sueHenderson = generateData.generateUser("suehenderson@one.com", "suehenderson", "Sue", "T",
                "Henderson", "234 Avenue A, Apt 943", "New York", "New York", "11903", "939-321-9560", "213-300-2001",
                dateFormat.parse("01/19/87"), false, "", false, false, "", "Internet", "Biff", "0", "");
        generateData.generateChild(sueHenderson, "John", "P", "Henderson", dateFormat.parse("12/28/11"), "Biff", "R",
                "Henderson", "512-291-1832", true, "Doctor Markson", "1231 Park Avenue", "Round Rock", "Texas",
                "76757", "512-839-2912", "Timothy Henderson", "310-855-3920", "Grandfather", "",
                "Happy songs and playing peek-a-boo", "");

        MopsUser maryJane = generateData
                .generateUser("maryjane@mars.com", "maryjane", "Mary", "", "Jane", "22932 Park Lane", "Round Rock",
                        "Texas", "75832", "512-301-6675", "512-301-6675", dateFormat.parse("04/02/79"), true,
                        "Round Rock MOPS", true, true, "Church of Round Rock", "Family", "", "35", "Check #589");
        generateData.generateChild(maryJane, "Jim", "E", "Jane", dateFormat.parse("02/19/09"), "Tom", "W", "Jane",
                "512-221-8484", false, "Doctor Richardson", "483 Avenue B", "Round Rock", "Texas", "78681",
                "512-344-3929", "Fran Tarpin", "512-909-2112", "Grandmother", null,
                "Playing with blocks.  Loves chocolate", null);

        MopsUser amyZooma = generateData.generateUser("amyzooma@gmail.com", "amyzooma", "Amy", "M", "Zooma",
                "1029 Cove", "Round Rock", "Texas", "77348", "512-212-0503", "512-310-4992",
                dateFormat.parse("10/20/85"), true, "Round Rock MOPS", false, true, "Church of Round Rock", "Friend",
                "Brian Zooma", "0", null);
        generateData.generateChild(amyZooma, "Ricky", "K", "Zooma", dateFormat.parse("05/14/08"), "Brian", "N",
                "Zooma", "512-546-2834", true, "Doctor Eberham", "1212 Wikersham St", "Round Rock", "Texas", "77676",
                "512-289-5345", "Archie Zooma", "512-635-0098", "Uncle",
                "Jeremy Zooma 11/01/05, Sally Zooma 08/03/10, Stephanie Zooma 02/01/12",
                "Jumping, dancing, and running", "none");
        generateData.generateChild(amyZooma, "Sally", "F", "Zooma", dateFormat.parse("08/03/10"), "Brian", "N",
                "Zooma", "512-546-2834", true, "Doctor Eberham", "1212 Wikersham St", "Round Rock", "Texas", "77676",
                "512-289-5345", "Archie Zooma", "512-635-0098", "Uncle",
                "Jeremy Zooma 11/01/05, Ricky Zooma 05/14/08, Stephanie Zooma 02/01/12",
                "Playing with dolls, blueberries, singing", "none");
        generateData.generateChild(amyZooma, "Stephanie", "O", "Zooma", dateFormat.parse("02/01/12"), "Brian", "N",
                "Zooma", "512-546-2834", true, "Doctor Eberham", "1212 Wikersham St", "Round Rock", "Texas", "77676",
                "512-289-5345", "Archie Zooma", "512-635-0098", "Uncle",
                "Jeremy Zooma 11/01/05, Ricky Zooma 05/14/08, Sally Zooma 08/03/10", "Juice and crackers", "none");

        MopsUser maryPoppins = generateData.generateUser("marypoppins@yahoo.com", "marypoppins", "Mary", "P",
                "Poppins", "123 Crazy Moon Street", "Austin", "Texas", "74632", "512-394-9298", "512-758-2990",
                dateFormat.parse("07/18/75"), false, null, false, true, "Church of Austin",
                "Heard about it from friends", "Burt", "0", "");
        generateData.generateChild(maryPoppins, "Stewart", "P", "Poppins", dateFormat.parse("10/20/10"), "Burt", "M",
                "Poppins", "512-767-8987", true, "Doctor Topinhosin", "4922 Ruckerfield Street", "Austin", "Texas",
                "76776", "512-443-8290", "Erthel Poppins", "398-934-2943", "Grandmother", "Timothy Poppins 09/01/06",
                "Her pony doll and eating snacks", "Dairy allergy");

        // generate AdminUser's
        generateData.generateAdminUser("RegistrarAdmin", "mops");
    }
}