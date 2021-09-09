package com.epam.tc.hw2.trello.services;

import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;

import com.epam.tc.hw2.trello.TrelloApi;
import com.epam.tc.hw2.trello.dto.CardDto;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class CardsService {
    public static final String CARDS_ENDPOINT = "/cards";

    private static List<CardDto> cards = new ArrayList<>();

    public static void deleteAllCreated() {
        cards.forEach(card -> deleteCard(card.getId()));
        cards = new ArrayList<>();
    }

    public static Response createCard(String idList, String name) {
        Response newCard = TrelloApi.preAuthorisedRequest()
                                    .when()
                                    .queryParam("idList", idList)
                                    .queryParam("name", name)
                                    .post(TrelloApi.DOMAIN + CARDS_ENDPOINT);
        assertThat().response(newCard).statusCodeIsOk().correctJsonFormat();
        cards.add(newCard.as(CardDto.class));
        return newCard;
    }

    public static Response deleteCard(String id) {
        return TrelloApi.preAuthorisedRequest()
                        .when()
                        .queryParam("value", "true")
                        .delete(TrelloApi.DOMAIN + CARDS_ENDPOINT + "/" + id);
    }
}
