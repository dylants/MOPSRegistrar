package com.mops.registrar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;

import com.mops.registrar.entities.Address;
import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.MOPSUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.services.admin.AdminUserService;
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
    }

    /**
     * Generates a new {@link MOPSUser} and adds it to the database
     * 
     * @param firstName
     * @param middleInitial
     * @param lastName
     * @param emailAddress
     * @param password
     * @param streetAddress
     * @param city
     * @param state
     * @param zipCode
     * @param homePhoneNumber
     * @param cellPhoneNumber
     * @param dateOfBirth
     */
    public void generateUser(String firstName, String middleInitial, String lastName, String emailAddress,
            String password, String homeAddress, String city, String state, String zipCode, String homePhoneNumber,
            String cellPhoneNumber, Date dateOfBirth) {

        MOPSUser mopsUser = new MOPSUser();
        mopsUser.setUsername(emailAddress);
        mopsUser.setClearTextPassword(password);

        Address address = new Address();
        address.setStreetAddress(homeAddress);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);

        RegistrationInformation registrationInformation = new RegistrationInformation();
        registrationInformation.setFirstName(firstName);
        registrationInformation.setMiddleInitial(middleInitial);
        registrationInformation.setLastName(lastName);
        registrationInformation.setEmailAddress(emailAddress);
        registrationInformation.setAddress(address);
        registrationInformation.setHomePhoneNumber(homePhoneNumber);
        registrationInformation.setCellPhoneNumber(cellPhoneNumber);
        registrationInformation.setDateOfBirth(dateOfBirth);

        mopsUser.setRegistrationInformation(registrationInformation);

        this.userService.addUser(mopsUser);
    }

    /**
     * Generates a new {@link AdminUser} and adds it to the database
     * 
     * @param username
     * @param password
     */
    public void generateAdminUser(String username, String password) {

        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        adminUser.setClearTextPassword(password);

        this.adminUserService.addAdminUser(adminUser);
    }

    public static void main(String[] args) throws Exception {
        GenerateData generateData = new GenerateData();
        generateData.loadSpringContext();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        generateData.generateUser("Jane", "Q", "Doe", "janedoe@email.com", "janedoe", "94920 Street", "Austin",
                "Texas", "75839", "512-993-2901", "512-585-5993", dateFormat.parse("03/12/81"));
        generateData.generateUser("Sue", "T", "Henderson", "suehenderson@one.com", "suehenderson",
                "234 Avenue A, Apt 943", "New York", "New York", "11903", "939-321-9560", "213-300-2001",
                dateFormat.parse("01/19/71"));
        generateData.generateUser("Mary", "", "Jane", "maryjane@mars.com", "maryjane", "22932 Park Lane", "Round Rock",
                "Texas", "75832", "512-301-6675", "512-301-6675", dateFormat.parse("04/02/79"));
        generateData.generateUser("Amy", "M", "Zooma", "amyzooma@gmail.com", "amyzooma", "1029 Cove", "Round Rock",
                "Texas", "77348", "512-212-0503", "512-310-4992", dateFormat.parse("10/20/81"));
        generateData.generateUser("Mary", "P", "Poppins", "marypoppins@yahoo.com", "marypoppins",
                "123 Crazy Moon Street", "Austin", "Texas", "74632", "512-394-9298", "512-758-2990",
                dateFormat.parse("07/18/75"));

        generateData.generateAdminUser("RegistrarAdmin", "mops");
    }

}