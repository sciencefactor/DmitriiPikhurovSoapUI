package com.epam.tc.api.hw9.trello.services;

import static com.epam.tc.api.hw9.trello.TrelloApi.DOMAIN;
import static com.epam.tc.api.hw9.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.api.hw9.trello.TrelloApi;
import com.epam.tc.api.hw9.trello.components.CardEntity;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class CardsService {
    public static final String CARDS_ENDPOINT = "/cards";

    private static List<CardEntity> cards = new ArrayList<>();

    public static void deleteAllCreated() {
        cards.forEach(card -> deleteCard(card.getId()));
        cards = new ArrayList<>();
    }

    public static Response createCard(String idList, String name) {
        Response newCard = TrelloApi.preAuthorisedRequest()
                                    .when()
                                    .queryParam("idList", idList)
                                    .queryParam("name", name)
                                    .post(DOMAIN + CARDS_ENDPOINT);
        assertThat().response(newCard).statusCodeIsOk().correctJsonFormat();
        cards.add(newCard.as(CardEntity.class));
        return newCard;
    }

    public static Response deleteCard(String id) {
        return TrelloApi.preAuthorisedRequest()
                        .when()
                        .queryParam("value", "true")
                        .delete(DOMAIN + CARDS_ENDPOINT + "/" + id);
    }
}
