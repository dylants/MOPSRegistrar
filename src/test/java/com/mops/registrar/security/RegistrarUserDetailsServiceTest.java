package com.mops.registrar.security;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mops.registrar.entities.AdminUser;
import com.mops.registrar.entities.MopsUser;
import com.mops.registrar.services.admin.AdminUserService;
import com.mops.registrar.services.user.UserService;

public class RegistrarUserDetailsServiceTest {

    private static final String MOPS_USER_USERNAME = "aMOPSUsername";
    private static final String ADMIN_USER_USERNAME = "anAdminUsername";

    private static MopsUser mopsUser;
    private static AdminUser adminUser;

    private UserService userService;
    private AdminUserService adminUserService;
    private RegistrarUserDetailsService registrarUserDetailsService;

    @BeforeClass
    public static void setupBeforeClass() {
        mopsUser = EasyMock.createNiceMock(MopsUser.class);
        EasyMock.replay(mopsUser);
        adminUser = EasyMock.createNiceMock(AdminUser.class);
        EasyMock.replay(adminUser);
    }

    @Before
    public void setupBefore() {
        this.userService = EasyMock.createNiceMock(UserService.class);
        EasyMock.expect(this.userService.getUserByUsername(MOPS_USER_USERNAME)).andReturn(mopsUser).anyTimes();
        EasyMock.replay(this.userService);

        this.adminUserService = EasyMock.createNiceMock(AdminUserService.class);
        EasyMock.expect(this.adminUserService.getAdminUserByUsername(ADMIN_USER_USERNAME)).andReturn(adminUser)
                .anyTimes();
        EasyMock.replay(this.adminUserService);

        this.registrarUserDetailsService = new RegistrarUserDetailsService();
        this.registrarUserDetailsService.setUserService(this.userService);
        this.registrarUserDetailsService.setAdminUserService(this.adminUserService);
    }

    @Test
    public void testLoadByUsername_MopsUser() {
        UserDetails userDetails = this.registrarUserDetailsService.loadUserByUsername(MOPS_USER_USERNAME);

        Assert.assertNotNull("userDetails must not be null", userDetails);
        Assert.assertEquals("userDetails must match", mopsUser, userDetails);
    }

    @Test
    public void testLoadByUsername_AdminUser() {
        UserDetails userDetails = this.registrarUserDetailsService.loadUserByUsername(ADMIN_USER_USERNAME);

        Assert.assertNotNull("userDetails must not be null", userDetails);
        Assert.assertEquals("userDetails must match", adminUser, userDetails);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadByUsername_NoneFound() {
        this.registrarUserDetailsService.loadUserByUsername("biff");
    }
}