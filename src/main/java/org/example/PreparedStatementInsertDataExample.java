package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.JdbcConnection.getConnection;

public class PreparedStatementInsertDataExample {

    public static void main(String[] args) {
        insertUser("Nika", "Zhordania", "GroupA", 25);
        insertUser("Nino", "sdgjkl", "GroupB", 225);
        insertUser("Giorgi", "asfsgf", "GroupC", 123);
    }


    public static void insertUser(String name, String lastname, String group_name, int age) {
        try (Connection connection = getConnection()) {
            String insertSQL = "INSERT INTO STUDENTS (name, lastname, group_name, age) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastname);
                preparedStatement.setString(3, group_name);
                preparedStatement.setInt(4, age);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully.");
                } else {
                    System.out.println("Failed to insert data.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
