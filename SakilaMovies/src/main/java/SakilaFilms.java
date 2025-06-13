// src/main/java/SakilaFilms.java
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Scanner;

public class SakilaFilms {
    public static void main(String[] args) {
        // Set up the DataSource
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("username");
        dataSource.setPassword("password");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the last name of an actor you like: ");
            String lastName = scanner.nextLine().trim();

            String query = "SELECT actor_id, first_name, last_name FROM actor WHERE last_name = ?";
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query)
            ) {
                statement.setString(1, lastName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Matching actors:");
                        do {
                            System.out.printf("%d: %s %s%n",
                                    resultSet.getInt("actor_id"),
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"));

                        } while (resultSet.next());
                    } else {
                        System.out.println("No actors found: " + lastName);
                        return;
                    }
                }
            }

            System.out.print("Enter the actor's first name: ");
            String first_Name = scanner.nextLine().trim();
            System.out.print("Enter the actor's last name: ");
            String last_Name = scanner.nextLine().trim();

            String filmQuery = "SELECT title " +
                    "FROM film " +
                    "WHERE film_id IN (" +
                    "SELECT film_id " +
                    "FROM film_actor " +
                    "WHERE actor_id = (" +
                    "SELECT actor_id " +
                    "FROM actor " +
                    "WHERE first_name = ? AND last_name = ?" +
                    ")" +
                    ")";



            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement(filmQuery)
            ) {
                statement.setString(1, first_Name);
                statement.setString(2, last_Name);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Films cast " + first_Name + " " + last_Name + ":");
                        do {
                            System.out.println(resultSet.getString("title"));
                        } while (resultSet.next());
                    } else {
                        System.out.println("No films found for that actor.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred.");
            e.printStackTrace();
        }
    }
}