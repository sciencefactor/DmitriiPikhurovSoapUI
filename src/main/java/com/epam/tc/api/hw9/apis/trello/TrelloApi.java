package com.epam.tc.api.hw9.apis.trello;


import com.epam.tc.api.hw9.apis.trello.components.TrelloBoard;
import com.epam.tc.api.hw9.apis.trello.components.TrelloWorkspace;
import com.epam.tc.api.hw9.utils.UserDataProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.testng.annotations.Test;

public class TrelloApi {

    public static final String DOMAIN = "https://api.trello.com";
    public static final String MEMBERS_ENDPOINT = "/1/members";
    public static final String USER_ENDPOINT = "/1/me";
    public static final String BOARDS_ENDPOINT = "/1/boards";

    public static RequestSpecification preAuthorisedRequest;

    public TrelloApi() {
        preAuthorisedRequest = RestAssured.given()
                                          .queryParam("key", UserDataProvider.getUserKey())
                                          .queryParam("token", UserDataProvider.getUserToken())
                                          .contentType("application/xml");
    }

    @Test
    public Response createNewBoard(String name) {
        Response response = preAuthorisedRequest
            .given()
            .queryParam("name", name)
            .when()
            .post(DOMAIN + BOARDS_ENDPOINT);
        TrelloWorkspace.addComponent(response.getBody().as(TrelloBoard.class));
        return response;
    }

    public Response deleteBoardByName(String name) {
        TrelloBoard board = TrelloWorkspace.getBoardByName(name);
        return preAuthorisedRequest
            .when()
            .delete(DOMAIN + BOARDS_ENDPOINT + "/" + board.getId());
    }

    public Response deleteBoardById(String id) {
        return preAuthorisedRequest
            .when()
            .delete(DOMAIN + BOARDS_ENDPOINT + "/" + id);
    }

}
