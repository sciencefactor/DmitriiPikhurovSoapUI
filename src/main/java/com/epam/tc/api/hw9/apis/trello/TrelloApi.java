package com.epam.tc.api.hw9.apis.trello;

import com.epam.tc.api.hw9.apis.ApiObject;
import com.epam.tc.api.hw9.apis.trello.components.TrelloAuth;
import com.epam.tc.api.hw9.apis.trello.components.TrelloBoard;
import com.epam.tc.api.hw9.apis.trello.components.TrelloDesc;
import com.epam.tc.api.hw9.apis.trello.components.TrelloMembers;

public class TrelloApi implements ApiObject {

    public static final String DOMAIN = "https://api.trello.com/1";

    private final TrelloAuth trelloAuth;
    private final TrelloBoard trelloBoard;
    private final TrelloDesc trelloDesc;
    private final TrelloMembers trelloMembers;


    public TrelloApi(){
        trelloAuth = new TrelloAuth();
        trelloBoard = new TrelloBoard();
        trelloDesc = new TrelloDesc();
        trelloMembers = new TrelloMembers();
    }


    @Override
    public void authorise() {
        trelloAuth.authorise();
    }

    @Override
    public void keepAuthorisation() {
        trelloAuth.keepAuthorisation();
    }

    public void getBoardById(int id){
        trelloBoard.getBoardById(id);
    }
}
