package gym.dao;

import gym.DatabaseConnection;
import gym.models.Medewerker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedewerkerDAO {
    // CREATE
    public void addMedewerker(Medewerker medewerker) throws SQLException {

        String sql = "INSERT INTO medewerkers (naam, functie) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, medewerker.getNaam());
            statement.setString(2, medewerker.getFunctie());
            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) medewerker.setId(rs.getInt(1));
            }
        }
    }
    // READ (alle medewerkers)
    public List<Medewerker> getAllMedewerkers() throws SQLException {

        List<Medewerker> medewerkers = new ArrayList<>();

        String sql = "SELECT * FROM medewerkers";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Medewerker m = new Medewerker(
                        resultSet.getString("naam"),
                        resultSet.getString("functie")
                );
                m.setId(resultSet.getInt("id"));
                medewerkers.add(m);
            }
        }
        return medewerkers;
    }
    // READ (by ID)
    public Medewerker getMedewerkerById(int id) throws SQLException {

        String sql = "SELECT * FROM medewerkers WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Medewerker m = new Medewerker(
                            resultSet.getString("naam"),
                            resultSet.getString("functie")
                    );
                    m.setId(id);
                    return m;
                }
            }
        }
        return null;
    }
    // UPDATE
    public void updateMedewerker(Medewerker medewerker) throws SQLException {

        String sql = "UPDATE medewerkers SET naam=?, functie=? WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, medewerker.getNaam());
            statement.setString(2, medewerker.getFunctie());
            statement.setInt(3, medewerker.getId());
            statement.executeUpdate();
        }
    }
    // DELETE
    public void deleteMedewerker(int id) throws SQLException {
        // Verwijder eerst gekoppelde lessen
        new LesDAO().verwijderLessenVanMedewerker(id);

        String sql = "DELETE FROM medewerkers WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
