package gym.dao;

import gym.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LedenLessenDAO {
    // CREATE
    public void addInschrijving(int lidId, int lesId) throws SQLException {

        String sql = "INSERT INTO leden_lessen (lid_id, les_id) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, lidId);
            statement.setInt(2, lesId);
            statement.executeUpdate();
        }
    }
    // READ (Leden per les)
    public List<Integer> getIngeschrevenLeden(int lesId) throws SQLException {

        List<Integer> ledenIds = new ArrayList<>();

        String sql = "SELECT lid_id FROM leden_lessen WHERE les_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, lesId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    ledenIds.add(resultSet.getInt("lid_id"));
                }
            }
        }
        return ledenIds;
    }
    // DELETE (individuele inschrijving)
    public void verwijderInschrijving(int lidId, int lesId) throws SQLException {

        String sql = "DELETE FROM leden_lessen WHERE lid_id=? AND les_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, lidId);
            statement.setInt(2, lesId);
            statement.executeUpdate();
        }
    }
    // DELETE (alle inschrijvingen voor een les)
    public void verwijderInschrijvingenVoorLes(int lesId) throws SQLException {
        String sql = "DELETE FROM leden_lessen WHERE les_id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, lesId);
            statement.executeUpdate();
        }
    }
    // DELETE (alle inschrijvingen voor een lid)
    public void verwijderInschrijvingenVoorLid(int lidId) throws SQLException {

        String sql = "DELETE FROM leden_lessen WHERE lid_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, lidId);
            statement.executeUpdate();
        }
    }
}
