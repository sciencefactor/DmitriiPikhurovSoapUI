package com.epam.tc.hw2.trello.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {
    String id;
    String name;
    String desc;
    String descData;
    String closed;
    String idOrganization;
    String idEnterprise;
    String pinned;
    String url;
    String shortUrl;
    Map<String, String> prefs;
    Map<String, String> labelNames;
}
