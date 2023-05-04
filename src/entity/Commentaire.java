/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class Commentaire {
    
    private int id ;
    
    private String contenue ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", contenue=" + contenue + '}';
    }
    
    
    
}
