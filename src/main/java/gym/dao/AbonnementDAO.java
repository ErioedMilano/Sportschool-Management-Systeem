package gym.dao;

import gym.DatabaseConnection;
import gym.models.Abonnement;

import java.sql.*;

public class AbonnementDAO {

    // CREATE

    public void addAbonnement(Abonnement abonnement) throws SQLException {

        String sql = "INSERT INTO abonnementen(type,maandelijkse_kosten,startdatum,eindatum,lid_id) VALUES(?,?,?,?,?,?)";
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
}
