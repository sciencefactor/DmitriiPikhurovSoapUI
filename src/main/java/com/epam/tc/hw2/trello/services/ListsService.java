package com.epam.tc.hw2.trello.services;

import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.hw2.trello.TrelloApi;
import com.epam.tc.hw2.trello.dto.ListDto;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class ListsService {

    public static final String LISTS_ENDPOINT = "/lists";

    private static List<ListDto> lists = new ArrayList<>();

    public static void deleteAllCreated() {
        lists.forEach(list -> deleteList(list.getId()));
        lists = new ArrayList<>();
    }

    public static Response createList(String idBoard, String name) {
        Response newList = TrelloApi.preAuthorisedRequest()
                                    .when()
                                    .queryParam("idBoard", idBoard)
                                    .queryParam("name", name)
                                    .post(TrelloApi.DOMAIN + LISTS_ENDPOINT);
        assertThat().response(newList).statusCodeIsOk().correctJsonFormat();
        lists.add(newList.as(ListDto.class));
        return newList;
    }

    public static Response deleteList(String id) {
        return TrelloApi.preAuthorisedRequest()
                        .when()
                        .queryParam("value", "true")
                        .put(TrelloApi.DOMAIN + LISTS_ENDPOINT + "/" + id + "/" + "closed");
    }
}
