package com.epam.tc.api.hw9.asserts;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class AssertProvider {

    void checkStatusCode(Response response) {
        response.then().
                  statusCode(400);
    }
}
