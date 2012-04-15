package com.mops.registrar.repositories.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mops.registrar.elements.user.User;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {

    /**
     * Locates a {@link User} by it's unique <code>userName</code>
     * 
     * @param userName
     *            The {@link User} user name
     * @return The {@link User} if found, else {@literal null}
     */
    public User findByUserName(String userName);
}
