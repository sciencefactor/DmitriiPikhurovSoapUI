package com.epam.tc.api.hw9.tests;

import com.epam.tc.api.hw9.data.TestDataProviders;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateNewBoardTest extends InitTest {

    @Test(dataProvider = "boardName",
          dataProviderClass = TestDataProviders.class,
          description = "Service should create new board")
    void createNewBoard(String name) {
        Response response = apiUnderTest.createBoard(name);
        System.out.println(response.getBody().asString());
        assertsProvider.responseToCheck(response)
                       .correctUserKey()
                       .and()
                       .correctUserToken()
                       .statusCodeShouldBeOk();
    }


    @Test(dataProvider = "provideBoardNameListName",
          dataProviderClass = TestDataProviders.class,
          description = "Service should create new list on board via boardID")
    void createNewList(String boardName, String name) {
        Response response = apiUnderTest.createBoard(boardName);
        apiUnderTest.createList(response.jsonPath().get("id"), name);
        assertsProvider.responseToCheck(response)
                       .correctUserKey()
                       .and()
                       .correctUserToken()
                       .statusCodeShouldBeOk();
    }

    @Test
    void clear() {
        apiUnderTest.deleteAllBoards();
    }

}
