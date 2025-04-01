package gym.services;

import gym.dao.LedenLessenDAO;
import gym.dao.LesDAO;
import gym.models.Les;

import java.sql.SQLException;
import java.util.List;

public class LesService {

    private final LesDAO lesDAO = new LesDAO();
    private final LedenLessenDAO ledenLessenDAO = new LedenLessenDAO();

    public void planLes(Les les, int medewerkerId) {
        try {
            les.getMedewerker().setId(medewerkerId);
            lesDAO.addLes(les);
            System.out.println("Les gepland!");
        } catch (SQLException e) {
            System.err.println("Fout: " + e.getMessage());
        }
    }
    public List<Les> getAllLessen() {
        try {
            return lesDAO.getAllLessen();
        } catch (SQLException e) {
            System.err.println("Fout: " + e.getMessage());
            return List.of();
        }
    }
    public void updateLes(Les les) {
        try {
            lesDAO.updateLes(les);
            System.out.println("Les gewijzigd!");
        } catch (SQLException e) {
            System.err.println("Fout: " + e.getMessage());
        }
    }

    public void annuleerLes(int lesId) {
        try {
            lesDAO.deleteLes(lesId);
            System.out.println("Les geannuleerd!");
        } catch (SQLException e) {
            System.err.println("Fout: " + e.getMessage());
        }
    }

    public void meldAanVoorLes(int lidId, int lesId) {
        try {
            if (isLesVol(lesId)) {
                throw new IllegalStateException("Les is vol!");
            }
            ledenLessenDAO.addInschrijving(lidId, lesId);
            System.out.println("Inschrijving gelukt!");
        } catch (SQLException e) {
            System.err.println("Databasefout: " + e.getMessage());
        }
    }

    private boolean isLesVol(int lesId) throws SQLException {
        Les les = lesDAO.getLesById(lesId);
        int ingeschreven = ledenLessenDAO.getIngeschrevenLeden(lesId).size();
        return ingeschreven >= les.getCapaciteit();
    }
    public Les getLesById(int id) {
        try {
            return lesDAO.getLesById(id);
        } catch (SQLException e) {
            System.err.println("Databasefout: " + e.getMessage());
            return null;
        }
    }

    public void verwijderUitLes(int lidId, int lesId) {
        try {
            ledenLessenDAO.verwijderInschrijving(lidId, lesId);
            System.out.println("Uitschrijving succesvol!");
        } catch (SQLException e) {
            System.err.println("Databasefout: " + e.getMessage());
        }
    }
}
