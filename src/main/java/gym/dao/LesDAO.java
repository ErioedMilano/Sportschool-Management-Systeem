package gym.dao;

import gym.DatabaseConnection;
import gym.models.Les;
import gym.models.Medewerker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    // READ (alle lessen)
    public List<Les> getAllLessen() throws SQLException {
        List<Les> lessen = new ArrayList<>();

        String sql = "SELECT l.*, m.naam AS medewerker_naam FROM lessen l JOIN medewerkers m ON l.medewerker_id = m.id";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Les les = new Les(
                        resultSet.getString("naam"),
                        resultSet.getTimestamp("tijdslot").toLocalDateTime(),
                        resultSet.getInt("capaciteit")
                );
                les.setId(resultSet.getInt("id"));

                Medewerker medewerker = new Medewerker(
                        resultSet.getString("medewerker_naam"),
                        "" // Functie wordt niet opgehaald
                );
                medewerker.setId(resultSet.getInt("medewerker_id"));
                les.setMedewerker(medewerker);

                lessen.add(les);
            }
        }
        return lessen;
    }
    // READ (by ID)
    public Les getLesById(int id) throws SQLException {
        String sql = "SELECT l.*, m.naam AS medewerker_naam FROM lessen l JOIN medewerkers m ON l.medewerker_id = m.id WHERE l.id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Les les = new Les(
                            resultSet.getString("naam"),
                            resultSet.getTimestamp("tijdslot").toLocalDateTime(),
                            resultSet.getInt("capaciteit")
                    );
                    les.setId(id);

                    Medewerker medewerker = new Medewerker(
                            resultSet.getString("medewerker_naam"),
                            "" // Functie niet opgehaald
                    );
                    medewerker.setId(resultSet.getInt("medewerker_id"));
                    les.setMedewerker(medewerker);

                    return les;
                }
            }
        }
        return null;
    }

    // UPDATE
    public void updateLes(Les les) throws SQLException {
        String sql = "UPDATE lessen SET naam=?, tijdslot=?, capaciteit=?, medewerker_id=? WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, les.getNaam());
            statement.setTimestamp(2, Timestamp.valueOf(les.getTijdslot()));
            statement.setInt(3, les.getCapaciteit());
            statement.setInt(4, les.getMedewerker().getId());
            statement.setInt(5, les.getId());
            statement.executeUpdate();
        }
    }

    // DELETE
    public void deleteLes(int id) throws SQLException {
        // Verwijder eerst inschrijvingen
        new LedenLessenDAO().verwijderInschrijvingenVoorLes(id);

        String sql = "DELETE FROM lessen WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    // Hulpmethode voor MedewerkerDAO
    void verwijderLessenVanMedewerker(int medewerkerId) throws SQLException {
        String sql = "DELETE FROM lessen WHERE medewerker_id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, medewerkerId);
            statement.executeUpdate();
        }
    }
}
