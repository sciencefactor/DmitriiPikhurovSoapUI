package com.epam.tc.hw2.tests;

import com.epam.tc.hw2.trello.TrelloApi;
import com.epam.tc.hw2.trello.dto.BoardDto;
import java.util.ArrayList;
import java.util.List;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class InitTest {

    protected TrelloApi trelloApi;
    protected List<BoardDto> createdBoards = new ArrayList<>();

    @BeforeSuite
    void setUp() {
        trelloApi = new TrelloApi();
    }

    @AfterMethod
    void restoreService(ITestResult result) {
        Reporter.log(String.format("Test: %s complete", result.getMethod().getMethodName()));
        createdBoards.forEach(trelloApi::deleteBoard);
        createdBoards.clear();
    }
}
