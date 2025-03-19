package gym.models;

import java.time.LocalDate;
import java.util.List;

public class Lid {
    private int id;
    private String naam;
    private String email;
    private LocalDate geboortedatum;
    private Abonnement abonnement;
    private List<Les>lessen;

    Lid(int id,String naam,String email,LocalDate geboortedatum,Abonnement abonnement,List<Les> lessen){
        this.id = id;
        this.naam = naam;
        this.email = email;
        this.geboortedatum = geboortedatum;
        this.abonnement = abonnement;
        this.lessen = lessen;
    }
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNaam(){
        return naam;
    }
    public void setNaam(String naam){
        this.naam = naam;
    }
    public String getEmail(){
        return email;
    }
    public LocalDate getGeboortedatum(){
        return geboortedatum;
    }
    public void setGeboortedatum(LocalDate geboortedatum){
        this.geboortedatum = geboortedatum;
    }
    public Abonnement getAbonnement(){
        return abonnement;
    }
    public void setAbonnement(Abonnement abonnement){
        this.abonnement = abonnement;
    }
    public List<Les> getLessen(){
        return lessen;
    }
    public void setLessen(List<Les> lessen){
        this.lessen = lessen;
    }
}
