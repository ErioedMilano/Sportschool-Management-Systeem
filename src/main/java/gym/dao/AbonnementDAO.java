package gym.dao;

import gym.DatabaseConnection;
import gym.models.Abonnement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonnementDAO {

    // CREATE

    public void addAbonnement(Abonnement abonnement) throws SQLException {

        String sql = "INSERT INTO abonnementen(type,maandelijkse_kosten,startdatum,einddatum,lid_id) VALUES(?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1,abonnement.getType());
            preparedStatement.setDouble(2,abonnement.getMaandelijkseKosten());
            preparedStatement.setDate(3, Date.valueOf(abonnement.getStartdatum()));
            preparedStatement.setDate(4,Date.valueOf(abonnement.getEinddatum()));
            preparedStatement.setInt(5,abonnement.getLidId());

            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if (resultSet.next()){
                    abonnement.setId(resultSet.getInt(1));
                }
            }
        }
    }
    // READ (alle abonnementen)
    public List<Abonnement> getAllAbonnementen() throws SQLException {
        List<Abonnement> abonnementen = new ArrayList<>();
        String sql = "SELECT * FROM abonnementen";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Abonnement abonnement = new Abonnement(
                        resultSet.getString("type"),
                        resultSet.getDouble("maandelijkse_kosten"),
                        resultSet.getDate("startdatum").toLocalDate(),
                        resultSet.getDate("einddatum").toLocalDate(),
                        resultSet.getInt("lid_id")
                );
                abonnement.setId(resultSet.getInt("id"));
                abonnementen.add(abonnement);
            }
        }
        return abonnementen;
    }
    // READ (by ID)
    public Abonnement getAbonnementById(int id) throws SQLException {

        String sql = "SELECT * FROM abonnementen WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Abonnement(
                            resultSet.getString("type"),
                            resultSet.getDouble("maandelijkse_kosten"),
                            resultSet.getDate("startdatum").toLocalDate(),
                            resultSet.getDate("einddatum").toLocalDate(),
                            resultSet.getInt("lid_id")
                    );
                }
            }
        }
        return null;
    }
    // READ (by Lid ID)
    public Abonnement getAbonnementByLidId(int lidId) throws SQLException {

        String sql = "SELECT * FROM abonnementen WHERE lid_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, lidId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Abonnement(
                            resultSet.getString("type"),
                            resultSet.getDouble("maandelijkse_kosten"),
                            resultSet.getDate("startdatum").toLocalDate(),
                            resultSet.getDate("einddatum").toLocalDate(),
                            resultSet.getInt("lid_id")
                    );
                }
            }
        }
        return null;
    }
    // UPDATE
    public void updateAbonnement(Abonnement abonnement) throws SQLException {

        String sql = "UPDATE abonnementen SET type=?, maandelijkse_kosten=?, startdatum=?, einddatum=? WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, abonnement.getType());
            statement.setDouble(2, abonnement.getMaandelijkseKosten());
            statement.setDate(3, Date.valueOf(abonnement.getStartdatum()));
            statement.setDate(4, Date.valueOf(abonnement.getEinddatum()));
            statement.setInt(5, abonnement.getId());

            statement.executeUpdate();
        }
    }
    // DELETE
    public void deleteAbonnement(int id) throws SQLException {

        String sql = "DELETE FROM abonnementen WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
