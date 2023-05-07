/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class CommentItemControllerClient implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Text nomuser;
    @FXML
    private Text type;

    
    String ImageUrl = "/gui/img/";
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
   
    }  
    
    public CommentItemControllerClient(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestionuser/GUI/CommenttItemClient.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
    }

    public HBox getBox() {
        return Hbox;
    }


    
    
    
    
        public void setInfo(gestionuser.entities.Commentaire p)  {   
            
        type.setText(p.getContenue());
        nomuser.setText("Romh");
        
     
}
        
}
