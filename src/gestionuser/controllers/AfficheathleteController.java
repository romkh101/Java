/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.Athlete;
import gestionuser.entities.Tournoi;
import gestionuser.services.AthleteService;
import gestionuser.services.TournoiService;
import gestionuser.utils.GuiNavigate;
import gestionuser.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AfficheathleteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    AthleteService t = new AthleteService();
    @FXML
    private TableView<?> tblData;
    @FXML
    private Button statbtn;
    @FXML
    private Button otherButton;
    @FXML
    private Button otherButton2;
    @FXML
    private ComboBox<String> comboid;

    public AfficheathleteController() {
        connection = MyConnection.getinstance().getCnx();

    }
    PreparedStatement preparedStatement;
    Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // create a list of Athlete objects


        
        tblData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            populateTableView(tblData, connection);

            /* fetColumnList();
            fetRowList();*/
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AffichetournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  

// ...

        
        // TODO
    }

    private void affiche(ActionEvent event) {
        List<Athlete> ttt = t.listerAthlete();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("liste de athlete");
        alert.setHeaderText(null);

        // Create a StringBuilder to build the content of the alert
        StringBuilder content = new StringBuilder();
        for (Athlete tt : ttt) {
            // Append user data to the content
            content.append("ID: ").append(tt.getId()).append("\n");
            content.append("ID: ").append(tt.getNom()).append("\n");

            // Append more user data as needed
            content.append("\n"); // Add a newline separator between each user

        }

        // Set the content of the alert
        alert.setContentText(content.toString());

        // Show the alert
        alert.showAndWait();
    }

    /*  public static void populateTableView(TableView tableView, Connection connection) throws SQLException {
        // Clear any existing data in the TableView
        tableView.getItems().clear();

        // Create a statement to execute a query
        Statement statement = connection.createStatement();

        // Execute the query to fetch data from the database
        ResultSet resultSet = statement.executeQuery("SELECT name, prenom,age,poids,taille FROM athlete");

        // Create an ObservableList to hold the data
        ObservableList<Athlete> data = FXCollections.observableArrayList();

        // Loop through the result set and add data to the ObservableList
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String prenom = resultSet.getString("prenom");
            int age = resultSet.getInt("age");
             double poids = resultSet.getDouble("poids");
double taille = resultSet.getDouble("taille");

            

            data.add(new Athlete(name,prenom,age,poids,taille));
        }

        // Set the data in the TableView
        tableView.setItems(data);

        // Define table columns and associate them with data model properties
        TableColumn<Athlete, String> nomColumn = new TableColumn<>("nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Athlete, String> prenomColumn = new TableColumn<>("prenom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
     TableColumn<Athlete, Integer> ageColumn = new TableColumn<>("age");
ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

TableColumn<Athlete, Double> poidsColumn = new TableColumn<>("poids");
poidsColumn.setCellValueFactory(new PropertyValueFactory<>("poids"));

TableColumn<Athlete, Double> tailleColumn = new TableColumn<>("taille");
tailleColumn.setCellValueFactory(new PropertyValueFactory<>("taille"));
        tableView.getColumns().setAll(nomColumn, prenomColumn,ageColumn,poidsColumn,tailleColumn);
       
        
    }*/
    public static void populateTableView(TableView tableView, Connection connection) throws SQLException {
        // Clear any existing data in the TableView
        tableView.getItems().clear();

        // Create a statement to execute a query
        Statement statement = connection.createStatement();

        // Execute the query to fetch data from the database
        ResultSet resultSet = statement.executeQuery("SELECT name, prenom,age,poids,taille FROM athlete");

        // Create an ObservableList to hold the data
        ObservableList<Athlete> data = FXCollections.observableArrayList();

        // Loop through the result set and add data to the ObservableList
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String prenom = resultSet.getString("prenom");
            int age = resultSet.getInt("age");
            double poids = resultSet.getDouble("poids");
            double taille = resultSet.getDouble("taille");

            data.add(new Athlete(name, prenom, age, poids, taille));
        }

        // Set the data in the TableView
        tableView.setItems(data);

        // Define table columns and associate them with data model properties
        TableColumn<Athlete, String> nomColumn = new TableColumn<>("nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Athlete, String> prenomColumn = new TableColumn<>("prenom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Athlete, Integer> ageColumn = new TableColumn<>("age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Athlete, Double> poidsColumn = new TableColumn<>("poids");
        poidsColumn.setCellValueFactory(new PropertyValueFactory<>("poids"));

        TableColumn<Athlete, Double> tailleColumn = new TableColumn<>("taille");
        tailleColumn.setCellValueFactory(new PropertyValueFactory<>("taille"));
        tableView.getColumns().setAll(nomColumn, prenomColumn, ageColumn, poidsColumn, tailleColumn);

        // Add the update and delete buttons to the TableView
        TableColumn<Athlete, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<Athlete, Void>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");

            {
                updateButton.setOnAction(event -> {
                    // Get the selected user from the TableView
                    Athlete athlete = getTableView().getItems().get(getIndex());

                    // Show a dialog to edit the user's data
                    Dialog<Athlete> dialog = new Dialog<>();
                    dialog.setTitle("Edit Athlete");

                    // Create text fields for editing the user's data
                    TextField nomTextField = new TextField(athlete.getNom());
                    TextField prenomTextField = new TextField(athlete.getPrenom());
                    TextField ageTextField = new TextField(String.valueOf(athlete.getAge()));
                    TextField poidsTextField = new TextField(athlete.getPoids().toString());
                    TextField tailleTextField = new TextField(athlete.getTaille().toString());
                    // Create a grid pane to layout the dialog controls
                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.addRow(0, new Label("Nom:"), nomTextField);
                    gridPane.addRow(1, new Label("Prenom:"), prenomTextField);
                    gridPane.addRow(2, new Label("age:"), ageTextField);
                    gridPane.addRow(2, new Label("poids:"), poidsTextField);
                    gridPane.addRow(2, new Label("taille:"), tailleTextField);

                    dialog.getDialogPane().setContent(gridPane);

                    // Add buttons to the dialog
                    ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

                    // Convert the dialog result to a user object
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == updateButtonType) {
                            int age = Integer.parseInt(ageTextField.getText());
                            double poids = Double.parseDouble(poidsTextField.getText());
                            double taille = Double.parseDouble(tailleTextField.getText());

                            return new Athlete(nomTextField.getText(), prenomTextField.getText(), age, poids, taille);
                        }
                        return null;
                    });

                    Optional<Athlete> result = dialog.showAndWait();

                    // If the user clicks the update button, update the user's data in the database
                    result.ifPresent(newAthleteData -> {
                        try {
                            PreparedStatement statement = connection.prepareStatement("UPDATE athlete SET name=?,prenom=?, age=?, poids=?,taille=? WHERE name=?");
                            statement.setString(1, newAthleteData.getNom());
                            statement.setString(2, newAthleteData.getPrenom());
                            statement.setInt(3, newAthleteData.getAge());
                            statement.setDouble(4, newAthleteData.getPoids());
                            statement.setDouble(5, newAthleteData.getTaille());
                            statement.setString(6, athlete.getNom());

                            statement.executeUpdate();
                            populateTableView(tableView, connection);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                });

                deleteButton.setOnAction(event -> {
                    // Get the selected user from the TableView
                    Athlete athlete = getTableView().getItems().get(getIndex());

                    // Show a confirmation dialog before deleting the user
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Athlete");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this athlete?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        try {
                            PreparedStatement statement = connection.prepareStatement("DELETE FROM athlete WHERE name=?");
                            statement.setString(1, athlete.getNom());
                            statement.executeUpdate();
                            populateTableView(tableView, connection);
                            /*   TrayNotification tr = new TrayNotification();
                            AnimationType type = AnimationType.POPUP;

                            tr.setAnimationType(type);
                            tr.setTitle("supprimer");
                            tr.setMessage("Utilisateur supprimé");
                            tr.setNotificationType(NotificationType.SUCCESS);
                            tr.showAndDismiss(Duration.millis(5000));*/

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox();
                    hbox.setSpacing(10);
                    hbox.getChildren().addAll(updateButton, deleteButton);
                    setGraphic(hbox);
                }
            }
        });

        tableView.getColumns().add(actionColumn);
    }


    @FXML
    private void statistiques(ActionEvent event) throws IOException {
        GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "GestionUser", "/gestionuser/GUI/statistqueathlete.fxml");

    }
    @FXML
    private void ajoutt(ActionEvent event) throws IOException {
        GuiNavigate nav = new GuiNavigate();
       nav.navigate(event, "GestionUser", "/gestionuser/GUI/Athleteajoutfx.fxml");

    }
@FXML
    private void tournoi(ActionEvent event) throws IOException {
        GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "GestionUser", "/gestionuser/GUI/affichetournoi.fxml");

    }

    @FXML
    private void relation(ActionEvent event) {
       
// Get the selected athlete
    Athlete selectedAthlete = (Athlete) tblData.getSelectionModel().getSelectedItem();
    if (selectedAthlete == null) {
        // No athlete is selected, show an alert and return
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucun athlète sélectionné");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un athlète pour l'ajouter à un tournoi.");
        alert.showAndWait();
        return;
    }

    // Show a dialog to select a tournoi to add the athlete to
    Dialog<Tournoi> dialog = new Dialog<>();
    dialog.setTitle("Sélectionner un tournoi");
    dialog.setHeaderText(null);

    // Add the cancel button to the dialog
    ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().addAll(cancelButton);

    // Create a grid pane to hold the form controls
    GridPane formGrid = new GridPane();
    formGrid.setHgap(10);
    formGrid.setVgap(10);
    formGrid.setPadding(new javafx.geometry.Insets(20));

    // Add the tournoi combobox to the grid
    ComboBox<Tournoi> tournoiComboBox = new ComboBox<>();
TournoiService tournoiService = new TournoiService();
tournoiComboBox.setItems(FXCollections.observableArrayList(tournoiService.listerTournoi()));
    tournoiComboBox.setPromptText("Sélectionner un tournoi");
    formGrid.addRow(0, new Label("Tournoi :"), tournoiComboBox);

    dialog.getDialogPane().setContent(formGrid);

    // Request focus on the tournoi combobox when the dialog is shown
    dialog.setOnShown(e -> tournoiComboBox.requestFocus());

    // Wait for the user to close the dialog
    Optional<Tournoi> result = dialog.showAndWait();
    if (!result.isPresent()) {
        // The user cancelled the dialog, return
        return;
    }

    // Get the selected tournoi
    Tournoi selectedTournoi = result.get();

    // Add the athlete to the selected tournoi
    selectedTournoi.addAthlete(selectedAthlete);
    TournoiService.modifierTournoi(selectedTournoi);

    // Show a success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Athlète ajouté au tournoi");
    alert.setHeaderText(null);
    alert.setContentText("L'athlète " + selectedAthlete.getNom() + " " + selectedAthlete.getPrenom() + " a été ajouté au tournoi " + selectedTournoi.getTitre() + ".");
    alert.showAndWait();
    }}
