package com.epam.tc.hw2.trello.services.dtoservices;

import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.hw2.trello.dto.BoardDto;
import com.epam.tc.hw2.trello.services.CommonService;
import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;
import java.util.Map;

public class BoardsService {

    public static final String BOARDS_ENDPOINT = "/boards";

    public static BoardDto create(BoardDto board) {
        Map<String, String> params = Map.of("name", board.getName());
        Response newBoardResponse = new CommonService()
            .makeRequest(RequestSenderOptions::post, BOARDS_ENDPOINT, params);

        assertThat(newBoardResponse)
            .checkContentIsJson()
            .checkBoardDto(board);
        return newBoardResponse.as(BoardDto.class);
    }

    public static Response delete(BoardDto board) {
        String uri = BOARDS_ENDPOINT + "/" + board.getId();
        return new CommonService()
            .makeRequest(RequestSenderOptions::delete, uri);
    }

    public static BoardDto getResponse(BoardDto board) {
        String uri = BOARDS_ENDPOINT + "/" + board.getId();
        Response getBoardResponse = new CommonService()
            .makeRequest(RequestSenderOptions::get, uri);
        return getBoardResponse.as(BoardDto.class);
    }
}
