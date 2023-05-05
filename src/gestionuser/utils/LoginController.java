/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.utils;


import gestionuser.services.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Acer
 */
public class LoginController {

    @FXML
    private TextField ai;
    @FXML
    private PasswordField mp;
    @FXML
    private Button sc;
    @FXML
    private Label btnForgot;
    @FXML
    private Button btnFB;
    @FXML
    private Button btnSignup;
    @FXML
    private Label lblErrors;

    @FXML
    private void onClick(ActionEvent event)throws IOException,SQLException {
         UserService us = new UserService();
                String email = ai.getText();
                String mdp = mp.getText();
             
             if ( (mdp.length()>8) && ( validate(email) ) ){
                  
                  if ( us.login(email, mdp)  ) {
                   TrayNotification tr = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tr.setAnimationType(type);
                tr.setTitle("User connecté");
                tr.setMessage("Binevenue");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.showAndDismiss(Duration.millis(5000));
                          GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "GestionUser", "/gestionuser/utils/show.fxml");
                    
               
               
                   
                }
                  else {
                      TrayNotification tr = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tr.setAnimationType(type);
                tr.setTitle("Erreur");
                tr.setMessage("Non Conncecté");
                tr.setNotificationType(NotificationType.ERROR);
                tr.showAndDismiss(Duration.millis(5000));
                  } 
                    
                }else { TrayNotification tr = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tr.setAnimationType(type);
                tr.setTitle("Erreur");
                tr.setMessage("Verifier les champs");
                tr.setNotificationType(NotificationType.ERROR);
                tr.showAndDismiss(Duration.millis(5000));
    }
    
}

     public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
     @FXML
    private void SignUp(ActionEvent event) throws IOException,SQLException {
        GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "GestionUser", "/gestionuser/utils/SignUp.fxml");
    }

    @FXML
    private void goTo(MouseEvent event) throws IOException {
    GuiNavigate nav = new GuiNavigate();
    Node source = (Node) event.getSource();
    ActionEvent newEvent = new ActionEvent(source, source);
    nav.navigate(newEvent, "GestionUser", "/gestionuser/utils/RecupMDP.fxml");
    }

  

 

   
    
}