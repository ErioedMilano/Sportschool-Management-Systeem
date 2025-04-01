package gym.dao;

import gym.DatabaseConnection;
import gym.models.Les;

import java.sql.*;

public class LesDAO {
    // CREATE
    public void addLes(Les les) throws SQLException {

        String sql = "INSERT INTO lessen (naam, tijdslot, capaciteit, medewerker_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, les.getNaam());
            statement.setTimestamp(2, Timestamp.valueOf(les.getTijdslot()));
            statement.setInt(3, les.getCapaciteit());
            statement.setInt(4, les.getMedewerker().getId());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) les.setId(resultSet.getInt(1));
            }
        }
    }
}
