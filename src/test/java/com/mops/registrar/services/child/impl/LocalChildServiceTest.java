package com.mops.registrar.services.child.impl;

import org.junit.Before;

public class LocalChildServiceTest extends ChildServiceTestBase {

    @Before
    public void setupBefore() {
        this.childService = new LocalChildService();
        super.setupBefore();
    }
}