package com.epam.tc.api.hw2.trello.components;

import lombok.Data;

@Data
public class CardEntity {
    String id;
    boolean closed;
    String desc;
    String idBoard;
    String idList;
    int idShort;
    String name;
    long pos;
}
