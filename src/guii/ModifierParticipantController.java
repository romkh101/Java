/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guii;

import entity.Participants;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import service.ParticipantService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierParticipantController implements Initializable {

      
        ParticipantService bs = new ParticipantService();
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField titre_event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        Participants b = bs.get_ParticipantsById(ListParticipantController.idPart);
        System.out.println("ModifierParticipantController.initialize()"+b.toString());

        nom.setText(b.getNomPart());
        prenom.setText(b.getPrenomPart());
        
        
        
    }    

    @FXML
  

   
    private void Ajouter(ActionEvent event) throws SQLException {
        
        
                
        
        if (nom.getText().equals("") || prenom.getText().equals("")|| cin.getText().equals("")|| titre_event.getText().equals("")){
       
            Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
           alert.showAndWait();
        
       
       }
       else{


       Participants e = new Participants();
       e.setPrenomPart(prenom.getText());       
       e.setNomPart(nom.getText());
              e.setCin(cin.getText());
                     e.setNom_event(titre_event.getText());


       e.setId(ListParticipantController.idPart);
       bs.addParticipants(e);

       
       
       
       Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/guii/ListParticipants.fxml"));
          Stage myWindow = (Stage) nom.getScene().getWindow();
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
    
}
