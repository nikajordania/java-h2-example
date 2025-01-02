package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static org.example.JdbcConnection.getConnection;

public class BatchUpdateExample {
    public static void main(String[] args) {


        String insertQuery = "INSERT INTO STUDENTS (NAME, LASTNAME, GROUP_NAME, AGE) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            connection.setAutoCommit(false);

            for (int i = 0; i < 10; i++) {
                preparedStatement.setString(1, "Value" + i);
                preparedStatement.setString(2, "la" + i);
                preparedStatement.setString(3, "G" + i);
                preparedStatement.setInt(4, i);
                preparedStatement.addBatch();
            }

            int[] rowsAffected = preparedStatement.executeBatch();
            System.out.println("rowsAffected = " + Arrays.toString(rowsAffected));
            connection.commit();

            System.out.println("Batch insert successful. " + rowsAffected.length + " rows affected.");

        } catch (SQLException ignored) {
        }
    }
}
