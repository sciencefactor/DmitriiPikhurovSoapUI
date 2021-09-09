package com.epam.tc.api.hw9.trello.services;

import static com.epam.tc.api.hw9.trello.TrelloApi.DOMAIN;
import static com.epam.tc.api.hw9.trello.TrelloApi.preAuthorisedRequest;

import com.epam.tc.api.hw9.trello.components.BoardEntity;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class BoardsService {

    public static final String BOARDS_ENDPOINT = "/boards";

    private static List<BoardEntity> boards = new ArrayList<>();

    public static void deleteAll() {
        boards.forEach(board -> deleteBoard(board.getId()));
    }

    public static Response createBoard(String name) {
        Response response = preAuthorisedRequest
            .given()
            .queryParam("name", name)
            .when()
            .post(DOMAIN + BOARDS_ENDPOINT);
        boards.add(response.as(BoardEntity.class));
        return response;
    }

    public static Response deleteBoard(String id) {
        return preAuthorisedRequest
            .when()
            .delete(DOMAIN + BOARDS_ENDPOINT + "/" + id);
    }

}
