/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.GUI.ListViewParticipant;
import gestionuser.entities.Evenement;
import gestionuser.entities.Participants;
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
import gestionuser.services.EvenementService;
import gestionuser.services.ParticipantService;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

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
    @FXML
    private Button btnPDF;

   

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
          root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/ShowEvenement.fxml"));
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

       
    @FXML
    private void handlePDF(ActionEvent event) throws IOException {
    Participants selectedParticipant = listView.getSelectionModel().getSelectedItem();
    if (selectedParticipant == null) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner un participant.");
    alert.showAndWait();
    return;
}

// prompt the user to choose a file path and name for the PDF file
FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Save PDF");
fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
File file = fileChooser.showSaveDialog(btnPDF.getScene().getWindow());

// the user cancelled the file save dialog
if (file == null) {
    return;
}

// create a new PDF document
PDDocument document = new PDDocument();

// add a new page to the document
PDPage page = new PDPage();
document.addPage(page);

// create a new content stream to write to the page
PDPageContentStream contentStream = new PDPageContentStream(document, page);

// get the image data from the selected actualite
/*String imagePath = "src/images/" + selectedActualite.getImage();
String logoPath = "src/images/abcspor.png";
BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
BufferedImage bufferedLogo = ImageIO.read(new File(logoPath));*/

try {
    // convert the bufferedImage to a PDImageXObject
  /*  PDImageXObject pdImage = LosslessFactory.createFromImage(document, bufferedImage);
    PDImageXObject logoImage = LosslessFactory.createFromImage(document, bufferedLogo);

    // draw the image onto the page
    contentStream.drawImage(pdImage, 50, 500, 500, 300);
    contentStream.drawImage(logoImage, 250, 30, 100, 50);*/

    // set the font and font size for the title
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);

    // write the title of the actualite to the page
    contentStream.beginText();
    contentStream.newLineAtOffset(50, 480);
    contentStream.showText(selectedParticipant.getNomPart());
    contentStream.endText();

    
   
        // set the font and font size for the content
   contentStream.setFont(PDType1Font.HELVETICA, 14);
    // write the content of the actualite to the page
contentStream.beginText();
contentStream.newLineAtOffset(50, 440);

String contenu = selectedParticipant.getPrenomPart();
int longueurLigne = 75;
for (int i = 0; i < contenu.length(); i += longueurLigne) {
    int finLigne = Math.min(i + longueurLigne, contenu.length());
    String ligne = contenu.substring(i, finLigne);
    contentStream.showText(ligne);
    contentStream.newLineAtOffset(0, -20);
}

contentStream.endText();
//categorie
// set the font and font size for the category
contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

// set the fill color to orange for the category rectangle
contentStream.setNonStrokingColor(255, 165, 0);

// draw a rectangle behind the category text
contentStream.addRect(50, 455, 70, 20);
contentStream.fill();

// write the category of the actualite to the page
contentStream.setNonStrokingColor(0, 0, 0);
contentStream.beginText();
contentStream.newLineAtOffset(55, 460);
contentStream.showText(selectedParticipant.getCin());
contentStream.endText();

// set the font and font size for the author
contentStream.setFont(PDType1Font.HELVETICA, 12);

// set the fill color to blue for the author text
contentStream.setNonStrokingColor(0, 0, 255);

// write the author of the actualite to the page
contentStream.beginText();
contentStream.newLineAtOffset(130, 460);
contentStream.showText(selectedParticipant.getNom_event());
contentStream.endText();

// set the font and font size for the date
contentStream.setFont(PDType1Font.HELVETICA, 12);

// set the fill color to gray for the date text
contentStream.setNonStrokingColor(128, 128, 128);

// write the date of the actualite to the page
/*contentStream.beginText();
contentStream.newLineAtOffset(200, 460);
contentStream.showText("le " + selectedActualite.getDate().toString());
contentStream.endText();
    */



    // footer

    contentStream.beginText();
    contentStream.newLineAtOffset(150, 10);
    contentStream.showText("Copyright © 2023-abc sports, Inc. All rights reserved.");
    contentStream.endText();

        // close the content stream before saving the document
        contentStream.close();

        // save the PDF document to the chosen file path
        document.save(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
}
