package com.behindthemachines.grandvert.entity;

/**
 *
 * @author ROCH
 */
public class Reaction_Sujet {
    
    private Sujet sujet;
    private int user_id;
    private String reaction;

    public Reaction_Sujet(Sujet sujet, int user_id, String reaction) {
        this.sujet = sujet;
        this.user_id = user_id;
        this.reaction = reaction;
    }
   
    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }
    
    
}
