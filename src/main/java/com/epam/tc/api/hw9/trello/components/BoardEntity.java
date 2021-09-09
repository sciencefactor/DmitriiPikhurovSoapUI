package com.epam.tc.api.hw9.trello.components;

import java.util.Map;
import lombok.Data;

@Data
public class BoardEntity {
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
