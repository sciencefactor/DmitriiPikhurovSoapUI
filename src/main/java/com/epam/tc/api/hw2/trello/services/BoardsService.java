package com.epam.tc.api.hw2.trello.services;

import static com.epam.tc.api.hw2.trello.TrelloApi.DOMAIN;
import static com.epam.tc.api.hw2.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.api.hw2.trello.TrelloApi;
import com.epam.tc.api.hw2.trello.components.BoardEntity;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class BoardsService {

    public static final String BOARDS_ENDPOINT = "/boards";

    private static List<BoardEntity> boards = new ArrayList<>();

    public static void deleteAllCreated() {
        boards.forEach(board -> deleteBoard(board.getId()));
        boards = new ArrayList<>();
    }

    public static Response createBoard(String name) {
        Response newBoard = TrelloApi.preAuthorisedRequest()
                                     .given()
                                     .queryParam("name", name)
                                     .when()
                                     .post(DOMAIN + BOARDS_ENDPOINT);
        assertThat().response(newBoard).statusCodeIsOk().correctJsonFormat();
        boards.add(newBoard.as(BoardEntity.class));
        return newBoard;
    }

    public static Response deleteBoard(String id) {
        return TrelloApi.preAuthorisedRequest()
                        .when()
                        .delete(DOMAIN + BOARDS_ENDPOINT + "/" + id);
    }

    public static Response getBoard(String id) {
        return TrelloApi.preAuthorisedRequest()
            .when()
            .get(DOMAIN + BOARDS_ENDPOINT + "/" + id);
    }
}
