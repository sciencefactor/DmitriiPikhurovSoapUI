package com.epam.tc.api.hw2.tests;

import static com.epam.tc.api.hw2.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.api.hw2.data.TestDataProviders;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateAndDeleteEntities extends InitTest {

    @Test(dataProvider = "boardName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> get board -> check")
    void serviceShouldCreateNewBoard(String name) {
        String boardId = apiUnderTest.createBoard(name).jsonPath().get("id");
        var getBoard = apiUnderTest.getBoardById(boardId);
        assertThat().response(getBoard).has().correctName(name);
    }

    @Test(dataProvider = "boardName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> delete board -> check")
    void serviceShouldDeleteBoard(String name) {
        String boardId = apiUnderTest.createBoard(name).jsonPath().get("id");
        var deleteBoard = apiUnderTest.deleteBoardById(boardId);
        assertThat().response(deleteBoard).statusCodeIsOk();
    }

    @Test(dataProvider = "boardNameListName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> create list -> delete board -> check")
    void serviceShouldDeleteBoardWithList(String boardName, String listName) {
        String boardId = apiUnderTest.createBoard(boardName).jsonPath().get("id");
        apiUnderTest.createList(boardId, listName);
        Response deleteBoard = apiUnderTest.deleteBoardById(boardId);
        assertThat().response(deleteBoard).statusCodeIsOk();
    }

    @Test(dataProvider = "boardNameListName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> create list -> delete list -> delete same list again ->check")
    void serviceShouldDeleteList(String boardName, String listName) {
        String boardId = apiUnderTest.createBoard(boardName).jsonPath().get("id");
        String listId = apiUnderTest.createList(boardId, listName).jsonPath().get("id");
        Response deleteList = apiUnderTest.deleteListById(listId);
        assertThat().response(deleteList).listIsClosed();
        Response deleteListRepeat = apiUnderTest.deleteListById(listId);
        //Should fail
        assertThat().response(deleteListRepeat).invalidId();
    }

    @Test(dataProvider = "boardNameListNameCardName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> create list -> create card -> check")
    void serviceShouldCreateCard(String boardName, String listName, String cardName) {
        String boardId = apiUnderTest.createBoard(boardName).jsonPath().get("id");
        String listId = apiUnderTest.createList(boardId, listName).jsonPath().get("id");
        Response card = apiUnderTest.createCard(listId, cardName);
        assertThat().response(card).correctName(cardName);
    }



}
