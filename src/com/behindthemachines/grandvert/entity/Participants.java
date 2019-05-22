/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.entity;

/**
 *
 * @author Bouazza Med
 */
public class Participants {
    private int event_id;
    private int user_id;
    private int statut=1;

    public Participants(int event_id, int user_id) {
        this.event_id = event_id;
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Participants{" + "event_id=" + event_id + ", user_id=" + user_id + ", statut=" + statut + '}';
    }
    

    
    
    
}
