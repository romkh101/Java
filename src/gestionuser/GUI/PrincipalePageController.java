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
public class PrincipalePageController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button logout;

    public BorderPane getBorderPane() {
        return borderPane;
    }
    @FXML
    private Button GestionTranBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    @FXML
    private void actualItE(ActionEvent event) {
              try {
                  
            AnchorPane view = FXMLLoader.load(getClass().getResource("showfront.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Evenement(ActionEvent event) {
           try {
                  
            AnchorPane view = FXMLLoader.load(getClass().getResource("ShowEvenement.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void participant(ActionEvent event) {
          try {
                  
            AnchorPane view = FXMLLoader.load(getClass().getResource("ShowParticipant.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void Athlete(ActionEvent event) {
          try {
                  
            AnchorPane view = FXMLLoader.load(getClass().getResource("showathletee.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Tournoi(ActionEvent event) {
        try {
                  
            AnchorPane view = FXMLLoader.load(getClass().getResource("affichetournoi.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Reclamation(ActionEvent event) {
    }

    @FXML
    private void Message(ActionEvent event) {
    }

    @FXML
    private void Sport(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         Session.getInstance().invalidate();

    // Navigate to the login page
    GuiNavigate nav = new GuiNavigate();
    nav.navigate(event, "GestionUser", "/gestionuser/GUI/Login.fxml");  
    }
    
}
