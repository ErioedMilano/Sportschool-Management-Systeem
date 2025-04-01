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
}
