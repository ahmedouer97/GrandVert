package com.behindthemachines.grandvert.entity;

/**
 *
 * @author ROCH
 */
public class Personne {
    private int id;
    private String Nom;
    private String Prenom;
    
    public Personne(){
    
    }

    public Personne(int id, String Nom, String Prenom) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
    }

    public Personne(String Nom, String Prenom) {
        this.Nom = Nom;
        this.Prenom = Prenom;
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

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + '}';
    }
    
    
    
    
}
