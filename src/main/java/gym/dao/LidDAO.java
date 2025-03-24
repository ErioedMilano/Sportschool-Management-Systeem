package gym.dao;

import gym.DatabaseConnection;
import gym.models.Lid;

import java.sql.*;
import java.util.List;

public class LidDAO {

    //Create
    public void addLid(Lid lid) throws SQLException {


        String sql = "INSERT INTO leden (naam, email, geboortedatum) VALEUS (?, ?,?)" ;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1,lid.getNaam());
            preparedStatement.setString(2,lid.getEmail());
            preparedStatement.setDate(3, Date.valueOf(lid.getGeboortedatum()));

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if (resultSet.next()){
                    lid.setId(resultSet.getInt(1));
                }
            }
        }

    }
    // READ (all)
    public List<Lid> getAlleLeden() throws SQLException {

    }
}
