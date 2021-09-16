package com.epam.tc.hw2.trello.asserts;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import com.epam.tc.hw2.trello.dto.BoardDto;
import com.epam.tc.hw2.trello.dto.ListDto;
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

    public TrelloAssertProvider checkBoardDto(BoardDto boardDraft) {
        BoardDto boardToCheck = responseToCheck
            .extract()
            .as(BoardDto.class);
        assertEquals(boardToCheck.getName(), boardDraft.getName());
        assertEquals(boardToCheck.isClosed(),boardDraft.isClosed());
        return this;
    }

    public TrelloAssertProvider checkListDto(ListDto listDtoDraft) {
        ListDto listToCheck = responseToCheck
            .extract()
            .as(ListDto.class);
        assertEquals(listToCheck.getName(), listDtoDraft.getName());
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
