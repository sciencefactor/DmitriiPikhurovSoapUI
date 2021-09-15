package com.epam.tc.hw2.trello.asserts;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

public class TrelloAssertProvider {

    private static ValidatableResponse responseToCheck;

    public static TrelloAssertProvider assertThat(Response response) {
        responseToCheck = response
            .then()
            .log().ifValidationFails(LogDetail.BODY);
        return new TrelloAssertProvider();
    }

    public TrelloAssertProvider checkContentIsJson() {
        responseToCheck
            .contentType(ContentType.JSON);
        return this;
    }

    public TrelloAssertProvider checkBoardHasCorrectKeys() {
        responseToCheck
            .body("$", hasKey("id"), "$", hasKey("name"), "$", hasKey("url"));
        return this;
    }

    public TrelloAssertProvider checkListHasCorrectKeys() {
        responseToCheck
            .body("$", hasKey("id"), "$", hasKey("name"), "$", hasKey("idBoard"), "$", hasKey("closed"));
        return this;
    }

    public TrelloAssertProvider statusCodeIsOk() {
        responseToCheck.statusCode(HttpStatus.SC_OK);
        return this;
    }

    public TrelloAssertProvider statusCodeValid() {
        responseToCheck
            .statusCode(Matchers.lessThan(300))
            .statusCode(Matchers.greaterThanOrEqualTo(200));
        return this;
    }

    public TrelloAssertProvider checkIsItClosed() {
        responseToCheck.body("closed", is(true));
        return this;
    }
}
