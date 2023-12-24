package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.JdbcConnection.getConnection;

public class InsertDataExample {

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            //language=sql
            String insertSQL = "INSERT INTO STUDENTS (name, lastname, group_name, age) VALUES ('John', 'Doe', 'GroupA', 25)";

            String insertMultipleSQL = "INSERT INTO STUDENTS (name, lastname, group_name, age) VALUES ('John', 'Doe', 'GroupA', 25);" +
                    "INSERT INTO STUDENTS (name, lastname, group_name, age) VALUES ('asdad', 'sdgjkl', 'GroupB', 225);" +
                    "INSERT INTO STUDENTS (name, lastname, group_name, age) VALUES ('sdf', 'asfsgf', 'GroupC', 123);";

            int rowsAffected = statement.executeUpdate(insertMultipleSQL);
            System.out.println("rowsAffected = " + rowsAffected);
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
