package com.epam.tc.api.hw2.trello.services;

import static com.epam.tc.api.hw2.trello.TrelloApi.DOMAIN;
import static com.epam.tc.api.hw2.trello.services.BoardsService.BOARDS_ENDPOINT;

import com.epam.tc.api.hw2.trello.TrelloApi;
import com.epam.tc.api.hw2.trello.components.BoardEntity;
import io.restassured.response.Response;
import java.util.Arrays;

public class MembersService {

    public static final String MEMBERS_ENDPOINT = "/members";
    public static final String ME_ENDPOINT = "/me";

    public static Response getMemberBoards() {
        return TrelloApi.preAuthorisedRequest()
            .when()
            .get(DOMAIN + MEMBERS_ENDPOINT + ME_ENDPOINT + BOARDS_ENDPOINT);
    }

    public static void deleteAllRemoteBoards() {
        BoardEntity[] allBoards = getMemberBoards().jsonPath().getObject("$", BoardEntity[].class);
        Arrays.stream(allBoards).forEach(board -> BoardsService.deleteBoard(board.getId()));
    }
}
