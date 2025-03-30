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
    // UPDATE
    public void updateLid(Lid lid) {
        try {
            lidDAO.updateLid(lid);
        } catch (IllegalArgumentException e) {
            System.err.println("Validatiefout: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Databasefout: " + e.getMessage());
        }
    }
    // DELETE
    public void verwijderLid(int id) {
        try {
            // Verwijder eerst gekoppelde data
            lidDAO.deleteLid(id);
        } catch (SQLException e) {
            System.err.println("Databasefout: " + e.getMessage());
        }
    }
}
