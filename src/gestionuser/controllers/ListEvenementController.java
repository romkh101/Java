/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;


import gestionuser.entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import gestionuser.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class ListEvenementController implements Initializable {

    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement,Date> date;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String> lieu;

    private ObservableList<Evenement> UserData = FXCollections.observableArrayList();
    
    EvenementService cs = new EvenementService();
    
    static int idEvent ;
    @FXML
    private TextField rechercher;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              try {
            List<Evenement> listb= new ArrayList<Evenement>();
            
            listb = cs.getAllEvenement();
     
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            lieu.setCellValueFactory
                      (
            new PropertyValueFactory<>("lieu")
                      );
            titre.setCellValueFactory
                      (
            new PropertyValueFactory<>("titre")
                      );
            date.setCellValueFactory
                      (
            new PropertyValueFactory<>("dateevenet")
                      );
        } catch (SQLDataException ex) {
            Logger.getLogger(ListParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
       Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/AjouterEvenement.fxml"));
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
        
                
        cs.deleteEvenement(table.getSelectionModel().getSelectedItem().getId());
        resetTableData();
        
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        idEvent  =  table.getSelectionModel().getSelectedItem().getId();     

               Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/ModifierEvenement.fxml"));
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
        List<Evenement> lisre = new ArrayList<>();
        lisre = cs.getAllEvenement();
        ObservableList<Evenement> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }

    @FXML
    private void rechercher(ActionEvent event) throws SQLDataException {
        
              
            lieu.setCellValueFactory
                      (
            new PropertyValueFactory<>("lieu")
                      );
            titre.setCellValueFactory
                      (
            new PropertyValueFactory<>("titre")
                      );
            date.setCellValueFactory
                      (
            new PropertyValueFactory<>("dateevenet")
                      );
                  
            List<Evenement> list = cs.getAllEvenement();
            
            //tableview.setItems(observablelist);
            
            FilteredList<Evenement> filtredData= new FilteredList<>(UserData, b ->true);
            rechercher.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super Evenement> Reservation;
                filtredData.setPredicate((Evenement e) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();
                    if(e.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (e.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Evenement> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
        
        
        
        
        
        
        
        
        
    }
        
}
    
