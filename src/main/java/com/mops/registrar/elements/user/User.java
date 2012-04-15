package com.mops.registrar.elements.user;

import org.springframework.data.annotation.Id;

/**
 * Represents a simple user
 * 
 */
public class User {

    @Id
    private String userName = null;
    private String firstName = null;
    private String lastName = null;

    /**
     * Constructs an empty {@link User}
     */
    public User() {
    }

    /**
     * Constructs a {@link User}
     * 
     * @param userName
     *            The user's uniquely identifiable "user name"
     * @param firstName
     *            The user's first name
     * @param lastName
     *            The user's last name
     */
    public User(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userId) {
        this.userName = userId;
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

    @Override
    public String toString() {
        return "User userName: " + this.userName + " firstName: " + this.firstName + " lastName: " + this.lastName;
    }
}