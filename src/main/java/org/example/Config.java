package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


class Config {
    private String jdbcUrl;

    public Config() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            properties.load(input);
            jdbcUrl = properties.getProperty("jdbc.url");
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties", e);
        }
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }
}
