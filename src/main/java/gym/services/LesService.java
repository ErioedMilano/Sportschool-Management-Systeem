package gym.services;

import gym.dao.LedenLessenDAO;
import gym.dao.LesDAO;
import gym.models.Les;

import java.sql.SQLException;

public class LesService {

    private final LesDAO lesDAO = new LesDAO();
    private final LedenLessenDAO ledenLesDAO = new LedenLessenDAO();

    public void planLes(Les les, int medewerkerId) {
        try {
            les.getMedewerker().setId(medewerkerId);
            lesDAO.addLes(les);
            System.out.println("Les gepland!");
        } catch (SQLException e) {
            System.err.println("Fout: " + e.getMessage());
        }
    }
}
