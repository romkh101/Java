/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.Actualite;
import gestionuser.services.ActualiteCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class ShowfrontController implements Initializable {

    @FXML
    private TableColumn<Actualite, String> imagef;
    @FXML
    private TableColumn<Actualite, String> titref;
    @FXML
    private TableColumn<Actualite, String> categorief;
    @FXML
    private TableView<Actualite> tvF;

    private ActualiteCRUD actualiteCRUD;
    private ObservableList<Actualite> actualites;
    private FilteredList<Actualite> filteredData;
    @FXML
    private TableColumn<?, ?> datef;
    @FXML
    private ComboBox<String> catbox;
    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        try {
            actualiteCRUD = new ActualiteCRUD();
            
            // Initialize the columns
                       imagef.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImage()));
imagef.setCellFactory(column -> new TableCell<Actualite, String>() {
    @Override
    protected void updateItem(String imageName, boolean empty) {
        super.updateItem(imageName, empty);
        if (empty || imageName == null) {
            setGraphic(null);
            
        } else {
            // Load the image file from the images directory
            File file = new File("src/gestionuser/images/" + imageName);
            Image image = new Image(file.toURI().toString());

            // Create an ImageView with the loaded image and set it as the cell's graphic
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setPreserveRatio(true);
            setGraphic(imageView);
        }
    }
});
            titref.setCellValueFactory(new PropertyValueFactory<>("titre"));
            datef.setCellValueFactory(new PropertyValueFactory<>("date"));
            
            categorief.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Actualite, String>, ObservableValue<String>>() {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Actualite, String> data) {
        return new SimpleStringProperty(data.getValue().getCategorie().getNom());
    }
    
});

            actualites = actualiteCRUD.listerActualite();
            tvF.setItems(actualites);
            tvF.setOnMouseClicked(event -> {
    if (event.getClickCount() == 2) {
        handleTableViewMouseClicked(event);
    }
});
            filteredData = new FilteredList<>(actualites);
              ObservableList<String> categories = actualites.stream()
    .map(a -> a.getCategorie().getNom())
    .distinct()
    .sorted()
    .collect(Collectors.toCollection(FXCollections::observableArrayList));
catbox.setItems(categories);
            tvF.refresh();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        catbox.valueProperty().addListener((observable, oldValue, newValue) -> {
    // Met à jour le filtre avec la nouvelle catégorie sélectionnée
    filteredData.setPredicate(actualite -> {
        // Vérifie si la catégorie correspond à la catégorie sélectionnée
        if (newValue == null || newValue.isEmpty()) {
            return true; // Pas de filtre de catégorie
        } else {
            return actualite.getCategorie().getNom().equals(newValue);
        }
    });

    // Rafraîchit la TableView avec les nouvelles données filtrées
    tvF.setItems(filteredData);
});

    }    
    private void handleTableViewMouseClicked(MouseEvent event) {
    if (event.getClickCount() == 2) { // Double-click detected
        Actualite selectedActualite = tvF.getSelectionModel().getSelectedItem();
        if (selectedActualite != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestionuser/GUI/ActualiteView.fxml"));
                Parent root = (Parent) loader.load();
                ActualiteViewController controller = loader.getController();
                controller.setActualite(selectedActualite);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle(selectedActualite.getTitre());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
    
}
