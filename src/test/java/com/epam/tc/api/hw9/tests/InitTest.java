package com.epam.tc.api.hw9.tests;

import com.epam.tc.api.hw9.asserts.trello.TrelloAssertProvider;
import com.epam.tc.api.hw9.trello.TrelloApi;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitTest {

    protected TrelloApi apiUnderTest;
    protected TrelloAssertProvider assertsProvider;

    @BeforeSuite
    void setUp() {
        apiUnderTest = new TrelloApi();
        assertsProvider = new TrelloAssertProvider();
    }

//    @AfterMethod
//    void clearService() {
//        apiUnderTest.restoreService();
//    }
//
//    @AfterSuite
//    void deleteEverything() {
//        apiUnderTest.deleteAllBoards();
//    }
}
