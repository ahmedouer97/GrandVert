package com.behindthemachines.grandvert.entity;

/**
 *
 * @author ROCH
 */
public class Reaction_Reponse {
    
    private Reponse reponse;
    private int user_id;
    private String reaction;

    public Reaction_Reponse(Reponse reponse, int user_id, String reaction) {
        this.reponse = reponse;
        this.user_id = user_id;
        this.reaction = reaction;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
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
