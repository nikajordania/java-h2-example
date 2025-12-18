package org.example.lecture_12;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.JdbcConnection.getConnection;

public class PersonDAO {
    static Connection connection;

    public static void main(String[] args) {
        // Acquire connection and ensure it's closed at the end
        try (Connection conn = getConnection()) {
            connection = conn; // keep compatibility with existing static methods

            createTable();

            // Prepare five sample persons to insert
            Person p1 = new Person("Daniel", "Carter", "GroupA", 26);
            Person p2 = new Person("Liam", "Miller", "GroupC", 31);
            Person p3 = new Person("James", "Wilson", "GroupB", 23);
            Person p4 = new Person("Olivia", "Anderson", "GroupD", 34);
            Person p5 = new Person("Michael", "Thompson", "GroupA", 28);

            insertPerson(p1);
            insertPerson(p2);
            insertPerson(p3);
            insertPerson(p4);
            insertPerson(p5);

            List<Person> persons = getAllPersons();
            System.out.println("All Persons (after insert):");
            for (Person person : persons) {
                System.out.println(person);
            }

            if (!persons.isEmpty()) {
                Person personToUpdate = persons.get(0);
                personToUpdate.setAge(personToUpdate.getAge() + 1);
                updatePerson(personToUpdate);

                Person updatedPerson = getPersonById(personToUpdate.getId());
                System.out.println("Updated Person: " + updatedPerson);

                // Demonstrate delete (comment/uncomment as needed)
                // deletePerson(updatedPerson.getId());
            }

            List<Person> remainingPersons = getAllPersons();
            System.out.println("Remaining Persons:");
            for (Person person : remainingPersons) {
                System.out.println(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void createTable() {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS persons (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        lastname VARCHAR(255) NOT NULL,
                        group_name VARCHAR(255) NOT NULL,
                        age INT NOT NULL
                    )
                    """;
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert a single person and set the generated id on the Person object
    public static void insertPerson(Person person) throws RuntimeException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO persons (name, lastname, group_name, age) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getLastname());
            preparedStatement.setString(3, person.getGroupName());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    person.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM persons")) {
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("group_name"),
                        resultSet.getInt("age")
                );
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public static Person getPersonById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Person(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("lastname"),
                            resultSet.getString("group_name"),
                            resultSet.getInt("age")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updatePerson(Person person) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE persons SET name = ?, lastname = ?, group_name = ?, age = ? WHERE id = ?")) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getLastname());
            preparedStatement.setString(3, person.getGroupName());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setInt(5, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePerson(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM persons WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
