package com.mops.registrar.repositories.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

    /**
     * Updates an element owned by the object with <code>entityId</code>, specified by <code>elementName</code> with the
     * data found in <code>elementValue</code>.
     * 
     * @param entityId
     *            The entity ID of the object to update
     * @param elementName
     *            The name of the element to update
     * @param elementValue
     *            The value to update in the element
     * @return The updated {@link MopsUser}
     */
    protected MopsUser updateElement(String entityId, String elementName, Object elementValue) {
        Query findQuery = new Query(Criteria.where("_id").is(entityId));
        Update update = new Update();
        update.set(elementName, elementValue);
        MopsUser mopsUser = this.mongoTemplate.findAndModify(findQuery, update, MopsUser.class);
        return mopsUser;
    }

    @Override
    public MopsUser addChildEntityId(String mopsUserEntityId, String childEntityId) {
        return pushElement(mopsUserEntityId, "childrenEntityIds", childEntityId);
    }

    /**
     * Using the MongoDB $push operation, this adds the <code>elementValue</code> to the existing (or creates a new)
     * <code>elementName</code>, performing the operations on a {@link MopsUser} specified by the <code>entityId</code>.
     * 
     * @param entityId
     *            The entity ID of the {@link MopsUser}
     * @param elementName
     *            The name of the element to push
     * @param elementValue
     *            The value of the element to push
     * @return The updated {@link MopsUser}
     */
    protected MopsUser pushElement(String entityId, String elementName, Object elementValue) {
        Query findQuery = new Query(Criteria.where("_id").is(entityId));
        Update update = new Update();
        update.push(elementName, elementValue);
        MopsUser mopsUser = this.mongoTemplate.findAndModify(findQuery, update, MopsUser.class);
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