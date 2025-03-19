package gym.models;

import java.time.LocalDate;

public class Abonnement {
    private int id;
    private String type;
    private double maandelijkseKosten;
    private LocalDate startdatum;
    private LocalDate einddatum;

    Abonnement(int id,String type,double maandelijkseKosten,LocalDate startdatum,LocalDate einddatum){
        this.id = id;
        this.type = type;
        this.maandelijkseKosten = maandelijkseKosten;
        this.startdatum = startdatum;
        this.einddatum = einddatum;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){

    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public double getMaandelijkseKosten(){
        return maandelijkseKosten;
    }
    public void setMaandelijkseKosten(double maandelijkseKosten){
        this.maandelijkseKosten = maandelijkseKosten;
    }
    public LocalDate getStartdatum(){
        return startdatum;
    }
    public void setStartdatum(LocalDate startdatum){
        this.startdatum = startdatum;
    }
    public LocalDate getEinddatum(){
        return einddatum;
    }
    public void setEinddatum(LocalDate einddatum){
        this.einddatum = einddatum;
    }
}
