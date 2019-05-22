package com.behindthemachines.grandvert.Membre;

import com.behindthemachines.grandvert.entity.User;
import com.behindthemachines.grandvert.services.JardinService;
import com.behindthemachines.grandvert.services.UserService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXTextField usrTxt;

    @FXML
    private JFXPasswordField pwTxt;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private Label errorMsg;
    
    UserService userService = new UserService();
    
    JardinService jardinService = new JardinService();

    @FXML
    void seConnecter(ActionEvent event) {
        String username = usrTxt.getText().trim();
        String pw = pwTxt.getText().trim();
        User user=userService.checkLogin(username, pw);
        if(user!=null){
            errorMsg.setVisible(false);
            StageManager stager = StageManager.getStageManager();
            stager.setUser(user);
            
           stager.setJardin(jardinService.getByUser(user.getId()));
            
            System.out.println("User: "+stager.getUsername());
            System.out.println("Role: "+stager.getRoles());
              
            stager.Dashboard();
        }
        else{
            errorMsg.setVisible(true);
            errorMsg.setText("Login ou mot de passe incorrecte!");
            System.out.println("USER DOESNT EXIST");
        }
        
    }

    @FXML
    void signUp(ActionEvent event) {
        try {
            URL signIn = getClass().getResource("Signup.fxml");
            AnchorPane anchorpane = FXMLLoader.load(signIn);
            
            anchor.getChildren().clear();
            anchor.getChildren().add(anchorpane);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
