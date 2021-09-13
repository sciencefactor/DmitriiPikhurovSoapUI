package com.epam.tc.hw2.trello.services;

import static com.epam.tc.hw2.trello.TrelloApi.DOMAIN;
import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.hw2.trello.TrelloApi;
import com.epam.tc.hw2.trello.dto.BoardDto;
import com.epam.tc.hw2.trello.dto.ListDto;
import io.restassured.response.Response;

public class ListsService {

    public static final String LISTS_ENDPOINT = "/lists";

    public static ListDto create(BoardDto board, ListDto list) {
        Response newListResponse = TrelloApi.preAuthorisedRequest()
                                    .when()
                                    .queryParam("idBoard", board.getId())
                                    .queryParam("name", list.getName())
                                    .post(DOMAIN + LISTS_ENDPOINT);
        assertThat().response(newListResponse).statusCodeIsOk().checkCorrectJsonFormat();
        return newListResponse.as(ListDto.class);
    }

    public static Response delete(ListDto list) {
        return TrelloApi.preAuthorisedRequest()
                        .when()
                        .queryParam("value", "true")
                        .put(DOMAIN + LISTS_ENDPOINT + "/" + list.getId() + "/closed");
    }

    public static ListDto move(ListDto list, BoardDto newBoard) {
        Response moveListResponse = TrelloApi.preAuthorisedRequest()
                 .when()
                 .queryParam("value", newBoard.getId())
                 .put(DOMAIN + LISTS_ENDPOINT + "/" + list.getId() + "/idBoard");

        assertThat().response(moveListResponse).statusCodeIsOk().checkCorrectJsonFormat();
        return moveListResponse.as(ListDto.class);
    }
}
