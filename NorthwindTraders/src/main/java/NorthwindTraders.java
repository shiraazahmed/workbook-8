import java.sql.*;

public class NorthwindTraders {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =  DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Northwind",
                "userName",
                "password");

        Statement statement = connection.createStatement();
        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products";

        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String productName = results.getString("ProductName");
            int productId = results.getInt("ProductID");
            double unitPrice = results.getDouble("UnitPrice");
            int unitsInStock = results.getInt("UnitsInStock");
            System.out.println(productName);
            System.out.println("Product ID: " + productId);
            System.out.println("Unit Price: $" + unitPrice);
            System.out.println("Units in Stock: " + unitsInStock);
            System.out.println("----------------");
        }
        connection.close();


    }
}
