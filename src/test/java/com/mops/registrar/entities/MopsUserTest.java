package com.mops.registrar.entities;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

public class MopsUserTest {

    @Test
    public void testMopsUser_Default() {
        MopsUser mopsUser = new MopsUser();

        Assert.assertEquals("authorities must match",
                Sets.newHashSet(Authorities.ROLE_REGISTERED_USER.getGrantedAuthority()), mopsUser.getAuthorities());
        Assert.assertNotNull("registration information must not be null", mopsUser.getRegistrationInformation());
        Assert.assertNotNull("children must not be null", mopsUser.getChildren());
    }

    @Test
    public void testComparable() {
        MopsUser mopsUser1 = new MopsUser();
        MopsUser mopsUser2 = new MopsUser();

        // set the last names to something that can be sorted
        mopsUser1.getRegistrationInformation().setLastName("Johnson");
        mopsUser2.getRegistrationInformation().setLastName("Henderson");

        Assert.assertTrue("1 should be after 2", mopsUser1.compareTo(mopsUser2) > 0);
        Assert.assertTrue("2 should be before 1", mopsUser2.compareTo(mopsUser1) < 0);
    }

    @Test
    public void testComparableWithNull() {
        MopsUser mopsUser1 = new MopsUser();
        MopsUser mopsUser2 = new MopsUser();

        // do not set the last names

        Assert.assertTrue("1 should be equal to 2", mopsUser1.compareTo(mopsUser2) == 0);
        Assert.assertTrue("2 should be equal to 1", mopsUser2.compareTo(mopsUser1) == 0);
    }

    @Test
    public void testToString() {
        MopsUser mopsUser = new MopsUser();

        Assert.assertNotNull("toString must not be null", mopsUser.toString());
    }
}