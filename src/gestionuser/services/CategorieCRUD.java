/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.services;

import gestionuser.entities.Categorie;
import gestionuser.MyConnection.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Aziz
 */
public class CategorieCRUD {
     public  void ajouterCategorie(Categorie c)
        { 
                  String requete="INSERT INTO categorie (nom)VALUES(?)";
              

          try {
          PreparedStatement pst=MyConnection.getInstance().getCnx().prepareStatement(requete);
          pst.setString(1,c.getNom());
         
             // System.out.println(r.getCategorie().toString());
              
           pst.executeUpdate();
          System.out.println("categorie Ajouteé!");
                 
        } catch (SQLException ex) {
                  System.out.println(ex.getMessage());}
            
       
         
        }
      

public ObservableList<Categorie> listerCategorie() throws ParseException{
      
    ObservableList<Categorie> myList= FXCollections.observableArrayList();
      
    try {
        String requete="SELECT * FROM categorie";
        Statement st= MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);

        while(rs.next()){
            Categorie c = new Categorie();
            c.setId(rs.getInt(1));
            c.setNom(rs.getString("Nom"));

            myList.add(c);
        }
             
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}
 
public void supprimerCategorie(Categorie c) {
    try {
        String requete3 = "DELETE FROM categorie WHERE Id = ?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete3);
        ps.setInt(1, c.getId());
        ps.executeUpdate();
        System.out.println("categorie supprimer!");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
      public void modifierCategorie(Categorie c, String nom) {
    try {
        String requete = "UPDATE categorie SET nom=? WHERE id=?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setString(1, nom);
        ps.setInt(2, c.getId());
        ps.executeUpdate();
        System.out.println("Categorie modifiee !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
         public boolean isCategorieUsedInActualite(Categorie categorie) {
    try {
        String query = "SELECT COUNT(*) FROM actualite WHERE categorie_id = ?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(query);
        ps.setInt(1, categorie.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return (count > 0);
        }
        return false;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return false;
    }
}
}
