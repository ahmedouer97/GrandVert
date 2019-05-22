/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Jardin;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author psn
 */
public class DaysListController_1 implements Initializable {

    @FXML
    private GridPane oneGrid;
    @FXML
    private Label oneLbl;
    @FXML
    private JFXButton oneNote;
    @FXML
    private TableView<?> oneNoteTable;
    @FXML
    private TableColumn<?, ?> oneNoteModCol;
    @FXML
    private TableColumn<?, ?> oneNoteDelCol;
    @FXML
    private TableView<?> onePlantTable;
    @FXML
    private TableColumn<?, ?> onePlantModCol;
    @FXML
    private TableColumn<?, ?> onePlantDelCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addNote(ActionEvent event) {
    }

    @FXML
    private void addPlantation(ActionEvent event) {
    }
    
}
