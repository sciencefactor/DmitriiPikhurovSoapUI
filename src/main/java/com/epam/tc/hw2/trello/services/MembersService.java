package com.epam.tc.hw2.trello.services;

import static com.epam.tc.hw2.trello.services.BoardsService.BOARDS_ENDPOINT;

import com.epam.tc.hw2.trello.dto.BoardDto;
import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;
import java.util.Arrays;

public class MembersService {

    public static final String MEMBERS_ENDPOINT = "/members";
    public static final String ME_ENDPOINT = "/me";

    public static Response getMemberBoards() {
        String uri = MEMBERS_ENDPOINT + ME_ENDPOINT + BOARDS_ENDPOINT;
        return new CommonService().makeRequest(RequestSenderOptions::get, uri);
    }

    public static void deleteAllRemoteBoards() {
        BoardDto[] allBoards = getMemberBoards().jsonPath().getObject("$", BoardDto[].class);
        Arrays.stream(allBoards).forEach(BoardsService::delete);
    }


}
