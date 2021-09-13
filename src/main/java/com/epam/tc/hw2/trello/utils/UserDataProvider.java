package com.epam.tc.hw2.trello.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDataProvider {

    public static final String PROPERTIES_PATH = "src/main/resources/auth.properties";
    private static final Properties properties = new Properties();


    public static String getUserKey() {
        loadProperties();
        return properties.getProperty("key");
    }

    public static String getUserToken() {
        loadProperties();
        return properties.getProperty("token");
    }

    private static Properties loadProperties() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
