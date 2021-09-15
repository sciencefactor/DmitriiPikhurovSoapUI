package com.epam.tc.hw2.trello.services.dtoservices;

import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.hw2.trello.dto.BoardDto;
import com.epam.tc.hw2.trello.dto.ListDto;
import com.epam.tc.hw2.trello.services.CommonService;
import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;
import java.util.Map;

public class ListsService {

    public static final String LISTS_ENDPOINT = "/lists";

    public static ListDto create(BoardDto board, ListDto listDraft) {
        Map<String, String> params = Map.of("idBoard", board.getId(), "name", listDraft.getName());
        Response newListResponse = new CommonService()
            .makeRequest(RequestSenderOptions::post, LISTS_ENDPOINT, params);

        assertThat(newListResponse)
            .checkContentIsJson()
            .checkListHasCorrectKeys()
            .checkListDto(listDraft);
        return newListResponse.as(ListDto.class);
    }

    public static Response delete(ListDto list) {
        Map<String, String> params = Map.of("value", "true");
        String uri = LISTS_ENDPOINT + "/" + list.getId() + "/closed";
        return new CommonService()
            .makeRequest(RequestSenderOptions::put, uri, params);
    }

    public static ListDto move(ListDto list, BoardDto newBoard) {
        Map<String, String> params = Map.of("value", newBoard.getId());
        String uri = LISTS_ENDPOINT + "/" + list.getId() + "/idBoard";
        Response moveListResponse = new CommonService()
            .makeRequest(RequestSenderOptions::put, uri, params);
        return moveListResponse.as(ListDto.class);
    }
}
