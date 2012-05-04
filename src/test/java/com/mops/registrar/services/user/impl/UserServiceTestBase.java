package com.mops.registrar.services.user.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.services.user.UserService;

/**
 * Base class used to test the implementations of {@link UserService}
 * 
 * @author dysmith
 * 
 */
public abstract class UserServiceTestBase {

    protected static final String MOPS_USER_ONE_USERNAME = "userOne";
    protected static final String MOPS_USER_TWO_USERNAME = "userTwo";

    protected static MopsUser mopsUserOne;
    protected static String mopsUserOneEntityId;
    protected static MopsUser mopsUserTwo;
    protected static String mopsUserTwoEntityId;

    // this is populated by the concrete class
    protected UserService userService;

    @BeforeClass
    public static void setupBeforeClass() {
        mopsUserOne = new MopsUser();
        mopsUserOne.setUsername(MOPS_USER_ONE_USERNAME);
        mopsUserOneEntityId = mopsUserOne.getEntityId();

        mopsUserTwo = new MopsUser();
        mopsUserTwo.setUsername(MOPS_USER_TWO_USERNAME);
        mopsUserTwoEntityId = mopsUserTwo.getEntityId();
    }

    @Before
    public void setupBefore() {
        // concrete class must populate this prior to running tests
        Assert.assertNotNull("userService must not be null, concrete implementation must populate", userService);

        this.userService.addUser(mopsUserOne);
        this.userService.addUser(mopsUserTwo);
    }

    @Test
    public void testGetUsers() {
        List<MopsUser> mopsUsers = this.userService.getUsers();

        Assert.assertNotNull("list must not be null", mopsUsers);
        Assert.assertNotNull("list must have two elements", mopsUsers.size() == 2);
        Assert.assertEquals("must be mopsUserOne", mopsUserOne, mopsUsers.get(0));
        Assert.assertEquals("must be mopsUserTwo", mopsUserTwo, mopsUsers.get(1));
    }

    @Test
    public void testGetUserByEntityId() {
        MopsUser mopsUser = this.userService.getUserByEntityId(mopsUserOneEntityId);

        Assert.assertNotNull("mopsUser must not be null", mopsUser);
        Assert.assertNotNull("entityId must not be null", mopsUser.getEntityId());
        Assert.assertEquals("entityId must match", mopsUserOneEntityId, mopsUser.getEntityId());
    }

    @Test
    public void testGetUserByUsername() {
        MopsUser mopsUser = this.userService.getUserByUsername(MOPS_USER_ONE_USERNAME);

        Assert.assertNotNull("mopsUser must not be null", mopsUser);
        Assert.assertNotNull("entityId must not be null", mopsUser.getEntityId());
        Assert.assertEquals("entityId must match", mopsUserOneEntityId, mopsUser.getEntityId());
        Assert.assertNotNull("username must not be null", mopsUser.getUsername());
        Assert.assertEquals("username must match", MOPS_USER_ONE_USERNAME, mopsUser.getUsername());
    }
}