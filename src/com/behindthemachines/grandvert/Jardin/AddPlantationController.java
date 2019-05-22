/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Jardin;

import com.behindthemachines.grandvert.entity.Plantation;
import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.services.PlantationService;
import com.behindthemachines.grandvert.services.PlanteService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author psn
 */
public class AddPlantationController implements Initializable{
    
    @FXML
    private JFXTextField qtTxt;

    @FXML
    private JFXTextField typeTxt;

    @FXML
    private JFXComboBox<Plante> plantesTxt;
    
    PlantationService plService = new PlantationService();
    
    PlanteService pService = new PlanteService();
    
    private int jardinId;
    
    private String date;
    
    public AddPlantationController(int jardinId, String date){
        this.jardinId=jardinId;
        this.date=date;
    }
    
    public AddPlantationController(){
    }

    @FXML
    void addNote(ActionEvent event) throws ParseException {
        Plantation pl = new Plantation();
           java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
           java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           pl.setJardin(jardinId);
           pl.setDateP(sqlDate);
           pl.setTypeP(typeTxt.getText());
           pl.setQt(Integer.parseInt(qtTxt.getText()));
           pl.setPlante(plantesTxt.getValue().getId());
           plService.add(pl);
    }

    @FXML
    void closeWindow(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plantesTxt.getItems().clear();
        plantesTxt.getItems().addAll(pService.getAll());
    }
    
}
