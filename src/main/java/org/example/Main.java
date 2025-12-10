package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:h2:./test;AUTO_SERVER=TRUE";
        //        ;AUTO_SERVER=TRUE

        System.out.println("Connected to H2 in-memory database.");

//        String sql = "Create table students (ID int primary key, name varchar(50))";
//        String sql = "create table if not exists students (ID int primary key, name varchar(50))";

        String sql = """
                create table if not exists students 
                (
                ID IDENTITY not null primary key,
                name varchar(50)
                )
                """;

        //try-with-resources
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);

            System.out.println("Created table students.");

//            sql = "Insert into students (ID, name) values (1, 'Nika')";
            sql = "Insert into students (name) values ('Nika')";

            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("Inserted a new row.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}