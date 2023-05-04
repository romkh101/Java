/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author asus
 */
public class Participants {
    private String id;
    private String nomPart;
    private String prenomPart;
    private String Cin;
    private String Nom_event;

    public Participants(String nomPart, String prenomPart, String Cin, String Nom_event) {
        this.nomPart = nomPart;
        this.prenomPart = prenomPart;
        this.Cin = Cin;
        this.Nom_event = Nom_event;
    }

    public Participants(String id, String nomPart, String prenomPart, String Cin, String Nom_event) {
        this.id = id;
        this.nomPart = nomPart;
        this.prenomPart = prenomPart;
        this.Cin = Cin;
        this.Nom_event = Nom_event;
    }

    public Participants() {
    }

  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    


    public String getNomPart() {
        return nomPart;
    }

    public void setNomPart(String nomPart) {
        this.nomPart = nomPart;
    }

    public String getPrenomPart() {
        return prenomPart;
    }

    public void setPrenomPart(String prenomPart) {
        this.prenomPart = prenomPart;
    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String Cin) {
        this.Cin = Cin;
    }

    public String getNom_event() {
        return Nom_event;
    }

    public void setNom_event(String Nom_event) {
        this.Nom_event = Nom_event;
    }

    @Override
    public String toString() {
        return "Participants{" + "nomPart=" + nomPart + ", prenomPart=" + prenomPart + ", Cin=" + Cin + ", Nom_event=" + Nom_event + '}';
    }

    public void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void CinPart(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
