package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.JdbcConnection.getConnection;

public class SelectExample {

    public static void main(String[] args) {
//        Connection conn = DriverManager.getConnection(myUrl, USER, PASS);
//        String query = "INSERT INTO students (ID, last_name, first_name, birthday, hometown) VALUES (?, ?, ?, ?, ?)";
//        PreparedStatement preparedStmt = conn.prepareStatement(query);
//        preparedStmt.setInt(1, 808027);
//        preparedStmt.setString(2, "Davis");
//        preparedStmt.setString(3, "Felicita");
//        preparedStmt.setDate(4, startDate);
//        preparedStmt.setString(5, "Venice");
//        preparedStmt.execute();
//        conn.close();


        String selectQuery = "SELECT * FROM STUDENTS";

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}