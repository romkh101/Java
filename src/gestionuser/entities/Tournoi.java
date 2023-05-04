/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Acer
 */
public class Tournoi {
   
    private int id;
    private String titre;
    private LocalDate dateTournoi;
    private Athlete athlete;

     private List<Athlete> athletes; // many-to-many relationship

    public Tournoi() {
        this.athletes = new ArrayList<>();
    }

    // other constructors, getters, setters

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void addAthlete(Athlete athlete) {
        this.athletes.add(athlete);
        athlete.getTournois().add(this);
    }

    public void removeAthlete(Athlete athlete) {
        this.athletes.remove(athlete);
        athlete.getTournois().remove(this);
    }

    public Tournoi(String titre, LocalDate dateTournoi) {
        this.titre = titre;
        this.dateTournoi = dateTournoi;
    }

    public Tournoi(int id, String titre, LocalDate dateTournoi) {
        this.id = id;
        this.titre = titre;
        this.dateTournoi = dateTournoi;
    }

    public Tournoi(String titre, Date date) {
        this.titre=titre ;
        this.dateTournoi = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public Tournoi(int id, String titre, Date date) {
        this.id = id;
        this.titre = titre;
        this.dateTournoi = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateTournoi() {
        return dateTournoi;
    }

    public void setDateTournoi(LocalDate dateTournoi) {
        this.dateTournoi = dateTournoi;
    }

    public Tournoi(String titre) {
        this.titre = titre;
    }

    public Tournoi(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }
    

    @Override
    public String toString() {
        return "Tournoi{" + "titre=" + titre + ", dateTournoi=" + dateTournoi + '}';
    }
    
    
    
}
