package com.mops.registrar.services.user.impl;

import org.junit.Before;

public class LocalUserServiceTest extends UserServiceTestBase {

    @Before
    public void setupBefore() {
        this.userService = new LocalUserService();
        super.setupBefore();
    }
}
