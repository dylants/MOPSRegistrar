package com.mops.registrar.services.child.impl;

import org.junit.Before;

public class DatabaseChildServiceTest extends ChildServiceTestBase {

    @Before
    public void setupBefore() {
        this.childService = new DatabaseChildService();
        // the DatabaseUserService also has to have a UserRepository
        MockChildRepository mockChildRepository = new MockChildRepository();
        ((DatabaseChildService) this.childService).setChildRepository(mockChildRepository);
        super.setupBefore();
    }
}
