package com.behindthemachines.grandvert.entity;

/**
 *
 * @author ROCH
 */
public class Plante {
    private int id;
    private String nom;
    private String Description;
    private int stock;
    private float prix;
    private int hauteur;
    private String fertiliseur;
    private String categorie;
    private String season;
    private String photo;
    private int proposition;
    private User user;
    private String nomu="";
    public Plante(int id,String photo, String nom, String Description, int stock, float prix, int hauteur, String fertiliseur, String categorie, String season,int proposition,User user) {
        this.id = id;
        this.nom = nom;
        this.Description = Description;
        this.stock = stock;
        this.prix = prix;
        this.hauteur = hauteur;
        this.fertiliseur = fertiliseur;
        this.categorie = categorie;
        this.season = season;
        this.photo=photo;
        this.proposition=proposition;
        this.user=user;
        if(this.user!=null)
        nomu=this.user.getNom();
    }
    public Plante(String nom,String photo, String Description, int stock, float prix, int hauteur, String fertiliseur, String categorie, String season,int proposition,User user) {
        this.nom = nom;
        this.Description = Description;
         this.photo=photo;
        this.stock = stock;
        this.prix = prix;
        this.hauteur = hauteur;
        this.fertiliseur = fertiliseur;
        this.categorie = categorie;
        this.season = season;
         this.proposition=proposition;
         this.user=user;
           if(this.user!=null)
           nomu=this.user.getNom();
    }

    public String getNomu() {
        return nomu;
    }

    public void setNomu(String nomu) {
        this.nomu = nomu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public String getFertiliseur() {
        return fertiliseur;
    }

    public void setFertiliseur(String fertiliseur) {
        this.fertiliseur = fertiliseur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getProposition() {
        return proposition;
    }

    public void setProposition(int proposition) {
        this.proposition = proposition;
    }

    @Override
    public String toString() {
        return "Plante{" + "id=" + id + ", nom=" + nom + ", Description=" + Description + ", stock=" + stock + ", prix=" + prix + ", hauteur=" + hauteur + ", fertiliseur=" + fertiliseur + ", categorie=" + categorie + ", season=" + season + ", proposition=" + proposition + '}';
    }
    
    
}
