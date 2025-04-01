package gym.services;

import gym.dao.MedewerkerDAO;
import gym.models.Medewerker;

import java.sql.SQLException;
import java.util.List;

public class MedewerkerService {

    private final MedewerkerDAO medewerkerDAO = new MedewerkerDAO();

    public void registreerMedewerker(Medewerker medewerker) {
        try {
            medewerkerDAO.addMedewerker(medewerker);
            System.out.println("Medewerker geregistreerd!");
        } catch (SQLException e) {
            System.err.println("Fout: " + e.getMessage());
        }
    }

    public List<Medewerker> getAllMedewerkers() {
        try {
            return medewerkerDAO.getAllMedewerkers();
        } catch (SQLException e) {
            System.err.println("Fout: " + e.getMessage());
            return List.of();
        }
    }

    public void updateMedewerker(Medewerker medewerker) {
        try {
            medewerkerDAO.updateMedewerker(medewerker);
            System.out.println("Medewerker gewijzigd!");
        } catch (SQLException e) {
            System.err.println("Fout: " + e.getMessage());
        }
    }

    public void verwijderMedewerker(int id) {
        try {
            medewerkerDAO.deleteMedewerker(id);
            System.out.println("Medewerker verwijderd!");
        } catch (SQLException e) {
            System.err.println("Fout: " + e.getMessage());
        }
    }
    public Medewerker getMedewerkerById(int id) {
        try {
            return medewerkerDAO.getMedewerkerById(id);
        } catch (SQLException e) {
            System.err.println("Databasefout: " + e.getMessage());
            return null;
        }
    }
}
