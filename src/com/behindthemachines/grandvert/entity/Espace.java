/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.entity;

/**
 *
 * @author Ghaith
 */
public class Espace {
    
    private int id;
    private String Nom;
    private int Capacity;
    private String Lieu;
    private int user_id;

    public Espace(String Nom) {
        this.Nom = Nom;
    }
    
    

    public Espace(String Nom, int Capacity) {
        this.Nom = Nom;
        this.Capacity = Capacity;
    }

    public Espace(String Nom, int Capacity, String Lieu) {
        this.Nom = Nom;
        this.Capacity = Capacity;
        this.Lieu = Lieu;
    }
    
    public Espace(int id, String Nom, int Capacity, String Lieu) {
        this.id = id;
        this.Nom = Nom;
        this.Capacity = Capacity;
        this.Lieu = Lieu;
      
    }

    public Espace(int id, String Nom) {
        this.id = id;
        this.Nom = Nom;
    }

    
    
    public Espace(int id, String Nom, int Capacity, String Lieu, int user_id) {
        this.id = id;
        this.Nom = Nom;
        this.Capacity = Capacity;
        this.Lieu = Lieu;
        this.user_id = user_id;
    }

    public Espace() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return Nom;
    }
    
    
    
    
    
    
}
