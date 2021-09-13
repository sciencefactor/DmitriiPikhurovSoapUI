package com.epam.tc.hw2.trello.services;

import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.hw2.trello.TrelloApi;
import com.epam.tc.hw2.trello.dto.BoardDto;
import io.restassured.response.Response;

public class BoardsService {

    public static final String BOARDS_ENDPOINT = "/boards";

    public static BoardDto create(BoardDto board) {
        Response newBoardResponse = TrelloApi.preAuthorisedRequest()
                                             .given()
                                             .queryParam("name", board.getName())
                                             .when()
                                             .post(TrelloApi.DOMAIN + BOARDS_ENDPOINT);
        assertThat().response(newBoardResponse).statusCodeIsOk().checkCorrectJsonFormat();
        return newBoardResponse.as(BoardDto.class);
    }

    public static Response delete(BoardDto board) {
        return TrelloApi.preAuthorisedRequest()
                        .when()
                        .delete(TrelloApi.DOMAIN + BOARDS_ENDPOINT + "/" + board.getId());
    }

    public static BoardDto get(BoardDto board) {
        Response getBoardResponse = TrelloApi.preAuthorisedRequest()
                                             .when()
                                             .get(TrelloApi.DOMAIN + BOARDS_ENDPOINT + "/" + board.getId());
        assertThat().response(getBoardResponse).statusCodeIsOk().checkCorrectJsonFormat();
        return getBoardResponse.as(BoardDto.class);
    }

}
