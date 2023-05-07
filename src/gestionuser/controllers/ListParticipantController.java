/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.Participants;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import gestionuser.services.ParticipantService;


/**
 *
 * @author asus
 */
class ListParticipantController {
      @FXML
    private TableView<Participants> table;
    @FXML
    private TableColumn<Participants, String> nom;
      @FXML
    private TableColumn<Participants, String> prenom;
    @FXML
    private TableColumn<Participants, String> cin;
    @FXML
    private TableColumn<Participants, String> nom_event;
    private ObservableList<Participants> UserData = FXCollections.observableArrayList();
    
    ParticipantService cs = new ParticipantService();
    
    static int idPart ;
    
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) throws SQLDataException {
        List<Participants> listb= new ArrayList<Participants>();
        listb = cs.getAllParticipants();
        UserData.clear();
        UserData.addAll(listb);
        table.setItems(UserData);
        nom.setCellValueFactory
                              (
                                      new PropertyValueFactory<>("nom")
                              );
        prenom.setCellValueFactory
                              (
                                      new PropertyValueFactory<>("prenom")
                              );
        cin.setCellValueFactory
                              (
                                      new PropertyValueFactory<>("cin")
                              );
          nom_event.setCellValueFactory
                              (
                                      new PropertyValueFactory<>("nom_event")
                              );
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
       Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/AjouterParticipant.fxml"));
          Stage myWindow = (Stage) table.getScene().getWindow();
          Scene sc = new Scene(root);
          myWindow.setScene(sc);
          myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
          myWindow.show();
          } catch (IOException ex) {
          Logger.getLogger(AjouterParticipantController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
        
                
        //cs.deleteParticipants(table.getSelectionModel().getSelectedItem().getId());
        resetTableData();
        
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
          String idPart = table.getSelectionModel().getSelectedItem().getId();     

               Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/ModifierParticipant.fxml"));
          Stage myWindow = (Stage) table.getScene().getWindow();
          Scene sc = new Scene(root);
          myWindow.setScene(sc);
          myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
          myWindow.show();
          } catch (IOException ex) {
          Logger.getLogger(AjouterParticipantController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    
    
        public void resetTableData() throws SQLDataException
    {
        List<Participants> lisre = new ArrayList<>();
        lisre = cs.getAllParticipants();
        ObservableList<Participants> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }

    @FXML
    private void GoEvenement(ActionEvent event) {
        
               Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/ListEvenement.fxml"));
          Stage myWindow = (Stage) table.getScene().getWindow();
          Scene sc = new Scene(root);
          myWindow.setScene(sc);
          myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
          myWindow.show();
          } catch (IOException ex) {
          Logger.getLogger(AjouterParticipantController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }
    
    
    
}
 

