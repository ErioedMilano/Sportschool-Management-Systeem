package gym.services;

import gym.dao.AbonnementDAO;
import gym.models.Abonnement;

import java.sql.SQLException;

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
}
