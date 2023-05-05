/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.GUI;

import static gestionuser.controllers.AfficheathleteController.populateTableView;
import gestionuser.controllers.AffichetournoiController;
import gestionuser.entities.Athlete;
import gestionuser.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class ShowathleteeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView<?> tblData;
     
     
     
    public ShowathleteeController() {
        connection = MyConnection.getinstance().getCnx();

    }
    PreparedStatement preparedStatement;
    Connection connection;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tblData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            populateTableView(tblData, connection);

            /* fetColumnList();
            fetRowList();*/
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AffichetournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
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
        
                    

                   
                    
                

                
            

            

    }

    
    
}
