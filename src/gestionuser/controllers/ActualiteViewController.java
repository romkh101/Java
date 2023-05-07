/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.Actualite;

import javafx.print.PrinterJob;
import java.awt.print.PrinterException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;



/**
 * FXML Controller class
 *
 * @author Aziz
 */

public class ActualiteViewController implements Initializable {

    @FXML
    private WebView webView;
    private WebEngine webEngine;
    @FXML
    private Button imprimer;

    /**
     * Initializes the controller class.
     */
public void setActualite(Actualite actualite) {
    webEngine = webView.getEngine();

    String imagePath = "src/gestionuser/images/" + actualite.getImage();
    File file = new File(imagePath);
    String imageUri = file.toURI().toString();

    String htmlContent = "<div style='text-align:center;'>" +
            "<img src=\"" + imageUri + "\" style='max-width:100%;'>" +
            "<h1 style='text-align:center;'>" + actualite.getTitre() + "</h1>" +
            "<p style='text-align:center;'>" +
                "<div style='text-align:center; background-color:orange;'>" +
                "<span style='color:white; padding:5px;'>"+ actualite.getCategorie().getNom() +"</span>" +
            "</div>" +
                "<span style='color:blue;'>"+ actualite.getAuteur() +"</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                "<span style='color:gray;'>"+ actualite.getDate() +"</span>" +
            "</p>" +
            "<hr>" +
            "<p>" + wrapText(actualite.getContenu(), 75) + "</p>" +
            "<div style='text-align:center;'>" +
                "<img src='file:/C:\\Users\\Acer\\Documents\\NetBeansProjects\\GestionUser - Copie\\src\\gestionuser\\images\\abcspor.png' style='width:80px;height:50px;'/>"  +
                "<br>" +
                "<span style='font-size:small; color:gray;'>Copyright © 2023-abc sports, Inc. All rights reserved.</span>" +
            "</div>" +
            "</div>";

    webEngine.loadContent(htmlContent);
}
private String wrapText(String text, int maxCharactersPerLine) {
    String[] words = text.split(" ");
    StringBuilder sb = new StringBuilder();
    int lineLength = 0;
    for (String word : words) {
        if (lineLength + word.length() > maxCharactersPerLine) {
            sb.append("\n");
            lineLength = 0;
        }
        sb.append(word + " ");
        lineLength += word.length() + 1;
    }
    return sb.toString();
}



     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
private void handleImprimer(ActionEvent event) throws PrinterException {
 PrinterJob printerJob = PrinterJob.createPrinterJob();
    if (printerJob != null && printerJob.showPrintDialog(webView.getScene().getWindow())) {
        webView.getEngine().print(printerJob);
        printerJob.endJob();
}
}
}
