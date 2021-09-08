package com.epam.tc.api.hw9.asserts.trello;

import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;


public class TrelloAssertProvider {

    Response responseToCheck;

    public TrelloAssertProvider() {
    }

    public TrelloAssertProvider responseToCheck(Response response) {
        responseToCheck = response;
        return this;
    }

    public TrelloAssertProvider and(){
        return this;
    }

    public TrelloAssertProvider correctJsonFormat() {
        responseToCheck.then().assertThat().contentType(ContentType.JSON);
        return this;
    }

    public TrelloAssertProvider correctBoardId() {
        responseToCheck.then().body(equalTo("Root"));
        return this;
    }

    public TrelloAssertProvider statusCodeShouldBeOk() {
        responseToCheck.then().
            statusCode(HttpStatus.SC_OK);
        return this;
    }


    public TrelloAssertProvider correctUserKey() {
        responseToCheck.then()
                .body(not(equalTo("invalid key")));
        return this;
    }

    public TrelloAssertProvider correctUserToken() {
        responseToCheck.then()
                .body(not(equalTo("invalid token")));
        return this;
    }
}
