package com.epam.tc.api.hw9.trello;

import com.epam.tc.api.hw9.trello.services.BoardsService;
import com.epam.tc.api.hw9.trello.services.ListsService;
import com.epam.tc.api.hw9.trello.services.MembersService;
import com.epam.tc.api.hw9.utils.UserDataProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TrelloApi {

    public static final String DOMAIN = "https://api.trello.com/1";

    public static RequestSpecification preAuthorisedRequest;

    public TrelloApi() {
        preAuthorisedRequest = RestAssured.given()
                                          .queryParam("key", UserDataProvider.getUserKey())
                                          .queryParam("token", UserDataProvider.getUserToken())
                                          .contentType("application/xml");
    }

    public void restoreService() {
        ListsService.deleteAll();
        BoardsService.deleteAll();
    }


    public void deleteAllBoards() {
        MembersService.deleteAllBoards();
    }

    public Response createList(String idBoard, String name) {
        return ListsService.createList(idBoard, name);
    }

    public Response createBoard(String name) {
        return BoardsService.createBoard(name);
    }


}
