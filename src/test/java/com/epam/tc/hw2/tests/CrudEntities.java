package com.epam.tc.hw2.tests;

import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;
import static org.testng.Assert.assertEquals;

import com.epam.tc.hw2.data.TestDataProviders;
import com.epam.tc.hw2.trello.dto.BoardDto;
import com.epam.tc.hw2.trello.dto.ListDto;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CrudEntities extends InitTest {

    @Test(dataProvider = "boardName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> get board -> compare")
    void serviceShouldCreateNewBoard(String name) {
        var boardDraft = BoardDto.builder().name(name).build();
        var remoteBoard = apiUnderTest.createBoard(boardDraft);
        createdBoards.add(remoteBoard);
        var getBoard = apiUnderTest.getBoard(remoteBoard);
        assertEquals(getBoard, remoteBoard);
        Reporter.log( "Test: serviceShouldCreateNewBoard complete", true );
    }

    @Test(dataProvider = "boardName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> delete board -> check status")
    void serviceShouldDeleteBoard(String name) {
        var boardDraft = BoardDto.builder().name(name).build();
        var remoteBoard = apiUnderTest.createBoard(boardDraft);
        createdBoards.add(remoteBoard);
        Response deleteBoard = apiUnderTest.deleteBoard(remoteBoard);
        assertThat().response(deleteBoard).statusCodeIsOk();
        Reporter.log( "Test: serviceShouldDeleteBoard complete", true );
    }

    @Test(dataProvider = "boardNameListName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> create list -> delete board -> check status")
    void serviceShouldDeleteBoardWithList(String boardName, String listName) {
        var remoteBoard = apiUnderTest.createBoard(BoardDto.builder().name(boardName).build());
        createdBoards.add(remoteBoard);
        apiUnderTest.createList(remoteBoard, ListDto.builder().name(listName).build());
        Response deleteBoard = apiUnderTest.deleteBoard(remoteBoard);
        assertThat().response(deleteBoard).statusCodeIsOk();
        Reporter.log( "Test: serviceShouldDeleteBoardWithList complete", true );
    }

    @Test(dataProvider = "boardNameListName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> create list -> delete list -> delete same list again -> check if closed")
    void serviceShouldDeleteList(String boardName, String listName) {
        var remoteBoard = apiUnderTest.createBoard(BoardDto.builder().name(boardName).build());
        createdBoards.add(remoteBoard);
        var remoteList = apiUnderTest.createList(remoteBoard, ListDto.builder().name(listName).build());
        Response deleteList = apiUnderTest.deleteList(remoteList);
        assertThat().response(deleteList).isClosed();
        Response deleteListRepeat = apiUnderTest.deleteList(remoteList);
        assertThat().response(deleteListRepeat).isClosed();
        Reporter.log( "Test: serviceShouldDeleteList complete", true );
    }

    @Test(dataProvider = "boardsNameListName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> create board -> create list -> "
              + "move list from one board to another -> compare lists before and after")
    void serviceShouldCreateCard(String sourceBoardName, String targetBoardName, String listName) {
        var remoteSource = apiUnderTest.createBoard(BoardDto.builder().name(sourceBoardName).build());
        createdBoards.add(remoteSource);
        var remoteTarget = apiUnderTest.createBoard(BoardDto.builder().name(targetBoardName).build());
        createdBoards.add(remoteTarget);
        var remoteList = apiUnderTest.createList(remoteSource, ListDto.builder().name(listName).build());
        var movedList = apiUnderTest.moveList(remoteList, remoteTarget);
        assertEquals(movedList.getName(), remoteList.getName());
        Reporter.log( "Test: serviceShouldCreateCard complete", true );
    }
}
