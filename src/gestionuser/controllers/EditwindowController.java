/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.Actualite;
import gestionuser.entities.Categorie;
import gestionuser.services.ActualiteCRUD;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class EditwindowController implements Initializable {

    @FXML
    private TextField titreA;
    @FXML
    private TextField imageA;
    @FXML
    private TextField contenuA;
    @FXML
    private TextField auteurA;
    @FXML
    private DatePicker dateA;
    @FXML
    private ComboBox<Categorie> categorieA;
    @FXML
    private Button modifierA;
    
    private TableView<Actualite> tableView;
    @FXML
    private Button choose;

    public void setTableView(TableView<Actualite> tableView) {
        this.tableView = tableView;
        
    }
    Actualite actualite = new Actualite();


    /**
     * Initializes the controller class.    
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         actualite = new Actualite();
    }    

    @FXML
    private void editactualite(ActionEvent event) {
     Actualite selectedActualite = tableView.getSelectionModel().getSelectedItem();
    String titre = titreA.getText();
    String image = imageA.getText();
    String contenu = contenuA.getText();
    String auteur = auteurA.getText();
    Date date = Date.valueOf(dateA.getValue());
    Categorie categorie = categorieA.getValue();
try {
    // update the actualite in the database
    ActualiteCRUD crud = new ActualiteCRUD();
    crud.modifierActualite(selectedActualite, titre, image, contenu, auteur, date, categorie);

    // refresh the tableview
    tableView.refresh();

    // close the edit window
    Stage stage = (Stage) modifierA.getScene().getWindow();
    stage.close();
     } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    }
      public void setActualite(Actualite actualite) {
         
   this.actualite = actualite;
    titreA.setText(actualite.getTitre());
    imageA.setText(actualite.getImage());
    contenuA.setText(actualite.getContenu());
    auteurA.setText(actualite.getAuteur());
    dateA.setValue(actualite.getDate().toLocalDate());
    categorieA.setValue(actualite.getCategorie());
}

    @FXML
    private void chooseImage(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        imageA.setText(selectedFile.getAbsolutePath());
    }
    }
      
    
}
