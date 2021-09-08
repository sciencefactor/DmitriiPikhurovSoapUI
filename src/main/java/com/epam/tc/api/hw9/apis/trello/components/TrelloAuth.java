package com.epam.tc.api.hw9.apis.trello.components;

import static io.restassured.RestAssured.given;
import static com.epam.tc.api.hw9.apis.trello.TrelloApi.DOMAIN;
import static com.epam.tc.api.hw9.apis.trello.components.TrelloMembers.MEMBERS_ENDPOINT;
import static com.epam.tc.api.hw9.apis.trello.components.TrelloMembers.USER_ENDPOINT;

import com.epam.tc.api.hw9.utils.UserDataProvider;
import io.restassured.response.Response;
import java.awt.Desktop;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

public class TrelloAuth {

    public static final String CREATE_USER_KEY_REF = "https://trello.com/app-key/";
    public static final String AUTH_ENDPOINT = "/authorize";
    public static final String AUTH_STANDARD_PARAMS = "?expiration=1day&name=EpamTrainingAPI&scope=read&response_type=token&key=";
    final String RELATIVE_PATH_TO_GET_KEY_INVITATION = "sites/getKeyInvitation.html";
    final String RELATIVE_PATH_TO_AUTH_INVITATION = "sites/authInvitation.html";
    final long timeToWait = 3500;

    @SneakyThrows
    @Test
    public void getUserKey() {
        String pathToInvitationSite = "file://" + Objects.requireNonNull(getClass()
            .getClassLoader()
            .getResource(RELATIVE_PATH_TO_GET_KEY_INVITATION)).getPath();
        URI html = new URI(pathToInvitationSite);
        Desktop.getDesktop().browse(html);
        Thread.sleep(timeToWait);
        html = new URI(CREATE_USER_KEY_REF);
        Desktop.getDesktop().browse(html);
    }

    @SneakyThrows
    @Test
    public void authorise() {
        String pathToInvitationSite = "file://" + Objects.requireNonNull(getClass()
            .getClassLoader()
            .getResource(RELATIVE_PATH_TO_AUTH_INVITATION)).getPath();
        URI html = new URI(pathToInvitationSite);
        Desktop.getDesktop().browse(html);
        Thread.sleep(timeToWait);
        String authLink = DOMAIN + AUTH_ENDPOINT + AUTH_STANDARD_PARAMS + UserDataProvider.getUserKey();
        html = new URI(authLink);
        Desktop.getDesktop().browse(html);
    }


    @Test
    public void keepAuthorisation() {
        Response response =
            given().param("key", UserDataProvider.getUserKey())
                   .param("token", UserDataProvider.getUserToken())
                   .when()
                   .get(DOMAIN + MEMBERS_ENDPOINT + USER_ENDPOINT);
        if(hasNoKey(response)){
            getUserKey();
        } else if (isNotAuthorised(response)) {
            authorise();
        }
    }

    private boolean hasNoKey(Response response){
        return response.getStatusCode() == 401 && response.getBody().asString().equals("invalid key");
    }

    private boolean isNotAuthorised(Response response) {
        return response.getBody().asString().equals("invalid token");
    }

}
