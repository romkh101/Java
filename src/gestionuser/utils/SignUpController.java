/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.utils;

import gestionuser.entities.User;
import gestionuser.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField nombtn;
    @FXML
    private TextField prenombtn;
    @FXML
    private TextField imagebtn;
    @FXML
    private TextField emailbtn;
    @FXML
    private TextField passwordbtn;
    UserService us = new UserService();
    @FXML
    private Button eyeButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombtn.setStyle("-fx-text-fill: white;");
        prenombtn.setStyle("-fx-text-fill: white;");
        imagebtn.setStyle("-fx-text-fill: white;");
        emailbtn.setStyle("-fx-text-fill: white;");
        passwordbtn.setStyle("-fx-text-fill: white;");            
        // TODO
    }
    private boolean validateInputs() {
        if (nombtn.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre Prenom");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if ((prenombtn.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre Nom");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((imagebtn.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
         
        } else if ((emailbtn.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
       
        } else if (passwordbtn.getText().length() < 8) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Mot de passe doit dépasser les 6 caractères");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        }   
        return true;
} 


    @FXML
    private void register(ActionEvent event) {
        if (!validateInputs()); 
        else {
            try{
            User u1 = new User();
            u1.setNom(nombtn.getText());
            u1.setPrenom(prenombtn.getText());
            u1.setImageFile(imagebtn.getText());
            u1.setEmail(emailbtn.getText());
            u1.setPassword(passwordbtn.getText());
             TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("add");
            tr.setMessage("Created succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndWait();       
          
            System.out.println(u1);
            us.Register(u1);}
            catch(Exception e){
               System.out.println(e.getMessage());
            }
        }
    }

}
