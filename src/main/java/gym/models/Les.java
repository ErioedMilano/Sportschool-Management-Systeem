package gym.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Les {
    private int id;
    private String naam;
    private LocalDateTime tijdslot;
    private int capaciteit;
    private Medewerker medewerker;

    public Les(String naam,LocalDateTime tijdslot,int capaciteit){
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
    public LocalDateTime getTijdslot(){
        return tijdslot;
    }
    public void setTijdslot(LocalDateTime tijdslot){
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
