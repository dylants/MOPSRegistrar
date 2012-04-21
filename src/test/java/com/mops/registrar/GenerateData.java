package com.mops.registrar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;

import com.mops.registrar.entities.Address;
import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.entities.User;
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
     * Generates a new {@link User} and adds it to the database
     * 
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param password
     * @param homeAddress1
     * @param homeAddress2
     * @param city
     * @param state
     * @param zipCode
     * @param phoneNumber
     * @param dateOfBirth
     */
    public void generateUser(String firstName, String lastName, String emailAddress, String password,
            String homeAddress1, String homeAddress2, String city, String state, String zipCode, String phoneNumber,
            Date dateOfBirth) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        user.setClearTextPassword(password);

        Address address = new Address();
        address.setHomeAddress1(homeAddress1);
        address.setHomeAddress2(homeAddress2);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);

        RegistrationInformation registrationInformation = new RegistrationInformation();
        registrationInformation.setAddress(address);
        registrationInformation.setPhoneNumber(phoneNumber);
        registrationInformation.setDateOfBirth(dateOfBirth);

        user.setRegistrationInformation(registrationInformation);

        this.userService.addUser(user);
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
        generateData.generateUser("Bob", "Jones", "bobjones@email.com", "bobjones", "94920 Street", null, "Austin",
                "Texas", "75839", "512-993-2901", dateFormat.parse("03/12/68"));
        generateData.generateUser("Biff", "Henderson", "biffhenderson@cbs.com", "biffhenderson", "234 Avenue A",
                "Apt 943", "New York", "New York", "11903", "939-321-9560", dateFormat.parse("01/19/49"));
        generateData.generateUser("Mary", "Jane", "maryjane@mars.com", "maryjane", "22932 Park Lane", null,
                "Round Rock", "Texas", "75832", "512-301-6675", dateFormat.parse("04/02/79"));
        generateData.generateUser("Amy", "Zooma", "amyzooma@gmail.com", "amyzooma", "1029 Cove", null, "Round Rock",
                "Texas", "77348", "512-212-0503", dateFormat.parse("10/20/81"));
        generateData.generateUser("Mary", "Poppins", "marypoppins@yahoo.com", "marypoppins", "123 Crazy Moon Street",
                null, "Austin", "Texas", "74632", "512-394-9298", dateFormat.parse("07/18/50"));

        generateData.generateAdminUser("RegistrarAdmin", "mops");
    }

}