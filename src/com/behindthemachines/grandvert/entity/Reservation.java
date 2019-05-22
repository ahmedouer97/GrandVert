/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.entity;

import java.sql.Date;

/**
 *
 * @author Ghaith
 */
public class Reservation {
    private int id;
    private Date DateDebut;
    private Date DateFin;
    private int NbPlaces;
    private Espace espace;
    private User user;
    
    public Reservation () {}

    public Espace getEspace() {
        return espace;
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }
    
    

    public Reservation(int id, Date DateDebut, Date DateFin, int NbPlaces) {
        this.id = id;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.NbPlaces = NbPlaces;
       
    }

    public Reservation(int id, Date DateDebut, Date DateFin, int NbPlaces, String espace,int idEspace) {
        this.id = id;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.NbPlaces = NbPlaces;
        this.espace = new Espace(espace);
    }
    
      public Reservation(int id, Date DateDebut, Date DateFin, int NbPlaces, String lieuEspace,int capacityEspace,String nomEspace,int idEspace) {
        this.id = id;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.NbPlaces = NbPlaces;
       this.espace = new Espace(nomEspace,capacityEspace,lieuEspace);
    }
      public Reservation(int id, Date DateDebut, Date DateFin, int NbPlaces, String espace,int idEspace,User user) {
        this.id = id;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.NbPlaces = NbPlaces;
        this.espace = new Espace(espace);
        this.user = user;
        
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    
    
    

    public Reservation(Date DateDebut, Date DateFin, int NbPlaces) {
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.NbPlaces = NbPlaces;
      
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + ", NbPlaces=" + NbPlaces + ", espace=" + espace+ ", user=" + user + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public int getNbPlaces() {
        return NbPlaces;
    }

    public void setNbPlaces(int NbPlaces) {
        this.NbPlaces = NbPlaces;
    }

   

  
    
    
    
}
