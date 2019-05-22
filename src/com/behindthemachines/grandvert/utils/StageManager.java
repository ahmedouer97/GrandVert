/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.utils;
import com.behindthemachines.grandvert.Plante.AfficheController;
import com.behindthemachines.grandvert.entity.Jardin;
import com.behindthemachines.grandvert.entity.User;
import grandvert.java.GrandVertJava;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author psn
 */
public class StageManager {
    
    private static StageManager stager = new StageManager();
    private User user;
    private Jardin jardin;
    BorderPane root= GrandVertJava.getRoot();
    
    private StageManager() {
    }
    
    public void setJardin(Jardin jardin){
        this.jardin=jardin;
       // System.out.println("JardinId= "+jardin.getId() + ", nom= "+jardin.getNom());
    }
    
    public int getJardinId(){
        return this.jardin.getId();
    }
    
    public static StageManager getStageManager(){
        return stager;
    }
    
    public String getUsername(){
        return user.getUsername();
    }
    
    public String getRoles(){
        return user.getRoles();
    }
    
    public void setUser(User user){
        this.user=user;
    }
    
    public User getUser(){
        return this.user;
    }
    
    public void Load(String s){
        try {
            URL content = getClass().getResource("/com/behindthemachines/grandvert/"+s+".fxml");
            AnchorPane middle = FXMLLoader.load(content);
            
            root.setCenter(middle);
           
        } catch (IOException ex) {
            Logger.getLogger(StageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Dashboard(){
        if (getRoles().contains("ADMIN")){
            try {    
            URL menu = getClass().getResource("/com/behindthemachines/grandvert/Membre/MenuAdmin.fxml");
            AnchorPane left = FXMLLoader.load(menu);
            URL content = getClass().getResource("/com/behindthemachines/grandvert/Membre/Dashboard.fxml");
            AnchorPane middle = FXMLLoader.load(content);
            
            root.setCenter(middle);
            root.setLeft(left);
            
        } catch (IOException ex) {
            Logger.getLogger(StageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            try {    
            URL menu = getClass().getResource("/com/behindthemachines/grandvert/Membre/MenuUser.fxml");
            AnchorPane left = FXMLLoader.load(menu);
            URL content = getClass().getResource("/com/behindthemachines/grandvert/Membre/Landing.fxml");
            AnchorPane middle = FXMLLoader.load(content);
            
            root.setCenter(middle);
            root.setLeft(left);
            
        } catch (IOException ex) {
            Logger.getLogger(StageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public void LoginScreen(){
        try {    
            URL menu = getClass().getResource("/com/behindthemachines/grandvert/Membre/Login.fxml");
            AnchorPane left = FXMLLoader.load(menu);
            URL content = getClass().getResource("/com/behindthemachines/grandvert/Membre/Landing.fxml");
            AnchorPane middle = FXMLLoader.load(content);
            
            root.setCenter(middle);
            root.setLeft(left);
            
        } catch (IOException ex) {
            Logger.getLogger(StageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        user = null;
        AfficheController.plantes.clear();
}
    
}
