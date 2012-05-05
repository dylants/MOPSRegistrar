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
     * @return The generated {@link MopsUser}
     */
    public MopsUser generateUser(String emailAddress, String password, String firstName, String middleInitial,
            String lastName, String streetAddress, String city, String state, String zipCode, String homePhoneNumber,
            String cellPhoneNumber, Date dateOfBirth) {

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
     * @return The generated {@link Child}
     */
    public Child generateChild(MopsUser mopsUser, String firstName, String middleInitial, String lastName,
            Date dateOfBirth, String fatherFirstName, String fatherMiddleInitial, String fatherLastName,
            String fatherEmergencyContactPhoneNumber, boolean fatherLiveAtHome, String doctorName,
            String doctorStreetAddress, String doctorCity, String doctorState, String doctorZipCode,
            String doctorPhoneNumber) {

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

        // generate User's
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        MopsUser janeDoe = generateData.generateUser("janedoe@email.com", "janedoe", "Jane", "Q", "Doe",
                "94920 Street", "Austin", "Texas", "75839", "512-993-2901", "512-585-5993",
                dateFormat.parse("03/12/81"));
        MopsUser sueHenderson = generateData.generateUser("suehenderson@one.com", "suehenderson", "Sue", "T",
                "Henderson", "234 Avenue A, Apt 943", "New York", "New York", "11903", "939-321-9560", "213-300-2001",
                dateFormat.parse("01/19/71"));
        MopsUser maryJane = generateData.generateUser("maryjane@mars.com", "maryjane", "Mary", "", "Jane",
                "22932 Park Lane", "Round Rock", "Texas", "75832", "512-301-6675", "512-301-6675",
                dateFormat.parse("04/02/79"));
        MopsUser amyZooma = generateData.generateUser("amyzooma@gmail.com", "amyzooma", "Amy", "M", "Zooma",
                "1029 Cove", "Round Rock", "Texas", "77348", "512-212-0503", "512-310-4992",
                dateFormat.parse("10/20/81"));
        MopsUser maryPoppins = generateData.generateUser("marypoppins@yahoo.com", "marypoppins", "Mary", "P",
                "Poppins", "123 Crazy Moon Street", "Austin", "Texas", "74632", "512-394-9298", "512-758-2990",
                dateFormat.parse("07/18/75"));

        // generate AdminUser's
        generateData.generateAdminUser("RegistrarAdmin", "mops");

        // generate Child entities
        generateData.generateChild(janeDoe, "Billy", "T", "Doe", dateFormat.parse("08/10/11"), "Bill", "G", "Doe",
                "512-932-1239", true, "Doctor Hamilton", "9209 Doctor Street", "Austin", "Texas", "78787",
                "512-230-2991");
        generateData.generateChild(janeDoe, "Suzy", "Q", "Doe", dateFormat.parse("09/08/08"), "Bill", "G", "Doe",
                "512-932-1239", true, "Doctor Hamilton", "9209 Doctor Street", "Austin", "Texas", "78787",
                "512-230-2991");
    }
}