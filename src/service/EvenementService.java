/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
import utils.DataSource;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class EvenementService {
    
     private final Connection cnx;

    private static EvenementService instance;
    
        public EvenementService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static EvenementService getInstance()
    {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance; 
    }
    
   public void addEvenement(Evenement q)throws SQLDataException, SQLException{
         String query ="INSERT INTO `evenement`(`titreevenement`,`imageevenement`,`dateevenement`,`lieuevenement`) VALUES (?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getTitre());
                st.setString(2,q.getImage());
                st.setString(4,q.getLieu());
                st.setDate(3,q.getDateevenet());
                
                st.executeUpdate();
               Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("e=Evennement Ajouter Avec Sucsess").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
        notificationBuilder.darkStyle();
        notificationBuilder.show();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public boolean ModifierEvenement(Evenement e) throws SQLDataException {
                String query = "UPDATE `evenement` SET `titreevenement`=?,`imageevenement`=?,`dateevenement`=?,`lieuevenement`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getTitre());
                st.setString(2,e.getImage());
                st.setDate(3,e.getDateevenet());
                st.setString(4,e.getLieu());
                st.setInt(5,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Evenement> getAllEvenement() throws SQLDataException {

        
        List<Evenement> list =new ArrayList<Evenement>();
        int count =0;
        
        String requete="select * from evenement";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Evenement e = new Evenement();
                e.setDateevenet(rs.getDate("dateevenement"));
                e.setImage(rs.getString("imageevenement"));
                e.setLieu(rs.getString("lieuevenement"));
                e.setTitre(rs.getString("titreevenement"));
                e.setId(rs.getInt("id"));
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Evenement get_EvenementById(int i) {
        Evenement e = new Evenement();
        int nombre = 0;
        String requete = "select * from evenement where id="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                e.setDateevenet(rs.getDate("dateevenement"));
                e.setImage(rs.getString("imageevenement"));
                e.setLieu(rs.getString("lieuevenement"));
                e.setTitre(rs.getString("titreevenement"));
                e.setId(rs.getInt("id"));
                
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
   
    

    public boolean deleteEvenement(int idCat) throws SQLDataException {

        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM evenement WHERE id="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
}
