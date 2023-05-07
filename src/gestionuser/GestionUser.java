/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser;

import gestionuser.entities.User;
import gestionuser.services.UserService;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class GestionUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UserService ser = new UserService();
        String[] roles = {"string1", "string2", "string3"};
        User u = new User(263, "gvsedfejv", "efe", "efqeaesfdqef", "dfzfdezf", roles, "adhefqe", true, "dfcqdefz");
        ser.ajouter_User(u);
        System.out.println(ser.listerUsers());
        System.out.println(ser.listerUsersparNom("mansour"));
                
        User user = new User(1165416, "example@email.com", "John", "Doe", "password", new String[]{"role1", "role2"}, "image.jpg", true, "token");
        UserService userService = new UserService();
        userService.ajouter_User(user);
        userService.modifier_User(user, "NewNom", "NewPrenom", "newemail@email.com", "new_image.jpg", "newToken", "newPassword", new String[]{"newRole1", "newRole2"});
        System.out.println(userService.listerUsers()); 
        
        
        

    }

}
