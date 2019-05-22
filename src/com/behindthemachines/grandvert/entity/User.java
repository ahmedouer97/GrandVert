/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.entity;

import java.util.Date;

/**
 *
 * @author psn
 */
public class User {
    
    private int id;
    private String username;
    private String email;
    private int enabled;
    private String password;
    private Date last_login;
    private String roles;
    private String nom;
    private String prenom;
    private int tel;
    private int level;
    private int score;
    private String avatar;
    private String adresse;

    public User() {
    }

    public User(int id, String username, String email, int enabled, Date last_login, String roles, String nom, String prenom, int tel, int level, int score, String avatar, String adresse) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.last_login = last_login;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.level = level;
        this.score = score;
        this.avatar = avatar;
        this.adresse = adresse;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
    
    
}
