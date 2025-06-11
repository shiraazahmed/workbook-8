import java.sql.*;
import java.util.Scanner;

public class NorthwindTraders {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Northwind",
                    "userName",
                    "password");

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1: Display all products");
                System.out.println("2: Display all customers");
                System.out.println("3: Display all categories");
                System.out.println("0: Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    String query1 = "SELECT ProductId, ProductName, UnitPrice, UnitsInStock FROM Products";
                    Statement statement = connection.createStatement();
                    ResultSet results = statement.executeQuery(query1);
                    while (results.next()) {
                        System.out.println("ProductId: " + results.getInt("ProductId"));
                        System.out.println("ProductName: " + results.getString("ProductName"));
                        System.out.println("UnitPrice: $" + results.getDouble("UnitPrice"));
                        System.out.println("UnitsInStock: " + results.getInt("UnitsInStock"));
                        System.out.println("----------------");
                    }
                } else if (choice == 2) {
                    String query2 = "SELECT ContactName, CompanyName, Phone, Region, City FROM Customers ORDER BY Country";
                    try (Statement statement = connection.createStatement();
                         ResultSet results = statement.executeQuery(query2)) {
                        while (results.next()) {
                            System.out.println("Contact: " + results.getString("ContactName"));
                            System.out.println("Company: " + results.getString("CompanyName"));
                            System.out.println("Number: " + results.getString("Phone"));
                            System.out.println("Region: " + results.getString("Region"));
                            System.out.println("City: " + results.getString("City"));
                            System.out.println("----------------");
                        }
                    }
                } else if (choice == 3) {
                    String query3 = "SELECT * FROM Categories ORDER BY CategoryId";
                    try (Statement statement = connection.createStatement();
                         ResultSet results = statement.executeQuery(query3)) {
                        while (results.next()) {
                            System.out.println("CategoryId: " + results.getInt("CategoryId"));
                            System.out.println("CategoryName: " + results.getString("CategoryName"));
                            System.out.println("----------------");
                        }
                    }
                    System.out.println("Type your CategoryId ");
                    int categoryId = scanner.nextInt();
                    scanner.nextLine();
                } else if (choice == 0) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice, please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error:");
        } catch (ClassNotFoundException e) {
            System.out.println("Error!");
        } finally {
            if (connection != null) {
                connection.close();
            }
            scanner.close();
        }
    }
}
        




