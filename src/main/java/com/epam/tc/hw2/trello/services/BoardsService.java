package com.epam.tc.hw2.trello.services;

import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.hw2.trello.TrelloApi;
import com.epam.tc.hw2.trello.dto.BoardDto;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class BoardsService {

    public static final String BOARDS_ENDPOINT = "/boards";

    private static List<BoardDto> boards = new ArrayList<>();

    public static void deleteAllCreated() {
        boards.forEach(board -> deleteBoard(board.getId()));
        boards = new ArrayList<>();
    }

    public static Response createBoard(String name) {
        Response newBoard = TrelloApi.preAuthorisedRequest()
                                     .given()
                                     .queryParam("name", name)
                                     .when()
                                     .post(TrelloApi.DOMAIN + BOARDS_ENDPOINT);
        assertThat().response(newBoard).statusCodeIsOk().correctJsonFormat();
        boards.add(newBoard.as(BoardDto.class));
        return newBoard;
    }

    public static Response deleteBoard(String id) {
        return TrelloApi.preAuthorisedRequest()
                        .when()
                        .delete(TrelloApi.DOMAIN + BOARDS_ENDPOINT + "/" + id);
    }

    public static Response getBoard(String id) {
        return TrelloApi.preAuthorisedRequest()
            .when()
            .get(TrelloApi.DOMAIN + BOARDS_ENDPOINT + "/" + id);
    }
}
