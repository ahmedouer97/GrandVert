/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Plante;

import com.behindthemachines.grandvert.entity.Notification;
import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.services.NotificationService;
import com.behindthemachines.grandvert.services.PlanteService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class GestionpropositionController implements Initializable {
 @FXML
    private TableView<Plante> table;
    @FXML
    private TableColumn<Plante, String> nom;
    @FXML
    private TableColumn<Plante, Float> prix;
    @FXML
    private TableColumn<Plante, Float> stock;
    @FXML
    private JFXTextField  nomp;
    @FXML
    private JFXButton  ref;
    @FXML
    private JFXButton  acc;
    @FXML
    private JFXButton  det;
   
    @FXML
    private ChoiceBox<String> cat;
    @FXML
    private TableColumn<Plante, String> nomu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       load();
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         stock.setCellValueFactory(new PropertyValueFactory<>("stock")); 
         nomu.setCellValueFactory(new PropertyValueFactory<>("nomu")); 
         ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
         selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if(table.getSelectionModel().getSelectedItem()!=null){
         nomp.setText(table.getSelectionModel().getSelectedItem().getNom());
         ConsulterController.id=table.getSelectionModel().getSelectedItem().getId();
              ref.setDisable(false);
                 acc.setDisable(false);
                 det.setDisable(false);}
            }
        }); 
    cat.getItems().addAll("tous","Fruit","Legume","Fleur","Herbe");
       cat.setValue("tous");
       cat.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               loadbycat(cat.getItems().get((Integer) newValue));
            }
        });
    }    
    PlanteService ps =new PlanteService();
 public void load(){
                 ArrayList plantes=(ArrayList) ps.getAll().stream().filter(o->o.getProposition()==1).collect(Collectors.toList());  
                  ObservableList observableList = FXCollections.observableArrayList(plantes);
               table.setItems(observableList);                     
    }
 public void loadbycat(String cat){
         PlanteService ps =new PlanteService();
         if(cat=="tous")load();else{
                 ArrayList plantes=(ArrayList) ps.getByCat(cat).stream().filter(o->o.getProposition()==1).collect(Collectors.toList());  
                  ObservableList observableList = FXCollections.observableArrayList(plantes);
               table.setItems(observableList);}                     
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
    private void ref(ActionEvent event) {
        
          
             Alert altert =new Alert(Alert.AlertType.CONFIRMATION);
        altert.setTitle("confirmation du rejet");
        altert.setHeaderText(null);
        altert.setContentText("voulez-vous rejeter cette proposition ");
        Optional <ButtonType>action=altert.showAndWait();
        if(action.get()==ButtonType.OK){
         table.getSelectionModel().getSelectedItem().setProposition(2);
         this.ps.update(table.getSelectionModel().getSelectedItem());
          sendNotification("Proposition refuser","Votre proposition est refuser",table.getSelectionModel().getSelectedItem().getUser().getId());
          nomp.setText("");
          load();
          ref.setDisable(true);
                 acc.setDisable(true);
                 nomp.setText("");
        }
    }

    @FXML
    private void acc(ActionEvent event) {
      Alert altert =new Alert(Alert.AlertType.CONFIRMATION);
        altert.setTitle("confirmation du acceptation");
        altert.setHeaderText(null);
        altert.setContentText("voulez-vous accepter cette proposition ");
        Optional <ButtonType>action=altert.showAndWait();
        if(action.get()==ButtonType.OK){
         table.getSelectionModel().getSelectedItem().setProposition(0);
         this.ps.update(table.getSelectionModel().getSelectedItem());
         sendNotification("Proposition Accepter","Votre proposition est Accpter",table.getSelectionModel().getSelectedItem().getUser().getId());
          nomp.setText("");
          load();
            ref.setDisable(true);
                 acc.setDisable(true);
                 nomp.setText("");
        }
    }
    public void sendNotification(String title , String description , int id_user){
        NotificationService ns = new NotificationService();
        Notification n = new Notification(id_user, title, description, new Date(), "route", 0);
        ns.add(n);
    }
}
