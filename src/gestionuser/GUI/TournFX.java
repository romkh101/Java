/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Acer
 */
public class TournFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
           try {
            Parent root = FXMLLoader.load(getClass(). getResource("afficheathlete.fxml"));
            Scene scene = new Scene(root);
          
            

        //sorry about that - Windows defender issue.
        //move around here
      
            
            
            primaryStage.setTitle("Athlete");
            primaryStage.setScene(scene);
            primaryStage.show();         
            
        } catch (IOException ex) {
            Logger.getLogger(TournFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
