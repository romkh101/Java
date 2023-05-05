/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.GUI;

import gestionuser.utils.GuiNavigate;
import gestionuser.utils.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/**
 * FXML Controller class
 *
 * @author pc
 */
public class PrincipalePageClientController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button ReservationBtn;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("show.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }    }    

 

   

    @FXML
    private void user(ActionEvent event) {
          try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("show.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void part(ActionEvent event) {
           try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("ListParticipant.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void event(ActionEvent event) {
           try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("ListEvenement.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Athlete(ActionEvent event) {
         try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("afficheathlete.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Tournoi(ActionEvent event) {
       try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("Admintournoishow.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         Session.getInstance().invalidate();

    // Navigate to the login page
    GuiNavigate nav = new GuiNavigate();
    nav.navigate(event, "GestionUser", "/gestionuser/GUI/Login.fxml");  
    }
    
    
}
