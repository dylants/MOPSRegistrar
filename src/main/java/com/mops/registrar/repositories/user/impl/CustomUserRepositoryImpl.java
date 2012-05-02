package com.mops.registrar.repositories.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.entities.RegistrationInformation;
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

    public List<MopsUser> findByFirstNameLastName(String firstName, String lastName) {
        return this.mongoTemplate.find(
                new Query(Criteria.where("firstName").is(firstName).and("lastName").is(lastName)), MopsUser.class);
    }

    @Override
    public MopsUser updateRegistrationInformation(String entityId, RegistrationInformation registrationInformation) {
        return updateElement(entityId, "registrationInformation", registrationInformation);
    }

    @Override
    public MopsUser updateUsername(String entityId, String username) {
        return updateElement(entityId, "username", username);
    }

    @Override
    public MopsUser updatePasswordHash(String entityId, String passwordHash) {
        return updateElement(entityId, "passwordHash", passwordHash);
    }

    protected MopsUser updateElement(String entityId, String elementName, Object elementValue) {
        Query findQuery = new Query(Criteria.where("_id").is(entityId));
        Update update = new Update();
        update.set(elementName, elementValue);
        WriteResult writeResult = this.mongoTemplate.upsert(findQuery, update, MopsUser.class);
        if (writeResult.getError() != null) {
            // TODO log the writeResult
            System.out.println(writeResult.getError());
        }

        MopsUser mopsUser = this.mongoTemplate.findOne(findQuery, MopsUser.class);
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