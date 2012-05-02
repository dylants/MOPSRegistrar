package com.mops.registrar.repositories.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.repositories.admin.CustomAdminUserRepository;

/**
 * Default implementation of {@link CustomAdminUserRepository}
 * 
 * @author dysmith
 * 
 */
public class CustomAdminUserRepositoryImpl implements CustomAdminUserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public AdminUser updateUsername(String entityId, String username) {
        return updateElement(entityId, "username", username);
    }

    @Override
    public AdminUser updatePasswordHash(String entityId, String passwordHash) {
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
     * @return The updated {@link AdminUser}
     */
    protected AdminUser updateElement(String entityId, String elementName, Object elementValue) {
        Query findQuery = new Query(Criteria.where("_id").is(entityId));
        Update update = new Update();
        update.set(elementName, elementValue);
        WriteResult writeResult = this.mongoTemplate.upsert(findQuery, update, AdminUser.class);
        if (writeResult.getError() != null) {
            // TODO log the writeResult
            System.out.println(writeResult.getError());
        }

        AdminUser adminUser = this.mongoTemplate.findOne(findQuery, AdminUser.class);
        return adminUser;
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