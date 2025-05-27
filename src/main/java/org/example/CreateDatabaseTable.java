package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.JdbcConnection.getConnection;

public class CreateDatabaseTable {
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            // This code creates a table named "students" in the H2 database if it does not already exist.
            //language=sql
            String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS students (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        lastname VARCHAR(255) NOT NULL,
                        group_name VARCHAR(255) NOT NULL,
                        age INT NOT NULL
                    )""";

            statement.executeUpdate(createTableSQL);

            System.out.println("Table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
