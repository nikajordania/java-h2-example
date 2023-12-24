package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {
    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/database.properties"))) {
            props.load(in);
        }
        String url = props.getProperty("JDBC_URL");
        System.out.println(url);
//        String username = props.getProperty("username");
//        String password = props.getProperty("password");

//        return DriverManager.getConnection(url, username, password);
        return DriverManager.getConnection(url);
    }
}
