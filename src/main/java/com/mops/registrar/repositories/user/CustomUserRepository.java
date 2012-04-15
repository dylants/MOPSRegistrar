package com.mops.registrar.repositories.user;

import java.util.List;

import com.mops.registrar.elements.user.User;

public interface CustomUserRepository {

    public List<User> findByFirstNameLastName(String firstName, String lastName);

}
