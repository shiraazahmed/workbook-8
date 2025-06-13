import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private BasicDataSource dataSource;

    public DataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Actor> searchActorsByName(String name) throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT actor_id, first_name, last_name FROM actor WHERE first_name LIKE ? OR last_name LIKE ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

        }
}
