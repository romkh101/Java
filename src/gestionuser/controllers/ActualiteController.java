/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.Actualite;
import gestionuser.entities.Categorie;
import gestionuser.services.ActualiteCRUD;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
/**
 * FXML Controller class
 *
 * @author Aziz
 */

public class ActualiteController implements Initializable {

    @FXML
    private TextField titreA;
    @FXML
    private TextField auteurA;
    @FXML
    private TextField contenuA;
    @FXML
    private DatePicker dateA;
    @FXML
    private ComboBox<Categorie> categorieA;
    @FXML
    private Button btnajouterA;

    private ActualiteCRUD actualiteCRUD;
    @FXML
    private Button choose;
    @FXML
    private ImageView imgview;
    @FXML
    private Label imglabel;
    private Actualite actualite;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Define the folder where images will be stored
String folderPath = "src/gestionuser/images/";

// Create the images folder if it does not exist
File folder = new File(folderPath);
if (!folder.exists()) {
    folder.mkdirs();
}
        
        actualiteCRUD  = new ActualiteCRUD();
        
        List<Categorie> categories = actualiteCRUD.getAllCategories();
        categorieA.getItems().addAll(categories);
        choose.setOnAction(actionEvent -> {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open a file");
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
    File selectedFile = fileChooser.showOpenDialog(null);

if (selectedFile != null) {
    // Set the image label
    imglabel.setText(selectedFile.getName());

    if (selectedFile.getName().endsWith(".jpg") || selectedFile.getName().endsWith(".png")) {
        // This is for displaying an image file
        Image image = new Image(selectedFile.toURI().toString());
        imgview.setImage(image);
        
        // Save the image file to the images folder
        try {
            // Generate a random file name for the image
            // Get the name of the image file
            String imageName = new File(selectedFile.getPath()).getName();

            // Save the image file to the images folder
            File imageFile = new File(folderPath + imageName);
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bImage, "png", imageFile);
            
            // Set the image path to the actualite object
            if (actualite != null) {
    actualite.setImage(imageName);
}
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        // Unsupported file type
        System.out.println("Unsupported file type");
    }
} else {
    System.out.println("No file has been selected");
}
});

        
    }
    
    @FXML
    private void saveactualite(ActionEvent event) {
        String titre = titreA.getText();
        String contenu = contenuA.getText();
        String auteur = auteurA.getText();
         String image = imglabel.getText();
        LocalDate localDate = dateA.getValue();
        Date date = Date.valueOf(localDate);
        Categorie categorie = categorieA.getValue();

        if (titre.isEmpty() || titre.matches(".*[!@#$%^&*(),.?\":{}|<>+_=;/'-]+.*")) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un titre valide (sans caractères spéciaux).");
    alert.showAndWait();
    return;
}

if (contenu.isEmpty() || contenu.length() < 10 || contenu.length() > 500) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un contenu d'actualité valide (entre 10 et 500 caractéres).");
    alert.showAndWait();
    return;
}

if (auteur.isEmpty() || auteur.matches(".*[0-9!@#$%^&*(),.?\":{}|<>+_=;/'-]+.*")) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un auteur valide (sans caractères spéciaux ni chiffres).");
    alert.showAndWait();
    return;
}

/*if (image.isEmpty()) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir une URL d'image.");
    alert.showAndWait();
    return;
}*/

if (localDate == null || localDate.isAfter(LocalDate.now())) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir une date valide (antérieure ou égale à la date actuelle).");
    alert.showAndWait();
    return;
}

if (categorie == null) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une catégorie.");
    alert.showAndWait();
    return;
}

// Tous les champs sont valides, on peut créer l'objet Actualite
Actualite a = new Actualite(titre, image, contenu, auteur, date, categorie);
actualiteCRUD.ajouterActualite(a);

// Afficher un message de confirmation
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Succès");
alert.setHeaderText(null);
alert.setContentText("L'actualité a été ajoutée avec succès.");
alert.showAndWait();

// Effacer les champs de saisie
titreA.clear();
contenuA.clear();
auteurA.clear();
imgview.setImage(null);
dateA.setValue(null);
categorieA.setValue(null);
}
   void choose(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));

        Stage stage = (Stage) choose.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){

                //this is for opening a file
                imglabel.setText(selectedFile.getName());
                Image image = new Image(selectedFile.getPath());
                imgview.setImage(image);

        }else{
            System.out.println("No file has been selected");
        }
    }


}