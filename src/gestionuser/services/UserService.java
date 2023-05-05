/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.services;

import gestionuser.controllers.getData;
import gestionuser.entities.User;
import gestionuser.utils.MyConnection;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import gestionuser.utils.BCrypt;
import java.util.UUID;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
public class UserService {

    Connection cnx;

    public UserService() {
        cnx = MyConnection.getinstance().getCnx();
    }

   

    public void Register(User u) {
        String hachedMdp = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO user( nom, prenom, email, image_filename,password) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getEmail());
              pst.setString(4, u.getImageFile());

            pst.setString(5,hachedMdp );
            
            pst.executeUpdate();
            System.out.println("Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public boolean login(String email, String password, HttpSession session) {
    try {
        String query = "SELECT id, password FROM user WHERE UPPER(email) = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, email.toUpperCase());
        ResultSet rs = statement.executeQuery();
        if (rs.next() && BCrypt.checkpw(password, rs.getString("password"))) {
            int userId = rs.getInt("id");
            String token = UUID.randomUUID().toString(); // generate a random token
            session.setAttribute("userId", userId);
            session.setAttribute("token", token);
            return true;
        } else {
            return false;
        }
    } catch (SQLException ex) {
        System.err.println("Error during login: " + ex.getMessage());
        return false;
    }
}

  public void logout(HttpSession session) {
    session.removeAttribute("userId");
    session.removeAttribute("token");
    session.invalidate();
}



    public boolean login(String email, String pwd) {
        try {
            String requete = "SELECT email,password From User WHERE UPPER(email) = '" + email.toUpperCase() + "'";
            Statement st = cnx.prepareStatement(requete);
            ResultSet rss = st.executeQuery(requete);
            while (rss.next()) {
                String hashp = rss.getString("password");
                if (rss.getString("email").equals(email) && BCrypt.checkpw(pwd, hashp)) {
                    try {

                        String requetee = "SELECT ID,password,roles From User WHERE UPPER(email) = '" + email.toUpperCase() + "'";
                        Statement stt = cnx.prepareStatement(requetee);
                        ResultSet rs = stt.executeQuery(requetee);
                        
                        while (rs.next()) {

                            String req = "INSERT INTO Logger VALUES (?) ";
                            PreparedStatement pst = cnx.prepareStatement(req);

                         
                            pst.setString(1, rs.getString("id"));
                            // pst.setString(2,rs.getString("mot_de_passe"));
                            //pst.setString(3, rs.getString("role"));
                            pst.executeUpdate();
                            /*System.out.println(rs.getInt("ID"));
               System.out.println(rs.getString("mot_de_passe"));*/
                            System.out.println(" Bien connecté ");

                        }
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }
                    return true;
                } else {
                    System.out.println("Mot de Passe ou Email erroné");
                    return false;
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;

    }
    
     public User LoginUser(String email, String password) {
        String request = "SELECT * FROM User where email=?";
        User user = null;
        try{
           PreparedStatement st;
           st = cnx.prepareStatement(request);
           st.setString(1,email);
           ResultSet rs = st.executeQuery();
         
        while(rs.next()){
             if(BCrypt.checkpw(password,rs.getString(4))== true){
                
              user = new User( rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                                
                               
                                
                              
                                
                           
                
        }
        }
        }catch(SQLException e){
            System.out.println("erreur authentification " + e);
        }
        return user ;
    }  
    
    
    
     public User getUserByEmail(String Email) throws SQLException {
        User a = null;
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, Email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            a = new User();
            a.setId(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setEmail(rs.getString("email"));
            a.setPassword("password");
            a.setResetToken(rs.getString("reset_token"));
            a.setImageFile(rs.getString("image_filename"));

        }
        return a;
    }

    public List<User> listerUsers() {
        List<User> myList = new ArrayList();
        try {

            String requete2 = "Select * FROM user";
            Statement st = MyConnection.getinstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPassword(rs.getString("is_verified"));
                user.setPassword(rs.getString("password"));
                user.setImageFile(rs.getString("image_filename"));
                user.setImageFile(rs.getString("reset_token"));
                myList.add(user);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }

    public List<User> listerUsersparNom(String nom) {
        List<User> myList = new ArrayList<>();
        try (PreparedStatement ps = MyConnection.getinstance().getCnx().prepareStatement(
                "SELECT * FROM user WHERE nom = ?")) {
            ps.setString(1, nom);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User rec = new User();
                    rec.setId(rs.getInt("id"));
                    rec.setNom(rs.getString("nom"));
                    rec.setPrenom(rs.getString("prenom"));
                    rec.setPrenom(rs.getString("prenom"));
                    rec.setEmail(rs.getString("email"));
                    rec.setPassword(rs.getString("password"));
                    rec.setPassword(rs.getString("is_verified"));
                    rec.setPassword(rs.getString("password"));
                    rec.setImageFile(rs.getString("image_filename"));
                    rec.setImageFile(rs.getString("reset_token"));
                    myList.add(rec);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE id=?";
            PreparedStatement statement = MyConnection.getinstance().getCnx().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String[] roles = resultSet.getString("roles").split(",");
                user = new User(resultSet.getString("email"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("Password"), roles, resultSet.getString("image_filename"), resultSet.getBoolean("is_verified"), resultSet.getString("reset_token"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByIdd(int id) {
        User user = null;
        try {
            Connection connection = MyConnection.getinstance().getCnx();
            String query = "SELECT * FROM user WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String imageFile = resultSet.getString("image_filename");
                String resetToken = resultSet.getString("reset_token");
                String password = resultSet.getString("Password");
                Array rolesArray = resultSet.getArray("roles");
                String[] roles = (String[]) rolesArray.getArray();
                Boolean isVerified = resultSet.getBoolean("is_verified");
                user = new User(email, nom, prenom, password, roles, imageFile, isVerified, resetToken);
            } else {
                System.out.println("No user found with id " + id);
            }
        } catch (SQLException ex) {
            System.err.println("Error retrieving user: " + ex.getMessage());
        }
        return user;
    }

    public void changePassword(String mdp, String email) throws SQLException {
        String hachedMdp = BCrypt.hashpw(mdp, BCrypt.gensalt());
        String req5 = "UPDATE user SET password = ?  WHERE email = ?";
        PreparedStatement pst = cnx.prepareStatement(req5);
        pst.setString(1, hachedMdp);
        pst.setString(2, email);
        int rowUpdated = pst.executeUpdate();
        if (rowUpdated > 0) {
            System.out.println("Mdp modifié");
        } else {
            System.out.println("ERR");
        }    }
   
/*public void logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
        session.invalidate();
    }
}*/


}
