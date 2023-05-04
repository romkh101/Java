/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.GUI;

import gestionuser.entities.Tournoi;
import gestionuser.services.TournoiService;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class TournoiajoutfxController implements Initializable {

    private TextField idbtn;
    @FXML
    private TextField titrebtn;
    @FXML
    private DatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
       TournoiService se= new TournoiService();
        
         
         String titre= titrebtn.getText();
        LocalDate date= datePicker.getValue();
         Tournoi T;
           T = new Tournoi(titre,date);
           se.ajouter_Tournoi(T);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajout d'un Tournoi");
                alert.setHeaderText(null);
                alert.setContentText("Tournoi Ajouté");
                alert.showAndWait();  
        

        
    }

    
    
}
