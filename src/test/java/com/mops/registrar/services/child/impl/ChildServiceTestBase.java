package com.mops.registrar.services.child.impl;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mops.registrar.entities.Child;
import com.mops.registrar.services.child.ChildService;

public abstract class ChildServiceTestBase {
    protected static final String FIRST_NAME = "MyFirstName";
    protected static final String LAST_NAME = "MyLastName";
    protected static final Date DATE_OF_BIRTH = new GregorianCalendar(2009, 8, 4).getTime();

    // this is populated by the concrete class
    protected ChildService childService;

    protected Child child;

    @Before
    public void setupBefore() {
        // concrete class must populate this prior to running tests
        Assert.assertNotNull("childService must not be null, concrete implementation must populate", this.childService);

        this.child = new Child();
        this.child.setFirstName(FIRST_NAME);
        this.child.setLastName(LAST_NAME);
        this.child.setDateOfBirth(DATE_OF_BIRTH);

        this.childService.addChild(this.child);
    }

    @Test
    public void testUpdateChild() {
        // original entity ID must never change
        String entityId = this.child.getEntityId();
        String firstName = "Biff";
        String lastName = "Jones";
        Date dateOfBirth = new GregorianCalendar(2010, 10, 14).getTime();
        String specialNeedsAndInstructionsAllergyInformation = "peanut allergy";

        Child newChild = new Child();
        newChild.setFirstName(firstName);
        newChild.setLastName(lastName);
        newChild.setDateOfBirth(dateOfBirth);
        newChild.setSpecialNeedsAndInstructionsAllergyInformation(specialNeedsAndInstructionsAllergyInformation);

        Child updatedChild = this.childService.updateChild(this.child.getEntityId(), newChild);

        Assert.assertEquals("entityID must match", entityId, updatedChild.getEntityId());
        Assert.assertEquals("firstName must match", firstName, updatedChild.getFirstName());
        Assert.assertEquals("lastName must match", lastName, updatedChild.getLastName());
        Assert.assertEquals("dateOfBirth must match", dateOfBirth, updatedChild.getDateOfBirth());
        Assert.assertEquals("specialNeeds must match", specialNeedsAndInstructionsAllergyInformation,
                updatedChild.getSpecialNeedsAndInstructionsAllergyInformation());
    }
}