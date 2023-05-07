package gestionuser.entities;

import java.sql.Date;

public class Actualite {
    private int id;
    private String titre;
    private String image;
    private String contenu;
    private String auteur;
    private Date date;
    private Categorie categorie;

    public Actualite() {
    }

    public Actualite(int id, String titre, String image, String contenu, String auteur, Date date, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.image = image;
        this.contenu = contenu;
        this.auteur = auteur;
        this.date = date;
        this.categorie = categorie;
    }

    public Actualite(String titre, String image, String contenu, String auteur, Date date, Categorie categorie) {
        this.titre = titre;
        this.image = image;
        this.contenu = contenu;
        this.auteur = auteur;
        this.date = date;
        this.categorie = categorie;
    }

    public Actualite(String titre, String image, String contenu, String auteur, Date date) {
        this.titre = titre;
        this.image = image;
        this.contenu = contenu;
        this.auteur = auteur;
        this.date = date;
    }

    public Actualite(String titre, String image, String contenu, String auteur) {
        this.titre = titre;
        this.image = image;
        this.contenu = contenu;
        this.auteur = auteur;
    }

    // getters and setters
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    // toString method
    @Override
    public String toString() {
        return "Actualite{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", image='" + image + '\'' +
                ", contenu='" + contenu + '\'' +
                ", auteur='" + auteur + '\'' +
                ", date=" + date +
                ", categorie=" + categorie.getNom() +
                '}';
    }
}