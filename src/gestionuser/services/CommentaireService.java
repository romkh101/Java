/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.services;

import gestionuser.entities.Commentaire;
import gestionuser.entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import gestionuser.utils.DataSource;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class CommentaireService {
    
      private final Connection cnx;

    private static CommentaireService instance;
    
        public CommentaireService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static CommentaireService getInstance()
    {
        if (instance == null) {
            instance = new CommentaireService();
        }
        return instance; 
    }
    
   public void addCommentaire(Commentaire q)throws SQLDataException, SQLException{
         String query ="INSERT INTO `commentaire`(`contenucommentairer`) VALUES (?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getContenue());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public boolean ModifierCommentaire(Commentaire e) throws SQLDataException {
                String query = "UPDATE `commentaire` SET `contenucommentairer`=? WHERE `idcommentairer` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getContenue());
                st.setInt(2,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Commentaire> getAllCommentaire() throws SQLDataException {

        
        List<Commentaire> list =new ArrayList<Commentaire>();
        int count =0;
        
        String requete="select * from commentaire";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Commentaire e = new Commentaire();
                e.setContenue(rs.getString("contenucommentairer"));
                e.setId(rs.getInt("idcommentairer"));
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Commentaire get_CommentaireById(int i) {
        Commentaire e = new Commentaire();
        int nombre = 0;
        String requete = "select * from commentaire where idcommentairer="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                e.setContenue(rs.getString("idcommentairer"));
                e.setId(rs.getInt("id"));
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
   
    

    public boolean deleteCommentaire(int idCat) throws SQLDataException {

        
        
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM commentaire WHERE idcommentairer="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
}
