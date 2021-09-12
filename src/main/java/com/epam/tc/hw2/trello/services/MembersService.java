package com.epam.tc.hw2.trello.services;

import static com.epam.tc.hw2.trello.TrelloApi.DOMAIN;
import static com.epam.tc.hw2.trello.services.BoardsService.BOARDS_ENDPOINT;

import com.epam.tc.hw2.trello.TrelloApi;
import com.epam.tc.hw2.trello.dto.BoardDto;
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
        BoardDto[] allBoards = getMemberBoards().jsonPath().getObject("$", BoardDto[].class);
        Arrays.stream(allBoards).forEach(BoardsService::delete);
    }
}
