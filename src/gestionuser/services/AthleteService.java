/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.services;

import gestionuser.entities.Athlete;
import gestionuser.entities.Tournoi;
import gestionuser.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class AthleteService {
     Connection cnx2;
    public AthleteService(){
        cnx2 = MyConnection.getinstance().getCnx();
    }
    
    
    public void ajouter_Athlete(Athlete r) {
        try {
            String requete1 = "INSERT INTO athlete (name,prenom,age,poids,taille) VALUES(?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete1);
            pst.setString(1, r.getNom());
            pst.setString(2, r.getPrenom());
            pst.setDouble(3, r.getAge());
            pst.setDouble(4, r.getPoids());
            pst.setDouble(5, r.getTaille());
            pst.executeUpdate();
            System.out.println("Athlete ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouter_Athlete_Tornoi(int idT,int idA) {
        try {
            System.out.println(idA+""+idT);
            String requete1 = "INSERT INTO athlete_tournoi (athlete_id,tournoi_id) VALUES(?,?)";
            PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete1);
            pst.setInt(1, idA);
            pst.setInt(2, idT);
            pst.execute();
            System.out.println("Athlete affecter !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Athlete> getAthleteTournoi(int idT) {
        List<Athlete> myList = new ArrayList();
        try {
            String requete2 = "Select * FROM athlete_tournoi WHERE tournoi_id="+idT;
            Statement st = MyConnection.getinstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
            String req = "Select * FROM Athlete WHERE id="+rs.getInt(1);
            Statement stt = MyConnection.getinstance().getCnx().createStatement();
            ResultSet rss = stt.executeQuery(req);

            while (rss.next()) {
                Athlete rec = new Athlete();
                rec.setId(rss.getInt(1));
                rec.setNom(rss.getString("name"));
                rec.setPrenom(rss.getString("prenom"));
                rec.setAge(rss.getInt("age"));
                rec.setPoids(rss.getDouble("poids"));
                rec.setTaille(rss.getDouble("taille"));
                myList.add(rec);
            }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
    
     public void modifier_Athlete(Athlete r,String nom,String prenom,int age,Double poids, Double taille) {
        try {
            String requete4 = " UPDATE Athlete SET " + "  name= ?, prenom = ? ,age = ? , poids  = ?,taille = ? WHERE id= " + r.getId();
            PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete4);
            pst.setString(1, r.getNom());
            pst.setString(2, r.getPrenom());
            pst.setInt(3, r.getAge());
            pst.setDouble(4, r.getPoids());
            pst.setDouble(5, r.getTaille());
            pst.executeUpdate();
            System.out.println("Athlete modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
    
      public void supprimer_Employee(Athlete R) {

        try {
            String requete3 = "DELETE FROM Athlete WHERE id=" + R.getId();
            Statement st = MyConnection.getinstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Athlete supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
      
      
       public List<Athlete> listerAthlete() {
         List<Athlete> myList = new ArrayList();
        try {

            String requete2 = "Select * FROM Athlete";
            Statement st = MyConnection.getinstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Athlete rec = new Athlete();
                rec.setId(rs.getInt(1));
                rec.setNom(rs.getString("name"));
                rec.setPrenom(rs.getString("prenom"));
                rec.setAge(rs.getInt("age"));
                rec.setPoids(rs.getDouble("poids"));
                rec.setTaille(rs.getDouble("taille"));
                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
    public boolean checkParticipent(int idT,int idU) {

        try {
            System.out.println(idT+" "+idU);
            String requete2 = "Select * FROM athlete_tournoi WHERE tournoi_id="+idT+" and athlete_id="+idU;
            Statement st = MyConnection.getinstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            if (!rs.next()){
                return false;
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return true;
    }

      
    
    
}
