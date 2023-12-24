package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.JdbcConnection.getConnection;

public class PreparedStatementInsertDataExample {

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            String insertSQL = "INSERT INTO STUDENTS (name, lastname, group_name, age) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, "John");
                preparedStatement.setString(2, "Doe");
                preparedStatement.setString(3, "GroupA");
                preparedStatement.setInt(4, 25);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully.");
                } else {
                    System.out.println("Failed to insert data.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
