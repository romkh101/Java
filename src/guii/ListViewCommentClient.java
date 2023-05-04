/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guii;


import entity.Commentaire;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewCommentClient extends ListCell<entity.Commentaire> {
    
    
     @Override
     public void updateItem(Commentaire e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            CommentItemControllerClient data = new CommentItemControllerClient();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}

