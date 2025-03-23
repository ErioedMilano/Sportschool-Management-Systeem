package gym.services;

import gym.dao.LidDAO;
import gym.models.Lid;

import java.sql.SQLException;

public class LidService {

    private final LidDAO lidDAO = new LidDAO();

    public void registreerLid(Lid lid){
        try {
            lidDAO.addLid(lid);
        } catch (SQLException e) {
            System.out.println("Databasefout: " + e.getMessage());
        }
    }
}
