package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesExample {
    public static void main(String[] args) throws IOException {
        // This example demonstrates how to read properties from a file using Java's Properties class.
        Properties prop = new Properties();
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");

        prop.load(input);

        String user = prop.getProperty("user");
        String password = prop.getProperty("password");

        System.out.println("user = " + user);
        System.out.println("password = " + password);
    }
}
