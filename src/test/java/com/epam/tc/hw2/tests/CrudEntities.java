package com.epam.tc.hw2.tests;

import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;
import static org.testng.Assert.assertEquals;

import com.epam.tc.hw2.data.TestDataProviders;
import com.epam.tc.hw2.trello.dto.BoardDto;
import com.epam.tc.hw2.trello.dto.ListDto;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrudEntities extends InitTest {

    private BoardDto testBoard;

    @BeforeMethod
    void createNewBoardPrecondition() {
        String name = RandomStringUtils.randomAlphabetic(10);
        BoardDto boardDraft = BoardDto.builder().name(name).closed(false).build();
        testBoard = trelloApi.createBoard(boardDraft);
        createdBoards.add(testBoard);
    }

    @Test(description = "Create board -> get board -> compare")
    void serviceShouldCreateNewBoard() {
        BoardDto getBoard = trelloApi.getBoard(testBoard);
        assertEquals(getBoard, testBoard);
    }

    @Test(description = "Create board -> delete board -> check status")
    void serviceShouldDeleteBoard() {
        Response deleteBoard = trelloApi.deleteBoard(testBoard);
        createdBoards.remove(testBoard);

        // Assert that board was created successful
        assertThat(deleteBoard).statusCodeIsOk();
    }

    @Test(dataProvider = "listName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> create list -> delete board -> check status")
    void serviceShouldDeleteBoardWithList(String listName) {
        trelloApi.createList(testBoard, ListDto.builder().name(listName).build());
        Response deleteBoard = trelloApi.deleteBoard(testBoard);
        createdBoards.remove(testBoard);

        // Assert that board was delete successful
        assertThat(deleteBoard).statusCodeIsOk();
    }

    @Test(dataProvider = "listName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> create list -> delete list -> delete same list again -> check if closed")
    void serviceShouldDeleteList(String listName) {
        ListDto remoteList = trelloApi.createList(testBoard, ListDto.builder().name(listName).build());
        Response deleteList = trelloApi.deleteList(remoteList);

        // Assert that first deletion of list was successful
        assertThat(deleteList).checkIsItClosed();
        Response deleteListRepeat = trelloApi.deleteList(remoteList);

        // Assert that repeated deletion of list was successful again
        assertThat(deleteListRepeat).checkIsItClosed();
    }

    @Test(dataProvider = "boardNameListName",
          dataProviderClass = TestDataProviders.class,
          description = "Create board -> create board -> create list -> "
              + "move list from one board to another -> compare lists before and after")
    void serviceShouldMoveCard(String boardName, String listName) {
        ListDto remoteList = trelloApi.createList(testBoard, ListDto.builder().name(listName).build());
        BoardDto remoteTarget = trelloApi.createBoard(BoardDto.builder().name(boardName).build());
        createdBoards.add(remoteTarget);
        ListDto movedList = trelloApi.moveList(remoteList, remoteTarget);

        // Assert that card was moved successful
        assertEquals(movedList.getName(), remoteList.getName());
    }
}
