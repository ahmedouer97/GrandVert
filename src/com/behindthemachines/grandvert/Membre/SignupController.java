/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Membre;

import com.behindthemachines.grandvert.entity.Jardin;
import com.behindthemachines.grandvert.entity.User;
import com.behindthemachines.grandvert.services.JardinService;
import com.behindthemachines.grandvert.services.UserService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SignupController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXTextField loginTxt;

    @FXML
    private JFXTextField nomTxt;

    @FXML
    private JFXTextField prenomTxt;

    @FXML
    private JFXPasswordField mdpTxt;

    @FXML
    private JFXPasswordField repmdpTxt;

    @FXML
    private JFXTextField emailTxt;
    
    UserService userService = new UserService();
    
    JardinService jardinService = new JardinService();

    @FXML
    void backLogin(ActionEvent event) {
        try {
            URL signIn = getClass().getResource("Login.fxml");
            AnchorPane anchorpane = FXMLLoader.load(signIn);
            
            anchor.getChildren().clear();
            anchor.getChildren().add(anchorpane);   
        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void confirmer(ActionEvent event) {
        if (validate()){
            // Get the user information from the textfields
            String login = loginTxt.getText().trim();
            String nom = nomTxt.getText().trim();
            String prenom = prenomTxt.getText().trim();
            String email = emailTxt.getText().trim();
            char[] mdp = mdpTxt.getText().trim().toCharArray();
            char[] repmdp = repmdpTxt.getText().trim().toCharArray();

            // Create User with informations
            User user = new User();

            //  Create User Profile with informations
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setEmail(email);
            user.setUsername(login);
            user.setPassword(String.valueOf(mdp));
            user.setRoles("a:0:{}");
            user.setAvatar("chooseApath");
            user.setAdresse("chooseAdress");
            
            int id = userService.addUser(user);
            user.setId(id);
            
            Jardin jardin = new Jardin();
            long time = System.currentTimeMillis();
            jardin.setDateC(new Date(time));
            jardin.setNom(user.getNom()+"Jardin");
            jardin.setUser(user.getId());
            jardinService.add(jardin);
        }
    }
    
    private boolean validate(){
        if (loginTxt.getText().trim().isEmpty()){
            loginTxt.getStyleClass().add("validateTxt");
            return false;
        }
        if (mdpTxt.getText().trim().isEmpty()){
            mdpTxt.getStyleClass().add("validateTxt");
            return false;
        }
        if (mdpTxt.getText().trim().compareTo(repmdpTxt.getText().trim())!=0){
            repmdpTxt.getStyleClass().add("validateTxt");
            return false;
        }
        
        return true;
    }
    
    private void redirect(){
        
    } 

}

