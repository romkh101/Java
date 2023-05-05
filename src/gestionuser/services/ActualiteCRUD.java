/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.services;

import gestionuser.entities.Actualite;
import gestionuser.entities.Categorie;
import gestionuser.MyConnection.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;

/**
 *
 * @author Aziz
 */
public class ActualiteCRUD {
    public static ArrayList<String> getCategories() {
    ArrayList<String> categories = new ArrayList<>();
    try {
        
      
        String query = "SELECT c.nom FROM categorie c JOIN actualite a ON a.categorie_id = c.id";
        Statement st= MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String categorie = rs.getString("nom");
            categories.add(categorie);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return categories;
}
 public void ajouterActualite(Actualite a) {
    String requete = "INSERT INTO actualite (titre, image, contenu, auteur, date, categorie_id) VALUES (?, ?, ?, ?, ?, ?)";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = dateFormat.format(a.getDate());

    try {
        // Récupération de l'identifiant de la catégorie
        int categorieId = getCategorieId(a.getCategorie().getNom());
        
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, a.getTitre());
        pst.setString(2, a.getImage());
        pst.setString(3, a.getContenu());
        pst.setString(4, a.getAuteur());
        pst.setString(5, dateString);
        pst.setInt(6, categorieId);
        pst.executeUpdate();
        System.out.println("Actualite ajoutee !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

private int getCategorieId(String nomCategorie) {
    String requete = "SELECT id FROM categorie WHERE nom = ?";
    
    try {
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setString(1, nomCategorie);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            return -1; // Si la catégorie n'existe pas
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return -1;
    }
}
 public ObservableList<Actualite> listerActualitesParCategorie(Categorie categorie) throws ParseException{
      
    ObservableList<Actualite> myList= FXCollections.observableArrayList();
      
    try {
        String requete="SELECT actualite.*, categorie.nom AS categorie_nom FROM actualite LEFT JOIN categorie ON actualite.categorie_id = categorie.id WHERE categorie_id = ?";
        PreparedStatement ps= MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setInt(1, categorie.getId());
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Actualite a = new Actualite();
            a.setId(rs.getInt(1));
            a.setTitre(rs.getString("Titre"));
            a.setImage(rs.getString("Image"));
            a.setContenu(rs.getString("Contenu"));
            a.setAuteur(rs.getString("Auteur"));
            String dateString = rs.getString("Date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = format.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            a.setDate(sqlDate);
            Categorie c = new Categorie();
            c.setId(rs.getInt("categorie_id"));
            c.setNom(rs.getString("categorie_nom"));
            a.setCategorie(c);
            myList.add(a);
        }
             
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}
public ObservableList<Actualite> listerActualite() throws ParseException{
      
    ObservableList<Actualite> myList= FXCollections.observableArrayList();
      
    try {
        String requete="SELECT actualite.*, categorie.nom AS categorie_nom FROM actualite LEFT JOIN categorie ON actualite.categorie_id = categorie.id";
        Statement st= MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);

        while(rs.next()){
            Actualite a = new Actualite();
            a.setId(rs.getInt(1));
            a.setTitre(rs.getString("Titre"));
            a.setImage(rs.getString("Image"));
            a.setContenu(rs.getString("Contenu"));
            a.setAuteur(rs.getString("Auteur"));
            String dateString = rs.getString("Date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = format.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            a.setDate(sqlDate);
            Categorie c = new Categorie();
            c.setId(rs.getInt("categorie_id"));
            c.setNom(rs.getString("categorie_nom"));
            a.setCategorie(c);
            myList.add(a);
        }
             
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}
 
 public void supprimerActualite(Actualite a){
     
    
         try {
             String requete3="DELETE FROM actualite WHERE Id ="+a.getId();
             Statement st= MyConnection.getInstance().getCnx().prepareStatement(requete3);
           
           st.executeUpdate(requete3);
             System.out.println("actualite supprimer!");
         } catch (SQLException ex) {
             System.err.println(ex.getMessage());          }
             
       }
public void modifierActualite(Actualite a, String titre, String image, String contenu, String auteur, Date date, Categorie categorie) {
    try {
        String requete = "UPDATE actualite SET titre=?, image=?, contenu=?, auteur=?, date=?, categorie_id=? WHERE id=?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setString(1, titre);
        ps.setString(2, image);
        ps.setString(3, contenu);
        ps.setString(4, auteur);
        ps.setDate(5, new java.sql.Date(date.getTime()));
        if (categorie != null) {
            int categorieId = getCategorieId(categorie.getNom());
            ps.setInt(6, categorieId);
        } else {
            ps.setNull(6, java.sql.Types.INTEGER);
        }
        ps.setInt(7, a.getId());
        ps.executeUpdate();
        System.out.println("Actualite modifiee !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
} 

public ArrayList<Categorie> getAllCategories() {
    ArrayList<Categorie> categories = new ArrayList<>();
    try {
        String query = "SELECT * FROM categorie";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Categorie categorie = new Categorie();
            categorie.setId(rs.getInt("id"));
            categorie.setNom(rs.getString("nom"));
            categories.add(categorie);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return categories;
}

        
    
        }
/*
        public void listerrActualite(){
           String requete ="SELECT date , COUNT(date) AS nb_actualite   FROM actualite GROUP BY date";
        try {
            PreparedStatement  ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
              ResultSet rs =ps.executeQuery(requete);
              
              while(rs.next()){
              int nb=rs.getInt("nb_actualite");
              
              
             String nbb=rs.getString("date");
              System.out.println("le nombre des acutualite de la date "+nbb+" est :"+nb);
            
             
              }
           
          } catch (SQLException ex) {
          System.out.println(ex.getMessage());
          }
        }*/
