package com.epam.tc.api.hw9.trello.asserts;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

public class TrelloAssertProvider {

    ValidatableResponse responseToCheck;

    public TrelloAssertProvider() {
    }

    public static TrelloAssertProvider assertThat() {
        return new TrelloAssertProvider();
    }

    public TrelloAssertProvider response(Response response) {
        responseToCheck = response.then().log().ifValidationFails(LogDetail.BODY);
        return this;
    }

    public TrelloAssertProvider and() {
        return this;
    }

    public TrelloAssertProvider has() {
        return this;
    }

    public TrelloAssertProvider correctJsonFormat() {
        responseToCheck.assertThat().contentType(ContentType.JSON);
        return this;
    }

    public TrelloAssertProvider correctId(String id) {
        responseToCheck.body("id", is(id));
        return this;
    }

    public TrelloAssertProvider statusCodeIsOk() {
        responseToCheck.statusCode(HttpStatus.SC_OK);
        return this;
    }

    public TrelloAssertProvider listIsClosed() {
        responseToCheck.body("closed", is(true));
        return this;
    }

    public TrelloAssertProvider invalidId() {
        responseToCheck.body(equalTo("invalid id"));
        return this;
    }

    public TrelloAssertProvider validUserKey() {
        responseToCheck.body(not(equalTo("invalid key")));
        return this;
    }

    public TrelloAssertProvider correctName(String name) {
        responseToCheck.body("name", is(name));
        return this;
    }

    public TrelloAssertProvider validUserToken() {
        responseToCheck.body(not(equalTo("invalid token")));
        return this;
    }
}
