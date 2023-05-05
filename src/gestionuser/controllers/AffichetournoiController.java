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
import gestionuser.utils.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class AffichetournoiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    TournoiService t=new TournoiService();
    @FXML
    private TableView<?> tblData;

    public AffichetournoiController() {
       connection = MyConnection.getinstance().getCnx();

    }
    PreparedStatement preparedStatement;
    Connection connection;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tblData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            populateTableView(tblData,connection);
            
            /* fetColumnList();
            fetRowList();*/
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AffichetournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    private void affiche(ActionEvent event) {
         List<Tournoi> ttt=t.listerTournoi();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("liste de tournois");
        alert.setHeaderText(null);
        
        // Create a StringBuilder to build the content of the alert
        StringBuilder content = new StringBuilder();
        for (Tournoi tt : ttt) {
            // Append user data to the content
            content.append("ID: ").append(tt.getId()).append("\n");
            content.append("ID: ").append(tt.getTitre()).append("\n");

            
            // Append more user data as needed
            content.append("\n"); // Add a newline separator between each user
            
        }
        
        // Set the content of the alert
        alert.setContentText(content.toString());
        
        // Show the alert
        alert.showAndWait();
    }
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
            
           data.add(new Tournoi(id,titre, date));
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
                                        private final Button participerButton = new Button("Participer");

                    private final Button updateButton = new Button("Afficher participant");
                    {
                        updateButton.setOnAction(new EventHandler<ActionEvent>() {
                           
                            @Override
                            public void handle(ActionEvent event) {
                                // Get the selected user from the TableView
                                Tournoi t = getTableView().getItems().get(getIndex());
                                AthleteService AS = new AthleteService();
                                // Create a new ListView
                                List<Athlete> myList = AS.getAthleteTournoi(t.getId());
                                ListView<String> listView = new ListView<>();
                                for(Athlete A : myList){
                                    listView.getItems().add(A.getNom()+" "+A.getPrenom());
                                }
 
                                // Create a new Dialog and set its content to the ListView
                                Dialog<Void> dialog = new Dialog<>();
                                dialog.setTitle("My Dialog");
                                dialog.setHeaderText("Select an item from the list");
                                DialogPane dialogPane = dialog.getDialogPane();
                                dialogPane.setContent(listView);
                               
                                 dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);                                
                                // Show the Dialog
                                dialog.showAndWait();

                            }
                            
                        });
                            participerButton.setOnAction(new EventHandler<ActionEvent>() {
                           
                            @Override
                            public void handle(ActionEvent event) {
                                // Get the selected user from the TableView
                                Tournoi t = getTableView().getItems().get(getIndex());
                                AthleteService AS = new AthleteService();
                                // Create a new ListView
                                
                             //   boolean test = AS.checkParticipent(t.getId(),Session.getInstance().getUser().getId());
                             boolean test = AS.checkParticipent(t.getId(),5);
                                Dialog<Void> dialog = new Dialog<>();

                                if (test == false ){
                                                                    AS.ajouter_Athlete_Tornoi(t.getId(), 0);
                                dialog.setTitle("Valider");
                                dialog.setHeaderText("Vous avez participer");
                                }else {
                                                                    dialog.setTitle("Error");
                                dialog.setHeaderText("Vous étes deja participer");

                                }
                     
                                // Create a new Dialog and set its content to the ListView

                               
                                 dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);                                
                                // Show the Dialog
                                dialog.showAndWait();

                            }
                            
                        });
                    };
                    
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox();
                    hbox.setSpacing(10);
                    hbox.getChildren().addAll(updateButton,participerButton);
                    setGraphic(hbox);
                }
            }

                        };
                        }});
                                                     tableView.getColumns().add(actionColumn);

        
}


    private void statistiquess(ActionEvent event) throws IOException {
        GuiNavigate nav = new GuiNavigate();
         nav.navigate(event, "GestionUser", "/gestionuser/GUI/Tournoiajoutfx.fxml");
    
     
    }
}
