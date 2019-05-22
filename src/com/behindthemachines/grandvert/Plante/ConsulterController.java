/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Plante;

import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.services.PlanteService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ConsulterController implements Initializable {

    @FXML
    private TableView<Plante> table;
    @FXML
    private TableColumn<Plante, String> nom;
    @FXML
    private TableColumn<Plante, Float> prix;
    @FXML
    private TableColumn<Plante, Integer> stock;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXTextField  nomp;
    public static int id;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField rech;
    @FXML
    private JFXButton det;
    @FXML
    private JFXButton mod;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         stock.setCellValueFactory(new PropertyValueFactory<>("stock")); 
         ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
         selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if(table.getSelectionModel().getSelectedItem()!=null){
         nomp.setText(table.getSelectionModel().getSelectedItem().getNom());
         id=table.getSelectionModel().getSelectedItem().getId();
               det.setDisable(false);
                mod.setDisable(false);
                delete.setDisable(false);}
            }
           
        });   
          rech.textProperty().addListener((observable, oldValue, newValue) -> {
    loadbynom(newValue);
});
    }    
    public void load(){
         PlanteService ps =new PlanteService();
         
                 ArrayList plantes=(ArrayList) ps.getAll().stream().filter(o->o.getProposition()==0).collect(Collectors.toList());  
                  ObservableList observableList = FXCollections.observableArrayList(plantes);
               table.setItems(observableList);                     
    }
    public void loadbynom(String nom){
         PlanteService ps =new PlanteService();
         
                 ArrayList plantes=(ArrayList) ps.getBynom(nom).stream().filter(o->o.getProposition()==0).collect(Collectors.toList());  
                  ObservableList observableList = FXCollections.observableArrayList(plantes);
               table.setItems(observableList);                     
    }
 PlanteService pss =new PlanteService();
    @FXML
    private void delete(ActionEvent event) {
        Alert altert =new Alert(Alert.AlertType.CONFIRMATION);
        altert.setTitle("confirmation du suppression");
        altert.setHeaderText(null);
        altert.setContentText("voulez-vous supprimer cette plante "+table.getSelectionModel().getSelectedItem().getNom());
        Optional <ButtonType>action=altert.showAndWait();
        if(action.get()==ButtonType.OK){
          pss.delete(table.getSelectionModel().getSelectedItem());
           det.setDisable(true);
                mod.setDisable(true);
                delete.setDisable(true);
          nomp.setText("");
          load();
        }
    }

    @FXML
    private void det(ActionEvent event) {     
            try {
           
             FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("detaille.fxml"));
            Parent root1=(Parent)fxmlloader.load();
             Stage stage= new Stage();
             stage.setScene(new Scene(root1));
             stage.show();
             
             
        } catch (IOException ex) {
            Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
        }
                               
    }

    @FXML
    private void modifier(ActionEvent event) {
        StageManager stageManager = StageManager.getStageManager();
            stageManager.Load("Plante/modifier");
    }

    @FXML
    private void det(MouseEvent event) {
    }
}
