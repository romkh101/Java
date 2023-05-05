/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.GUI;

import gestionuser.controllers.AffichetournoiController;
import static gestionuser.controllers.AffichetournoiController.populateTableView;
import gestionuser.entities.Tournoi;
import gestionuser.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class AdmintournoishowController implements Initializable {

    @FXML
    private TableView<?> tblData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tblData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            populateTableView(tblData,connection);
            
            /* fetColumnList();
            fetRowList();*/
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AffichetournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     public AdmintournoishowController() {
       connection = MyConnection.getinstance().getCnx();

    }
    PreparedStatement preparedStatement;
    Connection connection;
    public static void populateTableView(TableView tableView, Connection connection) throws SQLException {
        // Clear any existing data in the TableView
        tableView.getItems().clear();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // or SelectionMode.MULTIPLE

        // Create a statement to execute a query
        Statement statement = connection.createStatement();

        // Execute the query to fetch data from the database
        ResultSet resultSet = statement.executeQuery("SELECT id ,titre, date FROM tournoi");

        // Create an ObservableList to hold the data
        ObservableList<Tournoi> data = FXCollections.observableArrayList();

        // Loop through the result set and add data to the ObservableList
        while (resultSet.next()) {
            String titre = resultSet.getString("titre");
            Date date = resultSet.getDate("date");
            int id = resultSet.getInt("id");

            data.add(new Tournoi(id, titre, date));
        }

        // Set the data in the TableView
        tableView.setItems(data);

        // Define table columns and associate them with data model properties
        TableColumn<Tournoi, String> nomColumn = new TableColumn<>("titre");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));

        TableColumn<Tournoi, LocalDate> prenomColumn = new TableColumn<>("date");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("dateTournoi"));

        tableView.getColumns().setAll(nomColumn, prenomColumn);

        TableColumn<Tournoi, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(new Callback<TableColumn<Tournoi, Void>, TableCell<Tournoi, Void>>() {
            @Override
            public TableCell<Tournoi, Void> call(TableColumn<Tournoi, Void> param) {
                return new TableCell<Tournoi, Void>() {
                    private final Button deleteButton = new Button("delete");

                    private final Button updateButton = new Button("Modifier");

                    {
                       
                        updateButton.setOnAction(event -> {
                            // Get the selected user from the TableView
                            Tournoi tournoi = getTableView().getItems().get(getIndex());

                            // Show a dialog to edit the user's data
                            Dialog<Tournoi> dialog = new Dialog<>();
                            dialog.setTitle("Edit Tournoi");

                            // Create text fields for editing the user's data
                            TextField titreTextField = new TextField(tournoi.getTitre());

// Create a SimpleDateFormat object with the desired date format
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

// Format the date as a String
String dateString = dateFormat.format(tournoi.getDateTournoi());

// Set the value of the TextField with the formatted date string
TextField dateTextField = new TextField(dateString);

                            // Create a grid pane to layout the dialog controls
                            GridPane gridPane = new GridPane();
                            gridPane.setHgap(10);
                            gridPane.setVgap(10);
                            gridPane.addRow(0, new Label("Titre:"), titreTextField);
                            gridPane.addRow(1, new Label("Date:"), dateTextField);

                            dialog.getDialogPane().setContent(gridPane);

                            // Add buttons to the dialog
                            ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

                            // Convert the dialog result to a user object
                            dialog.setResultConverter(dialogButton -> {
                                if (dialogButton == updateButtonType) {
                                    String dateText = dateTextField.getText();
                                    LocalDate date = LocalDate.parse(dateText);

                                    return new Tournoi(titreTextField.getText(), date);
                                }
                                return null;
                            });

                            Optional<Tournoi> result = dialog.showAndWait();

                            // If the user clicks the update button, update the user's data in the database
                            result.ifPresent(newAthleteData -> {
                                try {
                                    PreparedStatement statement = connection.prepareStatement("UPDATE tournoi SET titre=?,date=? WHERE titre=?");
                                    statement.setString(1, newAthleteData.getTitre());

// Convert LocalDate to java.sql.Date
//Date sqlDate = Date.valueOf((LocalDate.parse(newAthleteData.getDateTournoi())));

// Use the sqlDate object in the SQL statement
//statement.setDate(2, sqlDate);
LocalDate localDate = newAthleteData.getDateTournoi(); // Replace with your LocalDate object
java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);


                                    statement.setDate(2, sqlDate);
                                    statement.setString(3, newAthleteData.getTitre());

                                    statement.executeUpdate();
                                    populateTableView(tableView, connection);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });
                        });
                        
                        deleteButton.setOnAction(event -> {
                    // Get the selected user from the TableView
                    Tournoi tournoi = getTableView().getItems().get(getIndex());

                    // Show a confirmation dialog before deleting the user
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Tournoi");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this tournoi?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        try {
                            PreparedStatement statement = connection.prepareStatement("DELETE FROM tournoi WHERE titre=?");
                            statement.setString(1, tournoi.getTitre());
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

                    ;
                    
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

                };
            }
        });
        tableView.getColumns().add(actionColumn);

    }
    
}
    
    

