package com.mops.registrar.services.user.impl;

import org.junit.Before;

public class DatabaseUserServiceTest extends UserServiceTestBase {

    @Before
    public void setupBefore() {
        this.userService = new DatabaseUserService();
        // the DatabaseUserService also has to have a UserRepository
        MockUserRepository mockUserRepository = new MockUserRepository();
        ((DatabaseUserService) this.userService).setUserRepository(mockUserRepository);
        super.setupBefore();
    }
}
