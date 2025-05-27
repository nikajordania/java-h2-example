package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.JdbcConnection.getConnection;

public class SelectExample {

    public static void main(String[] args) {
        // This example demonstrates how to select data from a database using JDBC.
        String selectQuery = "SELECT * FROM STUDENTS WHERE AGE > 20";

        // Connection is that object which is used to connect to the database.
        // Statement is used to execute SQL queries.
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            // This code executes a SQL SELECT query to retrieve all records from the STUDENTS table where the AGE is greater than 20.
            // ResultSet is used to store the results of the query.
            // ResultSet is iterable, meaning you can loop through the results.
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // .next() is used to move the cursor to the next row in the ResultSet.
            while (resultSet.next()) {

//               NAME, LASTNAME, GROUP_NAME, AGE
                String name = resultSet.getString("NAME");
                // Get typed value from the ResultSet by column label (also can use column index)
                String lastname = resultSet.getString("LASTNAME");

                System.out.println(name);
                System.out.println(lastname);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}