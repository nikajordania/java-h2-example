package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesExample {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");

        prop.load(input);

        String user = prop.getProperty("user");
        String password = prop.getProperty("password");

        System.out.println("user = " + user);
        System.out.println("password = " + password);
    }
}
