package com.epam.tc.api.hw9.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDataProvider {

    //TODO replace
    public static final String PROPERTIES_PATH = "src/main/resources/private/private.auth.properties";
    private static final Properties properties = new Properties();

    private UserDataProvider() {
    }

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
