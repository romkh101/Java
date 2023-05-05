/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import gestionuser.entities.Evenement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import gestionuser.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AjouterEvenementController implements Initializable {
    int c;
    int file;
    File pDir;
    File pfile;
    String lien;

    EvenementService bs = new EvenementService();
    @FXML
    private TextField titre;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker date;
    @FXML
    private ImageView imv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
            pDir = new File("/gestionuser/GUI/img/evenement" + c + ".jpg");
            lien = "evenement" + c + ".jpg";
    }    
    private void Upload(ActionEvent event) throws MalformedURLException {
        
                
             
                 FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
    
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
    }
    
}
        
    
        
   /* public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }
*/
    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        
        if (titre.getText().equals("") || lieu.getText().equals("")){
       
            Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
           alert.showAndWait();
        
       
       }
       else{
         //copier(pfile, pDir);

        Date dd = new java.sql.Date(new Date(date.getEditor().getText()).getTime());

       Evenement e = new Evenement();
       e.setTitre(titre.getText());
       e.setImage(lien);
       e.setLieu(lieu.getText());
       e.setDateevenet((java.sql.Date) dd);
       bs.addEvenement(e);

       
       
       
       Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/guii/ListEvenement.fxml"));
          Stage myWindow = (Stage) titre.getScene().getWindow();
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
