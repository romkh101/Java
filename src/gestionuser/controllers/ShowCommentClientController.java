/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gestionuser.GUI.ListViewCommentClient;
import gestionuser.entities.Commentaire;
import gestionuser.entities.Evenement;
import gestionuser.entities.Participants;
import gestionuser.entities.Vote;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import gestionuser.services.CommentaireService;
import gestionuser.services.EvenementService;
import gestionuser.services.ServiceVote;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
 * @author dell
 */
public class ShowCommentClientController implements Initializable {

    @FXML
    private ListView<Commentaire> listView;
   
    ObservableList<Commentaire> data;
    
    
    EvenementService ds = new EvenementService();

    CommentaireService cs = new CommentaireService();
   
    @FXML
    private TextField contenu;
    @FXML
    private Label nbrLike;
    @FXML
    private Label nbrdeslike;
    @FXML
    private TableView<Evenement> tableView;
    @FXML
    private TableColumn<Evenement, ?> columns;
    @FXML
    private Button btnPDF;
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
              ServiceVote v = new ServiceVote();
         int lik = 0;
        try {
            lik = v.NumLike(ShowEvenementController.idE);
        } catch (SQLException ex) {
            Logger.getLogger(ShowCommentClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
         int des = 0;
        try {
            des = v.NumdeLike(ShowEvenementController.idE);
        } catch (SQLException ex) {
            Logger.getLogger(ShowCommentClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
         nbrLike.setText(String.valueOf(des));
         nbrdeslike.setText(String.valueOf(lik));

        
 
        
        CommentaireService cs = new CommentaireService();
        try {
            data = (ObservableList<Commentaire>) cs.getAllCommentaire();
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowCommentClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listView.setItems(data);
        listView.setCellFactory((ListView<Commentaire> param) -> new ListViewCommentClient());

    }    


    @FXML
    private void commenter(ActionEvent event) throws SQLException {
         
        long millis=System.currentTimeMillis();  
        java.sql.Date d=new java.sql.Date(millis);  
        Commentaire c = new Commentaire();
        //BadWords.loadConfigs();
        
        
        c.setContenue(contenu.getText());
        cs.addCommentaire(c);
        
        
                          try {
            Parent root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/ShowCommentClient.fxml"));
            Stage myWindow =  (Stage) contenu.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
        }
    
          
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLDataException {
                      
            ObservableList<Commentaire> e = listView.getSelectionModel().getSelectedItems();

            
          for (Commentaire m : e) 
        
        cs.deleteCommentaire(m.getId());
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/ShowCommentClient.fxml"));
            Stage myWindow =  (Stage) contenu.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
        }
    }

    @FXML
    private void Like(ActionEvent event) throws SQLException {
        
       ServiceVote sv  = new ServiceVote();
        if (sv.user_vote(1,ShowEvenementController.idE)== null)
        {
            Vote v = new Vote ();
        v.setId_client(1);
        v.setType_vote(1);
        v.setId_evenement(ShowEvenementController.idE);
       sv.addVote(v);
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre vote est bien enregistrer").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/ShowCommentClient.fxml"));
            Stage myWindow =  (Stage) contenu.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(ShowCommentClientController.class.getName()).log(Level.SEVERE,null,ex);
           
        } 
        }
        else
        {
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre avez déja un vote").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();        
        }
    }


    
    @FXML
    private void DesLike(ActionEvent event) throws SQLException {
        
        
        ServiceVote sv  = new ServiceVote();
  if (sv.user_vote(1,ShowEvenementController.idE)== null)
  {
       Vote v = new Vote ();
        v.setId_client(1);
        v.setType_vote(2);
        v.setId_evenement(ShowEvenementController.idE);
        sv.addVote(v);
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre vote est bien enregistrer").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gestionuser/GUI/ShowCommentClient.fxml"));
            Stage myWindow =  (Stage) contenu.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(ShowCommentClientController.class.getName()).log(Level.SEVERE,null,ex);
           
        }
  }
  else
  {
          Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre vote est bien enregistrer").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
  }
       
        
        
    }
    /*
    @FXML
   
    void pdf(ActionEvent event) {

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream("table.pdf"));
            document.open();

            // Define column widths
            float[] columnWidths = new float[tableView.getColumns().size()];
            Arrays.fill(columnWidths, 1f / tableView.getColumns().size());

            // Create PDF table and set column widths
            PdfPTable pdfTable = new PdfPTable(columnWidths.length);
            pdfTable.setWidths(columnWidths);

            // Add table header and rows
            addTableHeader(pdfTable, tableView);
            addRows(pdfTable, tableView);

            document.add(pdfTable);
FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Save PDF");
fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
            File file = fileChooser.showSaveDialog(btnPDF.getScene().getWindow());

//document.save(file);
if (file == null) {
    return;
}

// create a new PDF document
PDDocument documentt = new PDDocument();

// add a new page to the document
PDPage page = new PDPage();
documentt.addPage(page);

// create a new content stream to write to the page
PDPageContentStream contentStream = new PDPageContentStream(documentt, page);
document.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF");

            alert.setHeaderText("PDF");
            alert.setContentText("Enregistrement effectué avec succès!");

            alert.showAndWait();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable pdfTable, TableView<Participants> tableView) {
        for (TableColumn<Participants, ?> column : tableView.getColumns()) {
            PdfPCell header = new PdfPCell();
            header.setPhrase(new com.itextpdf.text.Phrase(column.getText()));
            pdfTable.addCell(header);
        }
    }

private void addRows(PdfPTable pdfTable, TableView<Participants> tableView) {
    for (Participants traitement : tableView.getItems()) {
        pdfTable.addCell(String.valueOf(traitement.getId()));
        pdfTable.addCell(traitement.getNomPart());
        pdfTable.addCell(traitement.getPrenomPart());       
        pdfTable.addCell(traitement.getCin());
        pdfTable.addCell(traitement.getNom_event());
        //pdfTable.addCell(String.valueOf(traitement.getMaladie_id()));
    }
}

    private void addTableHeader(PdfPTable pdfTable, ListView<Commentaire> listView) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addRows(PdfPTable pdfTable, ListView<Commentaire> listView) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
 
   
}
    

    

