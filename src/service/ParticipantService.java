/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evenement;
import entity.Participants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
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
 * @author asus
 */
public class ParticipantService {
   private final Connection cnx;

    private static ParticipantService instance;
    
        public ParticipantService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static ParticipantService getInstance()
    {
        if (instance == null) {
            instance = new ParticipantService();
        }
        return instance; 
    }
    
   
   public void addParticipants(Participants q)throws SQLDataException, SQLException{
         String query ="INSERT INTO `participant`(`cin`,`nomPart`,`prenomPart`,`nom_event`) VALUES (?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getNomPart());
                st.setString(2,q.getPrenomPart());
                st.setString(3,q.getCin());
                st.setString(4,q.getNom_event());
                
                st.executeUpdate();
                //Evenement ev = new Evenement;
                LocalDateTime eventDate = LocalDateTime.of(2023, Month.MAY, 1, 10, 0); // replace with your event date
LocalDateTime currentDate = LocalDateTime.now();
Duration timeDifference = Duration.between(currentDate, eventDate);

                if (timeDifference.toHours() <= 24) {
    Notifications notificationBuilder = Notifications.create()
            .title("Alert")
            .text("Event is coming up soon!")
            .graphic(null)
            //.hideAfter(Duration.seconds(5))
            .position(Pos.CENTER_LEFT)
            .onAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    System.out.println("Notification clicked");
                }
            });
    notificationBuilder.show();
}


        } catch (SQLException ex) {
            Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public boolean ModifierParticipants(Participants e) throws SQLDataException {
                String query = "UPDATE `participant` SET `nomPart`=?,`prenomPart`=?,`cin`=?,`nom_event`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getNomPart());
                st.setString(2,e.getPrenomPart());
                st.setString(3,e.getCin());
                st.setString(4,e.getNom_event());
                
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Participants> getAllParticipants() throws SQLDataException {

        
        List<Participants> list =new ArrayList<Participants>();
        int count =0;
        
        String requete="select * from blog";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Participants e = new Participants();
                e.setCin(rs.getString("cin"));
                e.setNomPart(rs.getString("nomPart"));
                e.setPrenomPart(rs.getString("prenomPart"));
                e.setNom_event(rs.getString("Nom_event"));
                
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
            Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Participants get_ParticipantsById(int i) {
        Participants e = new Participants();
        int nombre = 0;
        String requete = "select * from participant where id="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                e.setId(rs.getInt("id"));
                e.setNomPart(rs.getString("nomPart"));
                e.setPrenomPart(rs.getString("prenomPart"));
                e.setNom_event(rs.getString("nom_event"));
                e.setCin(rs.getString("cin"));
                
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
   
    

    public boolean deleteParticipants(int idCat) throws SQLDataException {
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM participant WHERE id="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

      
}
