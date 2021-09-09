package com.epam.tc.hw2.trello;

import com.epam.tc.hw2.trello.services.BoardsService;
import com.epam.tc.hw2.trello.services.CardsService;
import com.epam.tc.hw2.trello.services.ListsService;
import com.epam.tc.hw2.trello.services.MembersService;
import com.epam.tc.hw2.utils.UserDataProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TrelloApi {
    public static final String DOMAIN = "https://api.trello.com/1";
    public static RequestSpecification preAuthorisedRequest;

    public TrelloApi() {
    }

    public void restoreService() {
        CardsService.deleteAllCreated();
        ListsService.deleteAllCreated();
        BoardsService.deleteAllCreated();
    }

    public static RequestSpecification preAuthorisedRequest() {
        return preAuthorisedRequest = RestAssured.given()
                                                 .queryParam("key", UserDataProvider.getUserKey())
                                                 .queryParam("token", UserDataProvider.getUserToken())
                                                 .contentType("application/xml");
    }

    public Response createBoard(String name) {
        return BoardsService.createBoard(name);
    }

    public Response getBoardById(String id) {
        return BoardsService.getBoard(id);
    }

    public Response deleteBoardById(String id) {
        return BoardsService.deleteBoard(id);
    }

    public void deleteAllBoards() {
        MembersService.deleteAllRemoteBoards();
    }

    public Response createList(String idBoard, String name) {
        return ListsService.createList(idBoard, name);
    }

    public Response deleteListById(String id) {
        return ListsService.deleteList(id);
    }

    public Response createCard(String idList, String name) {
        return CardsService.createCard(idList, name);
    }
}
