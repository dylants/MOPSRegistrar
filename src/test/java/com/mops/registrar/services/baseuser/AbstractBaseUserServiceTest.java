package com.mops.registrar.services.baseuser;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.mops.registrar.entities.BaseUser;

public class AbstractBaseUserServiceTest {

    private static final String PASSWORD = "secr3t";
    private static final String INVALID_PASSWORD = "hack3r";
    private static final String PASSWORD_HASH = "83492bklj239";
    private static final Object SALT = "salt";

    private static PasswordEncoder passwordEncoder;
    private static SaltSource saltSource;
    private static BaseUser baseUser;

    private AbstractBaseUserService abstractBaseUserService = null;

    @BeforeClass
    public static void setupBeforeClass() {
        // BaseUser
        baseUser = EasyMock.createNiceMock(BaseUser.class);
        EasyMock.expect(baseUser.getPasswordHash()).andReturn(PASSWORD_HASH).anyTimes();
        EasyMock.replay(baseUser);

        // PasswordEncoder
        passwordEncoder = EasyMock.createNiceMock(PasswordEncoder.class);
        // setup valid and invalid passwords
        EasyMock.expect(
                passwordEncoder.isPasswordValid(EasyMock.eq(PASSWORD_HASH), EasyMock.eq(PASSWORD), EasyMock.eq(SALT)))
                .andReturn(true).anyTimes();
        EasyMock.expect(
                passwordEncoder.isPasswordValid(EasyMock.eq(PASSWORD_HASH), EasyMock.eq(INVALID_PASSWORD),
                        EasyMock.eq(SALT))).andReturn(false).anyTimes();
        // setup password hash
        EasyMock.expect(passwordEncoder.encodePassword(PASSWORD, SALT)).andReturn(PASSWORD_HASH).anyTimes();
        EasyMock.replay(passwordEncoder);

        // SaltSource
        saltSource = EasyMock.createNiceMock(SaltSource.class);
        EasyMock.expect(saltSource.getSalt(baseUser)).andReturn(SALT).anyTimes();
        EasyMock.replay(saltSource);
    }

    @Before
    public void setupBefore() {
        this.abstractBaseUserService = new TestBaseUserService();
        this.abstractBaseUserService.setPasswordEncoder(passwordEncoder);
        this.abstractBaseUserService.setSaltSource(saltSource);
    }

    @Test
    public void testVerifyBaseUserPassword_validPassword() {
        boolean valid = this.abstractBaseUserService.verifyBaseUserPassword(PASSWORD, baseUser);
        Assert.assertTrue("password must be valid", valid);
    }

    @Test
    public void testVerifyBaseUserPassword_invalidPassword() {
        boolean valid = this.abstractBaseUserService.verifyBaseUserPassword(INVALID_PASSWORD, baseUser);
        Assert.assertFalse("password must be invalid", valid);
    }

    @Test
    public void testGeneratePasswordHash() {
        String passwordHash = this.abstractBaseUserService.generatePasswordHash(PASSWORD, baseUser);
        Assert.assertEquals("password hash must match", PASSWORD_HASH, passwordHash);
    }

    @Test
    public void testGeneratePasswordHash_DifferentPassword() {
        String passwordHash = this.abstractBaseUserService.generatePasswordHash(INVALID_PASSWORD, baseUser);
        Assert.assertNotSame("password hash must NOT match", PASSWORD_HASH, passwordHash);
    }

    @Test
    public void testGeneratePasswordHash_NullPassword() {
        String passwordHash = this.abstractBaseUserService.generatePasswordHash(null, baseUser);
        Assert.assertNull("password hash must be null", passwordHash);
    }

    private class TestBaseUserService extends AbstractBaseUserService {
        // empty...
    }
}
