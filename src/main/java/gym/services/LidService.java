package gym.services;

import gym.dao.LidDAO;
import gym.models.Lid;

import java.sql.SQLException;
import java.util.List;

public class LidService {

    private final LidDAO lidDAO = new LidDAO();

    public void registreerLid(Lid lid){
        try {
            lidDAO.addLid(lid);
        } catch (SQLException e) {
            System.err.println("Databasefout: " + e.getMessage());
        }
    }
    public List<Lid> getAlleLeden(){
        try {
            return lidDAO.getAlleLeden();
        }catch (SQLException e){
            System.err.println("Databasefout: " + e.getMessage());
        }
        return List.of();
    }
}
