/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guii;

import entity.Participants;
import javafx.scene.control.ListCell;

/**
 *
 * @author asus
 */
public class ListViewParticipant extends ListCell<Participants>{
       
    
     public void updateItem(Participants e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            ParticipantItemController data = new ParticipantItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
}
