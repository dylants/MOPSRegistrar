package com.mops.registrar.repositories.child.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mops.registrar.entities.Child;
import com.mops.registrar.repositories.child.CustomChildRepository;

/**
 * The default implementation of {@link CustomChildRepository}
 * 
 * @author dylants
 * 
 */
public class CustomChildRepositoryImpl implements CustomChildRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Set<Child> findAllChildrenByEntityId(Set<String> entityIds) {
        Query findQuery = new Query(Criteria.where("_id").in(entityIds));
        List<Child> childList =  this.mongoTemplate.find(findQuery, Child.class);
        return new HashSet<Child>(childList);
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