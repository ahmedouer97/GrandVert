/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Achat;

import com.behindthemachines.grandvert.entity.Commande;
import com.behindthemachines.grandvert.services.CommandeService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ConsultercommandeController implements Initializable {
 
    @FXML
    private TableColumn<Commande, String> nom;
    @FXML
    private TableColumn<Commande, Integer> cont;
    @FXML
    private TableColumn<Commande, Timestamp> date;
    @FXML
    private TableColumn<Commande, Float> total;
    @FXML
    private TableColumn<Commande, String> etat;
    @FXML
    private TableView<Commande> table;

    @FXML
    private JFXButton ann;
    
private Commande com;
    @FXML
    private ChoiceBox<String> choix;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       load();
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         cont.setCellValueFactory(new PropertyValueFactory<>("contite"));
         etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
         total.setCellValueFactory(new PropertyValueFactory<>("total"));
      ann.setDisable(true);  
      table.setRowFactory(row -> new TableRow<Commande>(){
    @Override
    public void updateItem(Commande item, boolean empty){
        super.updateItem(item, empty);
        if (item == null || empty) {
            setStyle("");
        } else {
            //Now 'item' has all the info of the Person in this row
            if (item.getEtat().equals("Annuler")) {
                             setStyle("-fx-background-color: gray;");           
            }
            else if(item.getEtat().equals("Valider")) {
                             setStyle("-fx-background-color: green;");           
            }else if(item.getEtat().equals("Rejeter")) {
                             setStyle("-fx-background-color: red;");           
            };
        }
    }
});
        ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
         selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if(table.getSelectionModel().getSelectedItem()!=null){
                    com=table.getSelectionModel().getSelectedItem();
                    if(table.getSelectionModel().getSelectedItem().getEtat().equals("en cours..."))
        ann.setDisable(false);
                    else  ann.setDisable(true);
                }
            }
        }); 
         choix.getItems().addAll("tous","en cours...","Valider","Rejeter","Annuler");
       choix.setValue("tous");
       choix.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               loadbyetat(choix.getItems().get((Integer) newValue));
            }
        });

    }    
 public void load(){
          CommandeService sc=new CommandeService();
           ArrayList coms=(ArrayList) sc.getAll().stream().filter(o->o.getUser().getId()==StageManager.getStageManager().getUser().getId()).collect(Collectors.toList());
                  ObservableList<Commande> observableList = FXCollections.observableArrayList(coms);                  
                    table.setItems(observableList);                             
    }
 public void loadbyetat(String etat){
     if(etat=="tous")load();else{
          CommandeService sc=new CommandeService();
           ArrayList coms=(ArrayList) sc.getByetat(etat).stream().filter(o->o.getUser().getId()==StageManager.getStageManager().getUser().getId()).collect(Collectors.toList());;
                  ObservableList<Commande> observableList = FXCollections.observableArrayList(coms);           
                    table.setItems(observableList);    }                         
    }
    @FXML
    private void ann(ActionEvent event) {
           Alert altert =new Alert(Alert.AlertType.CONFIRMATION);
        altert.setTitle("confirmation du refus");
        altert.setHeaderText(null);
        altert.setContentText("voulez-vous annuler cette commande");
        Optional <ButtonType>action=altert.showAndWait();
        if(action.get()==ButtonType.OK){
        CommandeService sc=new CommandeService();       
         com.setEtat("Annuler");
         sc.update(com);
         ann.setDisable(true);
       load(); 
        }
         
       
    }
}
