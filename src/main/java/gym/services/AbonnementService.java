package gym.services;

import gym.dao.AbonnementDAO;
import gym.models.Abonnement;

import java.sql.SQLException;
import java.util.List;

public class AbonnementService {

    private final AbonnementDAO abonnementDAO = new AbonnementDAO();

    public void registreerAbonnement(Abonnement abonnement){
        try {
            abonnementDAO.addAbonnement(abonnement);
            System.out.println("Abonnement succesvol geregistreerd!");
        }catch (IllegalArgumentException e){
            System.err.println("Validatiefout: " + e.getMessage());
        }catch (SQLException e){
            System.err.println("Databasefout: " + e.getMessage());
        }
    }
    public List<Abonnement> getAllAbonnementen() {
        try {
            return abonnementDAO.getAllAbonnementen();
        } catch (SQLException e) {
            handleError("Ophalen abonnementen mislukt", e);
            return List.of();
        }
    }
    public Abonnement getAbonnementById(int id) {
        try {
            return abonnementDAO.getAbonnementById(id);
        } catch (SQLException e) {
            System.err.println("Databasefout: " + e.getMessage());
            return null;
        }
    }
    private void handleError(String message, SQLException e) {
        System.err.println(message + ": " + e.getMessage());
    }
}

