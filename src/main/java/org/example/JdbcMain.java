package org.example;

import java.sql.*;

public class JdbcMain {

    public static void main(String[] args) throws SQLException {

        // H2 database connection URL (file-based database)
        String jdbcURL = "jdbc:h2:./mydb";

        // create connection to the database
        Connection connection = DriverManager.getConnection(jdbcURL);

        // create statement object to execute SQL queries
        Statement statement = connection.createStatement();

        // create table SQL with auto increment primary key (so we don't need to provide id value on insert)
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255),
                    lastname VARCHAR(255)
                )
                """;

        // create table if not exists (it create also database file mydb.mv.db in the project root folder if not exists)
        statement.execute(createTableSQL);

        // insert data into users table
        statement.executeUpdate("INSERT INTO users (name, lastname) VALUES ('Nick', 'Johnson')");
        statement.executeUpdate("INSERT INTO users (name, lastname) VALUES ('George', 'Smith')");

        // select data from users table
        ResultSet resultSet = statement.executeQuery("select * from users");

        // iterate over selected result set and print
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Last Name: " + resultSet.getString("lastname"));
            System.out.println("-----");
        }
    }
}
