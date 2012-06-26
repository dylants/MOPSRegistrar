package com.mops.registrar.services.child.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mops.registrar.entities.Child;
import com.mops.registrar.services.child.ChildService;

public abstract class ChildServiceTestBase {
    protected static final String FIRST_NAME = "MyFirstName";
    protected static final String FIRST_NAME2 = "MyFirstName2";
    protected static final String LAST_NAME = "MyLastName";
    protected static final String LAST_NAME2 = "MyLastName2";
    protected static final Date DATE_OF_BIRTH = new GregorianCalendar(2009, 8, 4).getTime();
    protected static final Date DATE_OF_BIRTH2 = new GregorianCalendar(2010, 4, 1).getTime();

    // this is populated by the concrete class
    protected ChildService childService;

    protected Child child;
    protected Child child2;
    protected String entityId;
    protected String entityId2;

    @Before
    public void setupBefore() {
        // concrete class must populate this prior to running tests
        Assert.assertNotNull("childService must not be null, concrete implementation must populate", this.childService);

        // create the child
        this.child = new Child();
        this.child.setFirstName(FIRST_NAME);
        this.child.setLastName(LAST_NAME);
        this.child.setDateOfBirth(DATE_OF_BIRTH);

        // store this child's entity ID
        this.entityId = this.child.getEntityId();

        // add this child to the service
        this.childService.addChild(this.child);

        // create the second child
        this.child2 = new Child();
        this.child2.setFirstName(FIRST_NAME2);
        this.child2.setLastName(LAST_NAME2);
        this.child2.setDateOfBirth(DATE_OF_BIRTH2);

        // store this child's entity ID
        this.entityId2 = this.child2.getEntityId();

        // add this child to the service
        this.childService.addChild(this.child2);
    }

    @Test
    public void testFindChildByEntityId() {
        Child testChild = this.childService.findChildByEntityId(this.entityId);

        assertChildElements(testChild, this.entityId, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
    }

    @Test
    public void testFindChildren_SingleEntityId() {
        Set<String> entityIds = new HashSet<String>();
        entityIds.add(this.entityId2);
        Set<Child> children = this.childService.findChildren(entityIds);

        Assert.assertNotNull("children must not be null", children);
        Assert.assertTrue("children must contain only 1 element", children.size() == 1);

        Child testChild = (Child) children.toArray()[0];

        assertChildElements(testChild, this.entityId2, FIRST_NAME2, LAST_NAME2, DATE_OF_BIRTH2);
    }

    @Test
    public void testFindChildren_MultipleEntityId() {
        Set<String> entityIds = new HashSet<String>();
        entityIds.add(this.entityId);
        entityIds.add(this.entityId2);
        Set<Child> children = this.childService.findChildren(entityIds);

        Assert.assertNotNull("children must not be null", children);
        Assert.assertTrue("children must contain only 2 elements", children.size() == 2);

        for (Child testChild : children) {
            if (testChild.getEntityId().equals(this.entityId)) {
                assertChildElements(testChild, this.entityId, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
            } else if (testChild.getEntityId().equals(this.entityId2)) {
                assertChildElements(testChild, this.entityId2, FIRST_NAME2, LAST_NAME2, DATE_OF_BIRTH2);
            } else {
                Assert.fail("test child not recognized");
            }
        }
    }

    @Test
    public void testFindAllChildren() {
        Set<Child> children = this.childService.findAllChildren();

        Assert.assertNotNull("children must not be null", children);
        Assert.assertTrue("children must contain only 2 elements", children.size() == 2);

        for (Child testChild : children) {
            if (testChild.getEntityId().equals(this.entityId)) {
                assertChildElements(testChild, this.entityId, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
            } else if (testChild.getEntityId().equals(this.entityId2)) {
                assertChildElements(testChild, this.entityId2, FIRST_NAME2, LAST_NAME2, DATE_OF_BIRTH2);
            } else {
                Assert.fail("test child not recognized");
            }
        }
    }

    @Test
    public void testUpdateChild() {
        // original entity ID must never change
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

        assertChildElements(updatedChild, this.entityId, firstName, lastName, dateOfBirth);
        Assert.assertEquals("specialNeeds must match", specialNeedsAndInstructionsAllergyInformation,
                updatedChild.getSpecialNeedsAndInstructionsAllergyInformation());
    }

    /**
     * Verifies a certain set of elements within this {@link Child} object
     * 
     * @param child
     *            The {@link Child} object to verify
     * @param entityId
     *            The entity ID that should match the child object's entity ID
     * @param firstName
     *            The first name that should match the child object
     * @param lastName
     *            The last name that should match the child object
     * @param dateOfBirth
     *            The date of birth that should match the child object
     */
    protected void assertChildElements(Child child, String entityId, String firstName, String lastName, Date dateOfBirth) {
        Assert.assertNotNull("child must not be null", child);
        Assert.assertEquals("entityID must match", entityId, child.getEntityId());
        Assert.assertEquals("firstName must match", firstName, child.getFirstName());
        Assert.assertEquals("lastName must match", lastName, child.getLastName());
        Assert.assertEquals("dateOfBirth must match", dateOfBirth, child.getDateOfBirth());
    }
}