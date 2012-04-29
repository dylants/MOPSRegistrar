package com.mops.registrar.repositories.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.mops.registrar.entities.MOPSUser;
import com.mops.registrar.entities.RegistrationInformation;
import com.mops.registrar.repositories.user.CustomMOPSUserRepository;

/**
 * Implements the {@link CustomMOPSUserRepository}
 * 
 * @author dylants
 * 
 */
public class CustomMOPSUserRepositoryImpl implements CustomMOPSUserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<MOPSUser> findByFirstNameLastName(String firstName, String lastName) {
        return this.mongoTemplate.find(
                new Query(Criteria.where("firstName").is(firstName).and("lastName").is(lastName)), MOPSUser.class);
    }

    @Override
    public MOPSUser updateRegistrationInformation(String entityId, RegistrationInformation registrationInformation) {
        return updateElement(entityId, "registrationInformation", registrationInformation);
    }

    @Override
    public MOPSUser updateEmailAddress(String entityId, String emailAddress) {
        return updateElement(entityId, "emailAddress", emailAddress);
    }

    @Override
    public MOPSUser updatePasswordHash(String entityId, String passwordHash) {
        return updateElement(entityId, "passwordHash", passwordHash);
    }

    protected MOPSUser updateElement(String entityId, String elementName, Object elementValue) {
        Query findQuery = new Query(Criteria.where("_id").is(entityId));
        Update update = new Update();
        update.set(elementName, elementValue);
        WriteResult writeResult = this.mongoTemplate.upsert(findQuery, update, MOPSUser.class);
        if (writeResult.getError() != null) {
            // TODO log the writeResult
            System.out.println(writeResult.getError());
        }

        MOPSUser mopsUser = this.mongoTemplate.findOne(findQuery, MOPSUser.class);
        return mopsUser;
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