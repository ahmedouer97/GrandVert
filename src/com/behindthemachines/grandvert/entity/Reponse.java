package com.behindthemachines.grandvert.entity;

import java.util.Date;

/**
 *
 * @author ROCH
 */
public class Reponse {
    
    private int id;
    private Sujet sujet;
    private User user;
    private String reponse_original;
    private String reponse_edited; 
    private Date date_roriginal;
    private Date date_redited;
    private int archive;
    private int nbsignal;

    public Reponse(int id, Sujet sujet, User user, String reponse_original, String reponse_edited, Date date_roriginal, Date date_redited, int archive, int nbsignal) {
        this.id = id;
        this.sujet = sujet;
        this.user = user;
        this.reponse_original = reponse_original;
        this.reponse_edited = reponse_edited;
        this.date_roriginal = date_roriginal;
        this.date_redited = date_redited;
        this.archive = archive;
        this.nbsignal = nbsignal;
    }

    public Reponse(Sujet sujet, User user, String reponse_original, String reponse_edited, Date date_roriginal, Date date_redited, int archive, int nbsignal) {
        this.sujet = sujet;
        this.user = user;
        this.reponse_original = reponse_original;
        this.reponse_edited = reponse_edited;
        this.date_roriginal = date_roriginal;
        this.date_redited = date_redited;
        this.archive = archive;
        this.nbsignal = nbsignal;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", sujet=" + sujet + ", user_id=" + getUser().getId() + ", reponse_original=" + reponse_original + ", reponse_edited=" + reponse_edited + ", date_roriginal=" + date_roriginal + ", date_redited=" + date_redited + ", archive=" + archive + ", nbsignal=" + nbsignal + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReponse_original() {
        return reponse_original;
    }

    public void setReponse_original(String reponse_original) {
        this.reponse_original = reponse_original;
    }

    public String getReponse_edited() {
        return reponse_edited;
    }

    public void setReponse_edited(String reponse_edited) {
        this.reponse_edited = reponse_edited;
    }

    public Date getDate_roriginal() {
        return date_roriginal;
    }

    public void setDate_roriginal(Date date_roriginal) {
        this.date_roriginal = date_roriginal;
    }

    public Date getDate_redited() {
        return date_redited;
    }

    public void setDate_redited(Date date_redited) {
        this.date_redited = date_redited;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public int getNbsignal() {
        return nbsignal;
    }

    public void setNbsignal(int nbsignal) {
        this.nbsignal = nbsignal;
    }


    
    

}
