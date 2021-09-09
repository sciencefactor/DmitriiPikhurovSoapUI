package com.epam.tc.hw2.trello.dto;

import lombok.Data;

@Data
public class ListDto {

    String id;
    String name;
    boolean closed;
    long pos;
    String idBoard;
}
