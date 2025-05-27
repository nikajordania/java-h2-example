package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


class Config {
    private final String jdbcUrl;

    public Config() {
        Properties properties = new Properties();
        // Load properties from the config.properties file located in the resources directory
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            // Check if the input stream is null, which means the file was not found
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            // Load the properties from the input stream
            properties.load(input);
            // Get the JDBC URL from the properties
            jdbcUrl = properties.getProperty("jdbc.url");
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties", e);
        }
    }

    /**
     * Returns the JDBC URL from the configuration properties.
     *
     * @return the JDBC URL as a String
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }
}
