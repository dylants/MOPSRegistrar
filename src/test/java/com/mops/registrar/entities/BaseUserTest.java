package com.mops.registrar.entities;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Sets;
import com.mops.registrar.security.Authorities;

public class BaseUserTest {

    @Test
    public void testBaseUser_Default() {
        BaseUser baseUser = new TestBaseUser();

        // verify entity ID is created
        Assert.assertNotNull("entity ID must exist", baseUser.getEntityId());
        // verify the base user's default authorities
        Assert.assertEquals("authorities must match",
                Sets.newHashSet(Authorities.ROLE_ANONYMOUS.getGrantedAuthority()), baseUser.getAuthorities());
        // verify base spring security details
        Assert.assertTrue("accountNonExpired", baseUser.isAccountNonExpired());
        Assert.assertTrue("accountNonLocked", baseUser.isAccountNonLocked());
        Assert.assertTrue("credentialsNonExpired", baseUser.isCredentialsNonExpired());
        Assert.assertTrue("enabled", baseUser.isEnabled());
    }

    @Test
    public void testBaseUser_DefaultWithUsernamePasswordHash() {
        BaseUser baseUser = new TestBaseUser();

        String username = "myUsername";
        String passwordHash = "123xyz";

        baseUser.setUsername(username);
        baseUser.setPasswordHash(passwordHash);

        Assert.assertEquals("username must match", username, baseUser.getUsername());
        Assert.assertEquals("password hash must match", passwordHash, baseUser.getPasswordHash());
        Assert.assertEquals("password must match password hash", passwordHash, baseUser.getPassword());
    }

    @Test
    public void testBaseUser_WithAuthorities() {
        BaseUser baseUser = new TestBaseUser(Sets.newHashSet(Authorities.ROLE_ADMIN_USER.getGrantedAuthority()));

        // verify the base user's authorities
        Assert.assertEquals("authorities must match",
                Sets.newHashSet(Authorities.ROLE_ADMIN_USER.getGrantedAuthority()), baseUser.getAuthorities());
    }

    private class TestBaseUser extends BaseUser {
        private static final long serialVersionUID = 5373643673635100455L;

        protected TestBaseUser() {
        }

        protected TestBaseUser(Set<GrantedAuthority> authorities) {
            super(authorities);
        }
    }
}
