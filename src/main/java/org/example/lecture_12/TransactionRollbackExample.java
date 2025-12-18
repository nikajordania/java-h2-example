package org.example.lecture_12;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.JdbcConnection.getConnection;

public class TransactionRollbackExample {

    public static void main(String[] args) {

        try (Connection conn = getConnection()) {

            createTable(conn);
            showUsers(conn, "BEFORE TRANSACTION");

            runTransactionWithRollback(conn);

            showUsers(conn, "AFTER TRANSACTION");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void createTable(Connection conn) throws SQLException {
        conn.createStatement().execute("""
                    CREATE TABLE IF NOT EXISTS users (
                        id IDENTITY PRIMARY KEY,
                        name VARCHAR(100),
                        age INT CHECK (age >= 18)
                    )
                """);

        conn.createStatement().execute("""
                    INSERT INTO users(name, age) VALUES
                    ('Nika', 25),
                    ('Giorgi', 30)
                """);

        System.out.println("Table & initial data created");
    }

    static void runTransactionWithRollback(Connection conn) {
        try {
            System.out.println("START TRANSACTION");

            conn.setAutoCommit(false);

            conn.createStatement().execute("UPDATE users SET age = age + 1 WHERE name = 'Nika'");
            System.out.println("Nika updated");

            conn.createStatement().execute("INSERT INTO users(name, age) VALUES ('BrokenUser', 15)");

            conn.commit();
            System.out.println("TRANSACTION COMMITTED");

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED → ROLLBACK");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void showUsers(Connection conn, String title) throws SQLException {
        System.out.println(title);

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM users");

        while (rs.next()) {
            System.out.println(
                    rs.getLong("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getInt("age")
            );
        }
    }
}
