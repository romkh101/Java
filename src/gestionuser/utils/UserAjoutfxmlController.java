/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.utils;

import gestionuser.entities.User;
import gestionuser.services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class UserAjoutfxmlController implements Initializable {

    @FXML
    private TextField prenombtn;
    @FXML
    private TextField emailbtn;
    @FXML
    private TextField passwordbtn;
    @FXML
    private Button buttonbtn;
    @FXML
    private TextField nombtn;
    @FXML
    private TextField resettokenbtn;
    @FXML
    private TextField imagefilebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
         UserService ser= new UserService();
        
         String prenom= prenombtn.getText();
         String email= emailbtn.getText();
         String password= passwordbtn.getText();
         String resettoken=resettokenbtn.getText();
         String imagefile=imagefilebtn.getText();
         String nom=nombtn.getText();
         
         
         User U;
           U = new User(nom,prenom,email,password,resettoken,imagefile);
           ser.ajouter_User(U);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajout d'un utilisateur");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur ajouté");
                alert.showAndWait();  
    }
    }
    

