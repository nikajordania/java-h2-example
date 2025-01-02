package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    final static Config config = new Config();

    public static Connection getConnection() throws SQLException {
        String url = config.getJdbcUrl();
        System.out.println(url);
        return DriverManager.getConnection(url);
    }
}
