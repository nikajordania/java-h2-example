package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.JdbcConnection.getConnection;

public class SelectExample {

    public static void main(String[] args) {
        String selectQuery = "SELECT * FROM STUDENTS WHERE AGE > 20";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();) {


            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {

//               NAME, LASTNAME, GROUP_NAME, AGE
                String name = resultSet.getString("NAME");
                String lastname = resultSet.getString("LASTNAME");

                System.out.println(name);
                System.out.println(lastname);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}