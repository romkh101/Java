/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.entities;

/**
 *
 * @author Acer
 */
public class User {
   private int id ;
   private String email ;
   private String nom,prenom ;
   private String password ;
   private String[] roles ;
   private String imageFile ;
   private Boolean isVerified ;
   private String resetToken ;

    public User(int id, String email, String nom, String prenom, String password, String[] roles, String imageFile, Boolean isVerified, String resetToken) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.roles = roles;
        this.imageFile = imageFile;
        this.isVerified = isVerified;
        this.resetToken = resetToken;
    }

    public User(String email, String nom, String prenom, String password, String[] roles, String imageFile, Boolean isVerified, String resetToken) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.roles = roles;
        this.imageFile = imageFile;
        this.isVerified = isVerified;
        this.resetToken = resetToken;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public User(String email, String nom, String prenom, String password, String imageFile, String resetToken) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.imageFile = imageFile;
        this.resetToken = resetToken;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", roles=" + roles + ", imageFile=" + imageFile + ", isVerified=" + isVerified + ", resetToken=" + resetToken + '}';
    }

  /*  public User(String email, String prenom, String password) {
        this.email = email;
        this.prenom = prenom;
        this.password = password;
    }*/
     public User(String nom, String prenom, String email) {
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
    }
   
   
   
}
