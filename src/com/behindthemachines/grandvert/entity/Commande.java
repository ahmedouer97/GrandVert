/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.entity;

import java.sql.Timestamp;

/**
 *
 * @author ahmed
 */
public class Commande {
     private int id;
    private Plante plante;
     private User user;
      private int contite;
       private String etat;
       private Timestamp date;
        private String nom;
        private Float total;
        private int stock;
        private String nomu;

    public Commande(int id, Plante plante, int contite, String etat, Timestamp date,User user) {
        this.id = id;
        this.plante = plante;
        this.contite = contite;
        this.etat = etat;
        this.date = date;
        this.user=user;
        nom=this.plante.getNom();
        total=contite*this.plante.getPrix();
        stock=this.plante.getStock();
        nomu=this.user.getUsername();
    }

    public Commande(Plante plante, int contite, String etat, Timestamp date,User user) {
       this.plante = plante;
        this.contite = contite;
        this.etat = etat;
        this.date = date;
        this.user=user;
        nom=this.plante.getNom();
        total=contite*this.plante.getPrix();
        stock=this.plante.getStock();
        nomu=this.user.getUsername();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNomu() {
        return nomu;
    }

    public void setNomu(String nomu) {
        this.nomu = nomu;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plante getPlante() {
        return plante;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }

    public int getContite() {
        return contite;
    }

    public void setContite(int contite) {
        this.contite = contite;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", plante=" + plante + ", contite=" + contite + ", etat=" + etat + ", date=" + date + ", nom=" + nom + ", total=" + total + '}';
    }
    
}
