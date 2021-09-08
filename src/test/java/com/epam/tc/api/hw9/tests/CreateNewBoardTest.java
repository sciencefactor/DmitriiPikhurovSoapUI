package com.epam.tc.api.hw9.tests;

import com.epam.tc.api.hw9.data.TestDataProviders;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateNewBoardTest extends InitTest {

    @Test(dataProvider = "boardName",
          dataProviderClass = TestDataProviders.class,
          description = "Service should create new board")
    void createNewBoard(String name) {
        Response response = apiUnderTest.createNewBoard(name);
        System.out.println(response.getBody().asString());
        assertsProvider.responseToCheck(response)
                       .correctUserKey()
                       .and()
                       .correctUserToken()
                       .statusCodeShouldBeOk();
    }

    @Test
    void deleteBoard() {
        Response response = apiUnderTest.deleteBoardByName("RedBoard");
        System.out.println(response.getBody().asString());
        assertsProvider.responseToCheck(response)
                       .correctUserKey()
                       .and()
                       .correctUserToken()
                       .statusCodeShouldBeOk();
    }
}
