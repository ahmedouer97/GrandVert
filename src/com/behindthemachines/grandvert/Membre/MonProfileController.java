/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Membre;

import com.behindthemachines.grandvert.entity.User;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author psn
 */
public class MonProfileController implements Initializable {

    @FXML
    private JFXTextField userTxt;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private JFXTextField nomTxt;
    @FXML
    private JFXTextField prenomTxt;
    @FXML
    private JFXTextField telTxt;
    @FXML
    private JFXTextField adrTxt;
    @FXML
    private JFXTextField levelTxt;
    @FXML
    private JFXTextField scoreTxt;
    
    StageManager stager = StageManager.getStageManager();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = stager.getUser();
        userTxt.setText(user.getUsername());
        emailTxt.setText(user.getEmail());
        nomTxt.setText(user.getNom());
        prenomTxt.setText(user.getPrenom());
        telTxt.setText(user.getTel()+"");
        adrTxt.setText(user.getAdresse());
        levelTxt.setText(user.getLevel()+"");
        scoreTxt.setText(user.getScore()+"");
    }    
    
}
