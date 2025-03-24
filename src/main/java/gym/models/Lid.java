package gym.models;

import java.time.LocalDate;
import java.util.List;

public class Lid {
    private int id;
    private String naam;
    private String email;
    private LocalDate geboortedatum;
    private Abonnement abonnement;
    private List<Les> lessen;

    public Lid(String naam, String email, LocalDate geboortedatum) {
        this.naam = naam;
        this.email = email;
        this.geboortedatum = geboortedatum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }
}
