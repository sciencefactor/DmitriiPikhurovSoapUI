package com.epam.tc.api.hw2.tests;


import com.epam.tc.api.hw2.trello.TrelloApi;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitTest {

    protected TrelloApi apiUnderTest;

    @BeforeSuite
    void setUp() {
        apiUnderTest = new TrelloApi();
    }

    @AfterMethod
    void restoreService() {
        apiUnderTest.restoreService();
    }

    @AfterSuite
    void deleteEverything() {
        apiUnderTest.deleteAllBoards();
    }
}
