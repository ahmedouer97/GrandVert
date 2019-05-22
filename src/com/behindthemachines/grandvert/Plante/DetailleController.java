/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Plante;

import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.services.PlanteService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class DetailleController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label season;
    @FXML
    private Label fert;
    @FXML
    private Label haut;
    @FXML
    private Label prix;
    @FXML
    private Label stock;
    @FXML
    private Label cat;
    @FXML
    private Label desc;
    @FXML
    private ImageView photo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         PlanteService ps =new PlanteService();
         Plante p=ps.getById(ConsulterController.id);
        nom.setText("Nom: "+p.getNom());
        stock.setText("Stock: "+p.getStock());
        season.setText("season: "+p.getSeason());
        fert.setText("fertélissuer: "+p.getFertiliseur());
        haut.setText("Hauteur: "+p.getHauteur());
        prix.setText("prix: "+p.getPrix());
        cat.setText("catégorie: "+p.getNom());
        File file = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\"+p.getPhoto());
        desc.setText(p.getDescription());
        Image image=new Image(file.toURI().toString());
        System.out.println(file.toURI().toString());
     photo.setImage(image);
    }    
}
