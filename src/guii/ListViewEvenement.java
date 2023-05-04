/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guii;


import entity.Evenement;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewEvenement extends ListCell<Evenement> {
    
    
     @Override
     public void updateItem(Evenement e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            EvenementtItemController data = new EvenementtItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
