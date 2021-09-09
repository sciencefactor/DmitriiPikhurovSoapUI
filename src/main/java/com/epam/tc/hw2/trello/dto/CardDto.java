package com.epam.tc.hw2.trello.dto;

import lombok.Data;

@Data
public class CardDto {
    String id;
    boolean closed;
    String desc;
    String idBoard;
    String idList;
    int idShort;
    String name;
    long pos;
}
