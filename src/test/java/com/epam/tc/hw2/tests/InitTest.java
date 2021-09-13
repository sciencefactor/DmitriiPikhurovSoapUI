package com.epam.tc.hw2.tests;

import com.epam.tc.hw2.trello.TrelloApi;
import com.epam.tc.hw2.trello.dto.BoardDto;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class InitTest {

    protected TrelloApi apiUnderTest;
    protected List<BoardDto> createdBoards = new ArrayList<>();

    @BeforeSuite
    void setUp() {
        apiUnderTest = new TrelloApi();
    }

    @AfterMethod
    void restoreService() {
        createdBoards.forEach(apiUnderTest::deleteBoard);
    }
}
