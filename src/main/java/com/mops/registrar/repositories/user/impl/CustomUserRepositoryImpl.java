package com.mops.registrar.repositories.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mops.registrar.entities.user.User;
import com.mops.registrar.repositories.user.CustomUserRepository;

/**
 * Implements the {@link CustomUserRepository}
 * 
 * @author dylants
 * 
 */
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findByFirstNameLastName(String firstName, String lastName) {
        return this.mongoTemplate.find(
                new Query(Criteria.where("firstName").is(firstName).and("lastName").is(lastName)), User.class);
    }

    /**
     * @return the mongoTemplate
     */
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * @param mongoTemplate
     *            the mongoTemplate to set
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}