package com.mops.registrar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;

import com.mops.registrar.entities.Address;
import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.entities.User;
import com.mops.registrar.services.user.UserService;

/**
 * Helper class that generates {@link User}s when database population is needed
 * 
 * @author dylants
 * 
 */
public class GenerateUsers {
    private static final String contextXMLFile = ClassUtils.classPackageAsResourcePath(GenerateUsers.class)
            + "/generate-users-spring.xml";

    private UserService userService = null;

    /**
     * Default constructor (loads the spring beans)
     */
    public GenerateUsers() {
        loadSpringContext();
    }

    /**
     * Loads up the Spring context to instantiate the required beans
     */
    protected void loadSpringContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextXMLFile);
        this.userService = context.getBean(UserService.class);
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

        user.setEntityId(UUID.randomUUID().toString());

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
    public void generateAdminUser(String firstName, String lastName, String emailAddress, String password,
            String homeAddress1, String homeAddress2, String city, String state, String zipCode, String phoneNumber,
            Date dateOfBirth) {

        AdminUser adminUser = new AdminUser();
        adminUser.setFirstName(firstName);
        adminUser.setLastName(lastName);
        adminUser.setEmailAddress(emailAddress);
        adminUser.setClearTextPassword(password);

        adminUser.setEntityId(UUID.randomUUID().toString());

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

        adminUser.setRegistrationInformation(registrationInformation);

        this.userService.addUser(adminUser);
    }

    public static void main(String[] args) throws Exception {
        GenerateUsers generateUsers = new GenerateUsers();
        generateUsers.loadSpringContext();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        generateUsers.generateUser("Bob", "Jones", "bobjones@email.com", "bobjones", "94920 Street", null, "Austin",
                "Texas", "75839", "512-993-2901", dateFormat.parse("03/12/68"));
        generateUsers.generateUser("Biff", "Henderson", "biffhenderson@cbs.com", "biffhenderson", "234 Avenue A",
                "Apt 943", "New York", "New York", "11903", "939-321-9560", dateFormat.parse("01/19/49"));
        generateUsers.generateUser("Mary", "Jane", "maryjane@mars.com", "maryjane", "22932 Park Lane", null,
                "Round Rock", "Texas", "75832", "512-301-6675", dateFormat.parse("04/02/79"));
        generateUsers.generateUser("Amy", "Zooma", "amyzooma@gmail.com", "amyzooma", "1029 Cove", null, "Round Rock",
                "Texas", "77348", "512-212-0503", dateFormat.parse("10/20/81"));

        generateUsers
                .generateAdminUser("Mary", "Poppins", "marypoppins@yahoo.com", "marypoppins", "123 Crazy Moon Street",
                        null, "Austin", "Texas", "74632", "512-394-9298", dateFormat.parse("07/18/50"));
    }

}