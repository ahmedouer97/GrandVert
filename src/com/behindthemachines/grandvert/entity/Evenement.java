/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.entity;
import java.util.Date;
/**
 *
 * @author Bouazza Med
 */
public class Evenement {
    private int id;
    private int user_id;
    private String categorie="formation";
    private String titre;
    private String organisation;
    private String description;
    private String image;
    private String lieu;
    private String adresse;
    private String gouvernorat="tunis";
    private Date dated;
    private Date datef;    
    private double prix=0;
    private int confa=0;
    private int etat=0;

    public Evenement(int id, int user_id, String categorie, String titre, String organisation, String description, String image, String lieu, String adresse, String gouvernorat, Date dated, Date datef, double prix, int confa, int etat) {
        this.id = id;
        this.user_id = user_id;
        this.categorie = categorie;
        this.titre = titre;
        this.organisation = organisation;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.adresse = adresse;
        this.gouvernorat = gouvernorat;
        this.dated = dated;
        this.datef = datef;
        this.prix = prix;
        this.confa = confa;
        this.etat = etat;
    }

    public Evenement( int user_id,String titre, String organisation, String description, String image, String lieu, String adresse, Date dated, Date datef) {
        this.user_id = user_id;
        this.titre = titre;
        this.organisation = organisation;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.adresse = adresse;
        this.dated = dated;
        this.datef = datef;
    }
    public Evenement(int id,int user_id, String titre, String organisation, String description, String image, String lieu, String adresse, Date dated, Date datef) {
                this.user_id = user_id;
        this.id = id;
        this.titre = titre;
        this.organisation = organisation;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.adresse = adresse;
        this.dated = dated;
        this.datef = datef;
    }
    
     public Evenement( int id,int user_id,String titre, String organisation, String description, String image, String lieu, String adresse, Date datef) {
                this.user_id = user_id;
         this.id = id;
         this.titre = titre;
        this.organisation = organisation;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.adresse = adresse;
        this.datef = datef;
    }
      public Evenement(int id,int user_id, String categorie,String titre, String organisation, String description, String image, String lieu, String adresse, Date dated) {
                  this.user_id = user_id;    
          this.id = id;
          this.categorie=categorie;
        this.titre = titre;
        this.organisation = organisation;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.adresse = adresse;
        this.dated = dated;
    }
         public Evenement(int id,int user_id,String titre, String organisation, String description, String image, String lieu, String adresse) {
                     this.user_id = user_id;  
             this.id = id;
        this.titre = titre;
        this.organisation = organisation;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.adresse = adresse;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public Date getDatef() {
        return datef;
    }

    public void setDatef(Date datef) {
        this.datef = datef;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getConfa() {
        return confa;
    }

    public void setConfa(int confa) {
        this.confa = confa;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", user_id=" + user_id + ", categorie=" + categorie + ", titre=" + titre + ", organisation=" + organisation + ", description=" + description + ", lieu=" + lieu + ", adresse=" + adresse + ", gouvernorat=" + gouvernorat + ", dated=" + dated + ", datef=" + datef + ", prix=" + prix + ", confa=" + confa + ", etat=" + etat + '}';
    }
    
    
    
  
}
