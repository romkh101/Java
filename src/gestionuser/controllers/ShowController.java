/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.User;
import gestionuser.services.UserService;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.servlet.http.HttpServletRequest;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author Acer
 */
public class ShowController implements Initializable {

    @FXML
    private TextField nombtn;
    @FXML
    private TextField prenombtn;
    @FXML
    private TextField emailbtn;
   
    @FXML
    private PasswordField passwordbtn;
    @FXML
    private Button btnSave;
    @FXML
    private TableView tblData;
    @FXML
    private Label lblStatus;
    @FXML
    private static Pagination paginationbtn;
        UserService us = new UserService();


    /**
     * Initializes the controller class.
     */
    PreparedStatement preparedStatement;
    Connection connection;
    @FXML
    private Button logout;
    @FXML
    private ImageView imagebtn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           tblData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            populateTableView(tblData,connection);
            
            /* fetColumnList();
            fetRowList();*/
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
   

    public ShowController() {
        connection = MyConnection.getinstance().getCnx();

    }
    


 
    public static void populateTableView(TableView tableView, Connection connection) throws SQLException {
    // Clear any existing data in the TableView
    tableView.getItems().clear();

    // Create a statement to execute a query
    Statement statement = connection.createStatement();

    // Execute the query to fetch data from the database
    ResultSet resultSet = statement.executeQuery("SELECT nom, prenom, email FROM user");

    // Create an ObservableList to hold the data
    ObservableList<User> data = FXCollections.observableArrayList();

    // Loop through the result set and add data to the ObservableList
    while (resultSet.next()) {
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        String email = resultSet.getString("email");

        data.add(new User(nom, prenom, email));
    }

    // Set the data in the TableView
    tableView.setItems(data);

    // Define table columns and associate them with data model properties
    TableColumn<User, String> nomColumn = new TableColumn<>("Nom");
    nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

    TableColumn<User, String> prenomColumn = new TableColumn<>("Prenom");
    prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

    TableColumn<User, String> emailColumn = new TableColumn<>("Email");
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

    // Add the columns to the TableView
    tableView.getColumns().setAll(emailColumn, nomColumn, prenomColumn);

    // Add the update and delete buttons to the TableView
    TableColumn<User, Void> actionColumn = new TableColumn<>("Action");
    actionColumn.setCellFactory(param -> new TableCell<User, Void>() {
        private final Button updateButton = new Button("Update");
        private final Button deleteButton = new Button("Delete");

        {
            updateButton.setOnAction(event -> {
                // Get the selected user from the TableView
                User user = getTableView().getItems().get(getIndex());

                // Show a dialog to edit the user's data
                Dialog<User> dialog = new Dialog<>();
                dialog.setTitle("Edit User");

                // Create text fields for editing the user's data
                TextField nomTextField = new TextField(user.getNom());
                TextField prenomTextField = new TextField(user.getPrenom());
                TextField emailTextField = new TextField(user.getEmail());

                // Create a grid pane to layout the dialog controls
                GridPane gridPane = new GridPane();
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.addRow(0, new Label("Nom:"), nomTextField);
                gridPane.addRow(1, new Label("Prenom:"), prenomTextField);
                gridPane.addRow(2, new Label("Email:"), emailTextField);

                dialog.getDialogPane().setContent(gridPane);

                // Add buttons to the dialog
                ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

                // Convert the dialog result to a user object
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == updateButtonType) {
                        return new User(nomTextField.getText(), prenomTextField.getText(), emailTextField.getText());
                    }
                    return null;
                });

                Optional<User> result = dialog.showAndWait();

                // If the user clicks the update button, update the user's data in the database
                result.ifPresent(newUserData -> {
                    try {
                        PreparedStatement statement = connection.prepareStatement("UPDATE user SET nom=?, prenom=?, email=? WHERE email=?");
                        statement.setString(1, newUserData.getNom());
                        statement.setString                    (2, newUserData.getPrenom());
                    statement.setString(3, newUserData.getEmail());
                    statement.setString(4, user.getEmail());
                    statement.executeUpdate();
                    populateTableView(tableView, connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });

        deleteButton.setOnAction(event -> {
            // Get the selected user from the TableView
            User user = getTableView().getItems().get(getIndex());

            // Show a confirmation dialog before deleting the user
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete User");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this user?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE email=?");
                    statement.setString(1, user.getEmail());
                    statement.executeUpdate();
                    populateTableView(tableView, connection);
                    TrayNotification tr = new TrayNotification();
                      AnimationType type = AnimationType.POPUP;

                        tr.setAnimationType(type);
                        tr.setTitle("supprimer");
                        tr.setMessage("Utilisateur supprimé");
                        tr.setNotificationType(NotificationType.SUCCESS);
                        tr.showAndDismiss(Duration.millis(5000));
                    
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

     private boolean validateInputs() {
        if (nombtn.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre Prenom");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if ((prenombtn.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre Nom");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } 
         
        else if ((emailbtn.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
       
        } else if (passwordbtn.getText().length() < 8) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Mot de passe doit dépasser les 6 caractères");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        }   
        return true;
} 


    @FXML
    private void register(ActionEvent event) {
        if (!validateInputs()); 
        else {
            try{
            
            User u1 = new User();
            u1.setNom(nombtn.getText());
            u1.setPrenom(prenombtn.getText());
           
            u1.setEmail(emailbtn.getText());
            u1.setPassword(passwordbtn.getText());
             TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("add");
            tr.setMessage("Created succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndWait();       
          
            System.out.println(u1);
            us.Register(u1);}
            catch(Exception e){
               System.out.println(e.getMessage());
            }
        }
        
}

  /*  private void logout(ActionEvent event,HttpServletRequest request) {
        UserService u=new UserService() ;
        u.logout(request);
    }

    @FXML
    private void logout(ActionEvent event) {
    }
*/

    @FXML
    private void logout(ActionEvent event) throws IOException {
          Session.getInstance().invalidate();

    // Navigate to the login page
    GuiNavigate nav = new GuiNavigate();
    nav.navigate(event, "GestionUser", "/gestionuser/GUI/Login.fxml");  
    }

   
}

    

