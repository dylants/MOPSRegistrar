package com.mops.registrar.entities;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

public class AdminUserTest {

    @Test
    public void testAdminUser() {
        AdminUser adminUser = new AdminUser();

        Assert.assertEquals("authorities must match",
                Sets.newHashSet(Authorities.ROLE_ADMIN_USER.getGrantedAuthority()), adminUser.getAuthorities());
    }

    @Test
    public void testToString() {
        AdminUser adminUser = new AdminUser();

        Assert.assertNotNull("toString must not be null", adminUser.toString());
    }
}
