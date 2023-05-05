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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class TournoiService {

    public static void modifierTournoi(Tournoi selectedTournoi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Connection cnx2;

    public TournoiService() {
        cnx2 = MyConnection.getinstance().getCnx();
    }

    public void ajouter_Tournoi(Tournoi T) {
        try {
            String requete1 = "INSERT INTO Tournoi (titre,date) VALUES(?,?)";
            PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete1);
            pst.setString(1, T.getTitre());
            LocalDate localDate = T.getDateTournoi();
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
            pst.setDate(2, sqlDate);

            pst.executeUpdate();
            System.out.println("Tournoi ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier_Tournoi(Tournoi r, String tire, Date dateTournoi) {
        try {
            String requete4 = " UPDATE Tournoi SET " + "  titre= ?, dateTournoi = ?  WHERE id= " + r.getId();
            PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete4);
            pst.setString(1, r.getTitre());
            pst.setDate(2, new java.sql.Date(r.getDateTournoi().getTime()));
            pst.executeUpdate();
            System.out.println("Tournoi modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer_Tournoi(Tournoi R) {

        try {
            String requete3 = "DELETE FROM Tournoi WHERE id=" + R.getId();
            Statement st = MyConnection.getinstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Tournoi supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Tournoi> listerTournoi() {
        List<Tournoi> myList = new ArrayList();
        try {

            String requete2 = "Select * FROM Tournoi";
            Statement st = MyConnection.getinstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Tournoi rec = new Tournoi();
                rec.setId(rs.getInt(1));
                rec.setTitre(rs.getString("titre"));
                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }

}
