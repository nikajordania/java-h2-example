package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

// This class is responsible for establishing a connection to the database.
    final static Config config = new Config();

// This method returns a Connection object to the database using the JDBC URL from the Config class.
    public static Connection getConnection() throws SQLException {
        String url = config.getJdbcUrl();
        System.out.println(url);
        return DriverManager.getConnection(url);
    }
}
