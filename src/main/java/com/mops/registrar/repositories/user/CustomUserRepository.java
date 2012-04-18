package com.mops.registrar.repositories.user;

import java.util.List;

import com.mops.registrar.entities.user.User;

/**
 * A custom user repository used to define custom queries.
 * 
 * @author dylants
 * 
 */
public interface CustomUserRepository {

    /**
     * Finds a {@link User} by first name and last name
     * 
     * @param firstName
     *            The {@link User}'s first name
     * @param lastName
     *            The {@link User}'s last name
     * @return The resulting {@link List} of {@link User}s
     */
    public List<User> findByFirstNameLastName(String firstName, String lastName);

}
