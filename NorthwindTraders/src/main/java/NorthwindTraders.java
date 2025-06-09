import java.sql.*;

public class NorthwindTraders {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =  DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Northwind",
                "userName",
                "password");

        Statement statement = connection.createStatement();
        String query = "SELECT ProductName FROM products";

        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String productName = results.getString("ProductName");
            System.out.println(productName);
        }
        connection.close();


    }
}
