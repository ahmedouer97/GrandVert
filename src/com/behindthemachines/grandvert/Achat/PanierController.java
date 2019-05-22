/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Achat;


import com.behindthemachines.grandvert.Plante.AfficheController;
import com.behindthemachines.grandvert.Plante.ConsulterController;
import com.behindthemachines.grandvert.entity.Commande;
import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.services.CommandeService;
import com.behindthemachines.grandvert.utils.Alertbox;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class PanierController implements Initializable {
 @FXML
    private TableView<Plante> table;
    @FXML
    private TableColumn<Plante, String> nom;
    @FXML
    private TableColumn<Plante, Float> prix;
    @FXML
    private TableColumn<Plante, Integer> stock;
    @FXML
    private JFXButton  delete;
    @FXML
    private JFXTextField  nomp;
    @FXML
    private Spinner<Integer> cont;
    @FXML
    private JFXTextField  total;
    @FXML
    private JFXButton  com;
    private float prie=0;
    private String p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
          load();
           com.setDisable(true);
        delete.setDisable(true);
          SpinnerValueFactory <Integer> conts=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
           this.cont.setValueFactory(conts);
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         stock.setCellValueFactory(new PropertyValueFactory<>("stock")); 
         ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
         selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if(table.getSelectionModel().getSelectedItem()!=null){
         nomp.setText(table.getSelectionModel().getSelectedItem().getNom());
         prie=table.getSelectionModel().getSelectedItem().getPrix();
         ConsulterController.id =table.getSelectionModel().getSelectedItem().getId();
         SpinnerValueFactory <Integer> conts=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,table.getSelectionModel().getSelectedItem().getStock());
          cont.setValueFactory(conts);
         cont.getValueFactory().setValue(0);
                System.out.println(ConsulterController.id);
                 com.setDisable(false);
        delete.setDisable(false);}
            }
        });  
    
        
         this.cont.valueProperty().addListener(new ChangeListener<Integer>() {
              @Override
              public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                  System.out.println(newValue);
                  total.setText(Float.toString(prie*newValue));
              }
          });
    }    
  public void load(){ 
                  ObservableList observableList = FXCollections.observableArrayList(AfficheController.plantes);
               table.setItems(observableList);                     
    }

    @FXML
    private void delete(ActionEvent event) {
            Alert altert =new Alert(Alert.AlertType.CONFIRMATION);
        altert.setTitle("confirmation du refus");
        altert.setHeaderText(null);
        altert.setContentText("voulez-vous refuser cette commande");
        Optional <ButtonType>action=altert.showAndWait();
        if(action.get()==ButtonType.OK){
             for(int j = 0; j <   AfficheController.plantes.size(); j++)
{
    if(AfficheController.plantes.get(j).getId()==table.getSelectionModel().getSelectedItem().getId()){
        AfficheController.plantes.remove(j);
        break;
    }
}
          nomp.setText("");
           com.setDisable(true);
        delete.setDisable(true);
 load(); 
        }
            
    }




    @FXML
    private void com(ActionEvent event) {
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
        CommandeService pc =new CommandeService();
        pc.add(new Commande(table.getSelectionModel().getSelectedItem(), cont.getValue(), "en cours...", timestamp,StageManager.getStageManager().getUser()));
        for(int j = 0; j <   AfficheController.plantes.size(); j++)
{
    if(AfficheController.plantes.get(j).getId()==table.getSelectionModel().getSelectedItem().getId()){
       //found, delete.
        AfficheController.plantes.remove(j);
        break;
    }
}
 load();    
        Alertbox.display("Succés", "commande envoyer");
        nomp.setText("");
        com.setDisable(true);
        delete.setDisable(true);
    }
           
}
