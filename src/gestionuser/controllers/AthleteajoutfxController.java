/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.Athlete;
import gestionuser.entities.Tournoi;
import gestionuser.services.AthleteService;
import gestionuser.services.TournoiService;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AthleteajoutfxController implements Initializable {
@FXML
private TextField namebtn;
    @FXML
    private TextField prenombtn;
    @FXML
    private TextField agebtn;
     @FXML
    private TextField poidsbtn;
      @FXML
    private TextField taillebtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void ajouterr(ActionEvent event) {
        AthleteService se= new AthleteService();
        
         
         String nom= namebtn.getText();
         String prenom= prenombtn.getText();
        int age = Integer.parseInt(agebtn.getText());
        Double poids = Double.parseDouble(poidsbtn.getText());
     Double taille = Double.parseDouble(taillebtn.getText());
         Athlete  A = new Athlete (nom,prenom,age,poids,taille);
           se.ajouter_Athlete(A);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajout d'un Athlete");
                alert.setHeaderText(null);
                alert.setContentText("Athlete Ajouté");
                alert.showAndWait();
    }}
