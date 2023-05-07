/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.controllers;

import gestionuser.entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class EvenementtItemController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Text nomuser;
    @FXML
    private Text type;
    private HBox hbox;
    private ImageView imv;
    
    String ImageUrl = "/gestionuser/GUI/img/";
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
   
    }  
    
    public EvenementtItemController(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestionuser/GUI/EvenementtItem.fxml"));
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

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }
    
    
    
    
        public void setInfo(Evenement p)  {   
        type.setText(p.getTitre());
        nomuser.setText(p.getLieu());
     /*   imv.setImage(new Image("/gestionuser/GUI/img/"+p.getImage()));*/
     
     
}
        
}
