/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guii;

import entity.Participants;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import service.EvenementService;
import service.ParticipantService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ShowParticipantController implements Initializable {

    ObservableList<Participants> data;
    
    public static int idE ;
    
    EvenementService ds = new EvenementService();
    @FXML
    private ListView<Participants> listView;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ParticipantService cs = new ParticipantService();
            data = (ObservableList<Participants>) cs.getAllParticipants();   
           
            listView.setItems(data);
            listView.setCellFactory((ListView<Participants> param) -> new ListViewParticipant());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    




    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void GoEvenement(ActionEvent event) {
        
               Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/guii/ShowEvenement.fxml"));
          Stage myWindow = (Stage) listView.getScene().getWindow();
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
