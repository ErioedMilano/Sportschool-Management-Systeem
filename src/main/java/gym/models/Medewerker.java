package gym.models;

import java.util.List;

public class Medewerker {
    private int id;
    private String naam;
    private String functie;
    private List<Les> lessen;

    public Medewerker(String naam,String functie){
        this.id = id;
        this.naam = naam;
        this.functie = functie;
        this.lessen = lessen;
    }
    public int getId(){
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
    public String getFunctie(){
        return functie;
    }
    public void setFunctie(String functie){
        this.functie = functie;
    }
    public List<Les> getLessen(){
        return lessen;
    }
    public void setLessen(List<Les> lessen){
        this.lessen = lessen;
    }
}
