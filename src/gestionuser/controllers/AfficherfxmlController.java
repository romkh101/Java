/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.User;
import gestionuser.services.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class AfficherfxmlController implements Initializable {

    
        UserService userser= new UserService();
    @FXML
    private Button btnbutton;
    @FXML
    private Button btnparnom;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherUtilisateurs(ActionEvent event) {
          List<User> userList=userser.listerUsers();
            Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("User List");
        alert.setHeaderText(null);
        
        // Create a StringBuilder to build the content of the alert
        StringBuilder content = new StringBuilder();
        for (User user : userList) {
            // Append user data to the content
            content.append("ID: ").append(user.getId()).append("\n");
            content.append("Nom: ").append(user.getNom()).append("\n");
            content.append("Prenom: ").append(user.getPrenom()).append("\n");
            // Append more user data as needed
            content.append("\n"); // Add a newline separator between each user
            
        }
        
        // Set the content of the alert
        alert.setContentText(content.toString());
        
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    private void AfficherUtilisateursParNom(ActionEvent event) {
     List<User> userList=userser.listerUsersparNom("mansour");
            Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("User List");
        alert.setHeaderText(null);
        
        // Create a StringBuilder to build the content of the alert
        StringBuilder content = new StringBuilder();
        
        for (User user : userList) {
            // Append user data to the content
            content.append("ID: ").append(user.getId()).append("\n");
            content.append("Nom: ").append(user.getNom()).append("\n");
            content.append("Prenom: ").append(user.getPrenom()).append("\n");
            // Append more user data as needed
            content.append("\n"); // Add a newline separator between each user
            
        }
        
        // Set the content of the alert
        alert.setContentText(content.toString()+"le nombre des users ayant le nom mansour est :"+userList.size());
        
        // Show the alert
        alert.showAndWait();
    }


   
    
}
