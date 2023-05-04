/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class Evenement {
    
    
    private int id ;
    private String titre,image,lieu;
    private Date dateevenet ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDateevenet() {
        return dateevenet;
    }

    public void setDateevenet(Date dateevenet) {
        this.dateevenet = dateevenet;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", titre=" + titre + ", image=" + image + ", lieu=" + lieu + ", dateevenet=" + dateevenet + '}';
    }
    
    
    
    
}
