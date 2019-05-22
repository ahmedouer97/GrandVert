/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Achat;

import com.behindthemachines.grandvert.entity.Commande;
import com.behindthemachines.grandvert.entity.Notification;
import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.services.CommandeService;
import com.behindthemachines.grandvert.services.NotificationService;
import com.behindthemachines.grandvert.services.PlanteService;
import com.behindthemachines.grandvert.utils.Alertbox;
import com.behindthemachines.grandvert.utils.ToPdf;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
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
public class GestionCommandeController implements Initializable {

    @FXML
    private TableColumn<Commande, String> nom;
    @FXML
    private TableColumn<Commande, Integer> cont;
    @FXML
    private TableColumn<Commande, String> etat;
    @FXML
    private TableColumn<Commande, Timestamp> date;
    @FXML
    private TableColumn<Commande, Float> total;
    @FXML
    private TableColumn<Commande, Integer> stock;
    @FXML
    private JFXButton acc;
    @FXML
    private JFXButton ref;
    @FXML
    private TableView<Commande> table;
private Commande com;
    @FXML
    private ChoiceBox<String> choix;
    @FXML
    private JFXButton pdf;
    @FXML
    private TableColumn<Commande, String> nomu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
         load();
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         cont.setCellValueFactory(new PropertyValueFactory<>("contite"));
         etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
         total.setCellValueFactory(new PropertyValueFactory<>("total"));
       stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        nomu.setCellValueFactory(new PropertyValueFactory<>("nomu"));
      acc.setDisable(true);
        ref.setDisable(true);
          pdf.setDisable(true);
        ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
         selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if(table.getSelectionModel().getSelectedItem()!=null){
                      pdf.setDisable(false);
                    com=table.getSelectionModel().getSelectedItem();
                    if(table.getSelectionModel().getSelectedItem().getEtat().equals("en cours...")){
        acc.setDisable(false);
        ref.setDisable(false);
                  }else{ acc.setDisable(true);
        ref.setDisable(true);
                    }
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
           ArrayList coms=(ArrayList) sc.getAll();
                  ObservableList<Commande> observableList = FXCollections.observableArrayList(coms);
              ;
                    table.setItems(observableList);                             
    }
 public void loadbyetat(String etat){
     if(etat=="tous")load();else{
          CommandeService sc=new CommandeService();
           ArrayList coms=(ArrayList) sc.getByetat(etat);
                  ObservableList<Commande> observableList = FXCollections.observableArrayList(coms);           
                    table.setItems(observableList);    }                         
    }
    @FXML
    private void acc(ActionEvent event) {
           CommandeService sc=new CommandeService();
         PlanteService sp=new PlanteService();
         Plante p=sp.getById(com.getPlante().getId());
         if(p.getStock()-com.getContite()>0){
          Alert altert =new Alert(Alert.AlertType.CONFIRMATION);
        altert.setTitle("confirmation du acceptation");
        altert.setHeaderText(null);
        altert.setContentText("voulez-vous accepter cette commande");
        Optional <ButtonType>action=altert.showAndWait();
        if(action.get()==ButtonType.OK){
         p.setStock(p.getStock()-com.getContite());
         com.setEtat("Valider");
         sp.update(p);
         sc.update(com);
         acc.setDisable(true);
        ref.setDisable(true);
        sendNotification("accepter commande","Votre commande est accepter",com.getUser().getId());
       load();  
        }
        
         }
         else Alertbox.display("erreur","on acceptons cette commande le stock de sette plante sera negative");
    }
    @FXML
    private void ref(ActionEvent event) {
          Alert altert =new Alert(Alert.AlertType.CONFIRMATION);
        altert.setTitle("confirmation du refus");
        altert.setHeaderText(null);
        altert.setContentText("voulez-vous refuser cette commande");
        Optional <ButtonType>action=altert.showAndWait();
        if(action.get()==ButtonType.OK){
        CommandeService sc=new CommandeService();     
         com.setEtat("Rejeter");
         sendNotification("refus commande","Votre commande est refuser",com.getUser().getId());
         sc.update(com);
         acc.setDisable(true);
        ref.setDisable(true);
       load(); 
        }
         
    }

    @FXML
    private void pdf(ActionEvent event) {
        ToPdf.pdf(com.getId(), com.getNom(), com.getContite(), com.getPlante().getPrix(), com.getTotal(), com.getDate(),com.getUser().getUsername());
    }
        public void sendNotification(String title , String description , int id_user){
        NotificationService ns = new NotificationService();
        Notification n = new Notification(id_user, title, description, new Date(), "route", 0);
        ns.add(n);
    }
}
