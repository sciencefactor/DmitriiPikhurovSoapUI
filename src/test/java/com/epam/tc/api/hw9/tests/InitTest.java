package com.epam.tc.api.hw9.tests;

import com.epam.tc.api.hw9.apis.ApiObject;
import com.epam.tc.api.hw9.apis.trello.TrelloApi;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class InitTest {

    protected ApiObject apiToTest;

    @BeforeSuite
    void setUp() {
        apiToTest = new TrelloApi();
    }

    @BeforeMethod
    void authorisePrecondition() {
        apiToTest.keepAuthorisation();
    }

    @AfterSuite
    void tearDown() {

    }
}
