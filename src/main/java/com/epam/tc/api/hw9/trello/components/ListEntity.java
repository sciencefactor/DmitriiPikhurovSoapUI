package com.epam.tc.api.hw9.trello.components;

import lombok.Data;

@Data
public class ListEntity {

    String id;
    String name;
    boolean closed;
    long pos;
    String idBoard;
}
