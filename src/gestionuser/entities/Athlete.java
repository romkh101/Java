/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class Athlete {
    private int id;
    private String nom,prenom;
    private int age;
    private Double poids,taille;
    private Tournoi tournoi;

    private List<Tournoi> tournois; // many-to-many relationship

    public Athlete() {
        this.tournois = new ArrayList<>();
    }

    // other constructors, getters, setters

    public List<Tournoi> getTournois() {
        return tournois;
    }

    public void addTournoi(Tournoi tournoi) {
        this.tournois.add(tournoi);
        tournoi.getAthletes().add(this);
    }

    public void removeTournoi(Tournoi tournoi) {
        this.tournois.remove(tournoi);
        tournoi.getAthletes().remove(this);
    }

    public Athlete(String nom, String prenom, int age, Double poids, Double taille) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        
    }

    public Athlete(int id, String nom, String prenom, int age, Double poids, Double taille) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getTaille() {
        return taille;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }
    

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return "Athlete{" + "nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", poids=" + poids + ", taille=" + taille + '}';
    }
    
    
    
}
