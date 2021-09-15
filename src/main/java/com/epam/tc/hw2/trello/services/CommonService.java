package com.epam.tc.hw2.trello.services;

import static com.epam.tc.hw2.trello.asserts.TrelloAssertProvider.assertThat;
import static io.restassured.RestAssured.given;

import com.epam.tc.hw2.trello.utils.UserDataProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import java.util.function.Function;

public class CommonService {

    public static final String DOMAIN = "https://api.trello.com/1";
    public static RequestSpecification preAuthorisedRequest;

    public CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        preAuthorisedRequest = new RequestSpecBuilder().setBaseUri(DOMAIN).build();
        preAuthorisedRequest.given()
                            .queryParam("key", UserDataProvider.getUserKey())
                            .queryParam("token", UserDataProvider.getUserToken())
                            .contentType("application/xml");
    }

    @SafeVarargs
    public final Response makeRequest(Function<RequestSpecification, Response> function,
                                      String uri,
                                      Map<String, String>... params) {
        RequestSpecification specification = given(preAuthorisedRequest).basePath(uri);
        if (params.length > 0) {
            for (Map.Entry<String, String> param : params[0].entrySet()) {
                specification.queryParam(param.getKey(), param.getValue());
            }
        }
        Response response = function.apply(specification);
        assertThat(response).statusCodeValid();
        return response;
    }
}
