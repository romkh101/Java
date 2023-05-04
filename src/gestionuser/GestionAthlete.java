/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser;

import gestionuser.entities.Athlete;
import gestionuser.entities.Tournoi;
import gestionuser.services.AthleteService;
import gestionuser.services.TournoiService;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class GestionAthlete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Date d=new Date(17,4,2023);
       Tournoi to=new Tournoi(455564,"aeda",d);
       
       
       TournoiService t=new TournoiService ();
       t.ajouter_Tournoi(to);
       t.supprimer_Tournoi(to);
       System.out.println(t.listerTournoi());

       
    }
    
}
