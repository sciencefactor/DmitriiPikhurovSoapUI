package com.epam.tc.hw2.trello;

import com.epam.tc.hw2.trello.dto.BoardDto;
import com.epam.tc.hw2.trello.dto.ListDto;
import com.epam.tc.hw2.trello.services.BoardsService;
import com.epam.tc.hw2.trello.services.ListsService;
import com.epam.tc.hw2.trello.services.MembersService;
import com.epam.tc.hw2.trello.utils.UserDataProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TrelloApi {
    public static final String DOMAIN = "https://api.trello.com/1";
    public static RequestSpecification preAuthorisedRequest;

    public TrelloApi() {
    }

    public static RequestSpecification preAuthorisedRequest() {
        return preAuthorisedRequest = RestAssured.given()
                                                 .queryParam("key", UserDataProvider.getUserKey())
                                                 .queryParam("token", UserDataProvider.getUserToken())
                                                 .contentType("application/xml");
    }

    public BoardDto createBoard(BoardDto board) {
        return BoardsService.create(board);
    }

    public BoardDto getBoard(BoardDto board) {
        return BoardsService.get(board);
    }

    public Response deleteBoard(BoardDto board) {
        return BoardsService.delete(board);
    }

    public void deleteAllBoards() {
        MembersService.deleteAllRemoteBoards();
    }

    public ListDto createList(BoardDto board, ListDto list) {
        return ListsService.create(board, list);
    }

    public Response deleteList(ListDto list) {
        return ListsService.delete(list);
    }

    public ListDto moveList(ListDto list, BoardDto newBoard) {
        return ListsService.move(list, newBoard);
    }

}
