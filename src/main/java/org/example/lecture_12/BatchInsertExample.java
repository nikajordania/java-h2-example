package org.example.lecture_12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static org.example.JdbcConnection.getConnection;

// This class demonstrates how to perform batch insert in a database using JDBC.
public class BatchInsertExample {
    public static void main(String[] args) {

        String insertQuery = "INSERT INTO STUDENTS (NAME, LASTNAME, GROUP_NAME, AGE) VALUES (?, ?, ?, ?)";

        // This query is used to insert multiple records into the STUDENTS table.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            connection.setAutoCommit(false);

            for (int i = 0; i < 10; i++) {
                preparedStatement.setString(1, "Value" + i);
                preparedStatement.setString(2, "la" + i);
                preparedStatement.setString(3, "G" + i);
                preparedStatement.setInt(4, i);

                // Add the current set of parameters to the batch
                preparedStatement.addBatch();
            }

            // Execute the batch of updates
            int[] rowsAffected = preparedStatement.executeBatch();
            System.out.println("rowsAffected = " + Arrays.toString(rowsAffected));

            // Commit the transaction
            connection.commit();

            System.out.println("Batch insert successful. " + rowsAffected.length + " rows affected.");

        } catch (SQLException ignored) {
        }
    }
}
