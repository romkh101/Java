/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.Categorie;
import gestionuser.services.CategorieCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class AddcategorieController implements Initializable {

    @FXML
    private TextField nomC;
    @FXML
    private Button ajouterC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void ajoutercategorie(ActionEvent event) {
         String nom = nomC.getText();
         if (nom.isEmpty() || nom.matches(".*[0-9!@#$%^&*(),.?\":{}|<>+_=;/'-]+.*")) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un nom valide (sans caractères spéciaux ni chiffres).");
    alert.showAndWait();
    return;
}
        Categorie c = new Categorie(nom);
        CategorieCRUD categorieCRUD = new CategorieCRUD();
        categorieCRUD.ajouterCategorie(c);

       Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Succès");
alert.setHeaderText(null);
alert.setContentText("L'actualité a été ajoutée avec succès.");
alert.showAndWait();

nomC.clear();
    }
    
}
