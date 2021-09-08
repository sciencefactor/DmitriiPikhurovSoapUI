package com.epam.tc.api.hw9.apis.trello.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrelloWorkspace {

    private static Map<String, List<Object>> components = new HashMap<>();

    public static void addComponent(Object component) {
        String key = component.getClass().getSimpleName();
        System.out.println(key);
        if (components.containsKey(key)) {
            components.get(key).add(component);
        } else {
            List<Object> store = new ArrayList<>();
            store.add(component);
            components.put(key, store);

        }
    }


    public static TrelloBoard getBoardByName(String name) {
        return (TrelloBoard) components.get(TrelloBoard.class.getSimpleName())
                                       .stream()
                                       .filter(board -> ((TrelloBoard)board).getName().equals(name))
                                       .findFirst().orElseThrow();
    }

    public static void get() {

    }
}
