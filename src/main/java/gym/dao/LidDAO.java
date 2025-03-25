package gym.dao;

import gym.DatabaseConnection;
import gym.models.Lid;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LidDAO {

    //Create
    public void addLid(Lid lid) throws SQLException {


        String sql = "INSERT INTO leden (naam, email, geboortedatum) VALUES (?, ?, ?)" ;

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
        List<Lid> leden = new ArrayList<>();
        String sql = "SELECT * FROM leden";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()){
                Lid lid = new Lid(
                        resultSet.getString("naam"),
                        resultSet.getString("email"),
                        resultSet.getDate("geboortedatum").toLocalDate());
                lid.setId(resultSet.getInt("id"));
                leden.add(lid);
            }
        }
        return leden;
    }
}
