package com.epam.tc.api.hw9.trello.services;

import static com.epam.tc.api.hw9.trello.TrelloApi.DOMAIN;
import static com.epam.tc.api.hw9.trello.TrelloApi.preAuthorisedRequest;

import com.epam.tc.api.hw9.trello.components.ListEntity;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class ListsService {

    public static final String LISTS_ENDPOINT = "/lists";

    private static List<ListEntity> lists = new ArrayList<>();

    public static void deleteAll() {
        lists.forEach(list -> deleteList(list.getId()));
    }

    public static Response createList(String idBoard, String name) {
        System.out.println(preAuthorisedRequest);
        Response response = preAuthorisedRequest
            .when()
            .queryParam("idBoard", idBoard)
            .queryParam("name", name)
            .post(DOMAIN + LISTS_ENDPOINT);
        lists.add(response.as(ListEntity.class));
        return response;
    }

    public static Response deleteList(String id){
        return preAuthorisedRequest
            .when()
            .queryParam("value", "true")
            .put(DOMAIN + LISTS_ENDPOINT + "/" + id + "/" + "closed");
    }

}
