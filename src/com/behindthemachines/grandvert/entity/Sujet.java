package com.behindthemachines.grandvert.entity;

import java.util.Date;

/**
 *
 * @author ROCH
 */
public class Sujet {
    
    private int id;
    private int plante_id;
    private User user;
    private String sujet_original;
    private String sujet_edited;
    private Date date_original;
    private Date date_edited;
    private int nbshare;
    private int nbviews;
    private String open;
    private String resolu;
    private int archive;
    private int nbsignal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlante_id() {
        return plante_id;
    }

    public void setPlante_id(int plante_id) {
        this.plante_id = plante_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSujet_original() {
        return sujet_original;
    }

    public void setSujet_original(String sujet_original) {
        this.sujet_original = sujet_original;
    }

    public String getSujet_edited() {
        return sujet_edited;
    }

    public void setSujet_edited(String sujet_edited) {
        this.sujet_edited = sujet_edited;
    }

    public Date getDate_original() {
        return date_original;
    }

    public void setDate_original(Date date_original) {
        this.date_original = date_original;
    }

    public Date getDate_edited() {
        return date_edited;
    }

    public void setDate_edited(Date date_edited) {
        this.date_edited = date_edited;
    }

    public int getNbshare() {
        return nbshare;
    }

    public void setNbshare(int nbshare) {
        this.nbshare = nbshare;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getResolu() {
        return resolu;
    }

    public void setResolu(String resolu) {
        this.resolu = resolu;
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

    public int getNbviews() {
        return nbviews;
    }

    public void setNbviews(int nbviews) {
        this.nbviews = nbviews;
    }

    public Sujet(int plante_id, User user, String sujet_original, String sujet_edited, Date date_original, Date date_edited, int nbshare, int nbviews, String open, String resolu, int archive, int nbsignal) {
        this.plante_id = plante_id;
        this.user = user;
        this.sujet_original = sujet_original;
        this.sujet_edited = sujet_edited;
        this.date_original = date_original;
        this.date_edited = date_edited;
        this.nbshare = nbshare;
        this.nbviews = nbviews;
        this.open = open;
        this.resolu = resolu;
        this.archive = archive;
        this.nbsignal = nbsignal;
    }

    public Sujet(int id, int plante_id, User user, String sujet_original, String sujet_edited, Date date_original, Date date_edited, int nbshare, int nbviews, String open, String resolu, int archive, int nbsignal) {
        this.id = id;
        this.plante_id = plante_id;
        this.user = user;
        this.sujet_original = sujet_original;
        this.sujet_edited = sujet_edited;
        this.date_original = date_original;
        this.date_edited = date_edited;
        this.nbshare = nbshare;
        this.nbviews = nbviews;
        this.open = open;
        this.resolu = resolu;
        this.archive = archive;
        this.nbsignal = nbsignal;
    }


    public Sujet(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sujet{" + "id=" + id + ", plante_id=" + plante_id + ", user=" + user + ", sujet_original=" + sujet_original + ", sujet_edited=" + sujet_edited + ", date_original=" + date_original + ", date_edited=" + date_edited + ", nbshare=" + nbshare + ", nbviews=" + nbviews + ", open=" + open + ", resolu=" + resolu + ", archive=" + archive + ", nbsignal=" + nbsignal + '}';
    }
    
    
    
    

    
}