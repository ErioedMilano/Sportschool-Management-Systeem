package gym.models;

import java.time.LocalDate;

public class Les {
    private int id;
    private String naam;
    private LocalDate tijdslot;
    private int capaciteit;
    private Medewerker medewerker;

    Les(int id,String naam,LocalDate tijdslot,int capaciteit,Medewerker medewerker){
        this.id = id;
        this.naam = naam;
        this.tijdslot = tijdslot;
        this.capaciteit = capaciteit;
        this.medewerker = medewerker;
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
    public LocalDate getTijdslot(){
        return tijdslot;
    }
    public void setTijdslot(LocalDate tijdslot){
        this.tijdslot = tijdslot;
    }
    public int getCapaciteit(){
        return capaciteit;
    }
    public void setCapaciteit(int capaciteit){
        this.capaciteit = capaciteit;
    }
    public Medewerker getMedewerker(){
        return medewerker;
    }
    public void setMedewerker(Medewerker medewerker){
        this.medewerker = medewerker;
    }
}
