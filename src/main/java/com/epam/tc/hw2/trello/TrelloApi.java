package com.epam.tc.hw2.trello;

import com.epam.tc.hw2.trello.dto.BoardDto;
import com.epam.tc.hw2.trello.dto.ListDto;
import com.epam.tc.hw2.trello.services.BoardsService;
import com.epam.tc.hw2.trello.services.ListsService;
import com.epam.tc.hw2.trello.services.MembersService;
import io.restassured.response.Response;

public class TrelloApi {

    public BoardDto createBoard(BoardDto board) {
        return BoardsService.create(board);
    }

    public BoardDto getBoard(BoardDto board) {
        return BoardsService.getResponse(board);
    }

    public Response deleteBoard(BoardDto board) {
        return BoardsService.delete(board);
    }

    public void deleteAllBoards() {
        MembersService.deleteAllRemoteBoards();
    }

    public ListDto createList(BoardDto board, ListDto list) {
        return ListsService.create(board, list);
    }

    public Response deleteList(ListDto list) {
        return ListsService.delete(list);
    }

    public ListDto moveList(ListDto list, BoardDto newBoard) {
        return ListsService.move(list, newBoard);
    }

}
