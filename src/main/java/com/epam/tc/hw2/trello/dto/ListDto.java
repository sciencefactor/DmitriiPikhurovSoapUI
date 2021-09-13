package com.epam.tc.hw2.trello.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListDto {
    String id;
    String name;
    boolean closed;
    long pos;
    String idBoard;
}
