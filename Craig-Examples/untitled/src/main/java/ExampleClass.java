import java.sql.*;

public class ExampleClass {
    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];


        try {
            //create a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila",username, password);
            System.out.println(connection);

            //create a SQL statement/query
            String sql = """
                         select * 
                         from film;
                        """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //execute the statement/query
            ResultSet resultSet =  preparedStatement.executeQuery();

            //print header row
            System.out.printf("%-4s %-40s %10s%n", "Id", "Title", "Release Year");
            System.out.println("_________________________________________________________________________________");


            //loop through the results and display them
            while (resultSet.next()){
                int id = resultSet.getInt("film_id");
                String title = resultSet.getString("title");
                int releaseYear = resultSet.getInt("release_year");

                //print row
                System.out.printf("%-4d %-40s %10d%n", id, title, releaseYear);
            }
        } catch (SQLException e) {
            //display user friendly error message
            System.out.println("There was an error retrieving the films. Please try again or contact support.");
            //display error message for the developer
            e.printStackTrace();
        } finally {
        }

    }
}
