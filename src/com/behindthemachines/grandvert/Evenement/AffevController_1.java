/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Evenement;

import com.behindthemachines.grandvert.entity.Evenement;
import com.behindthemachines.grandvert.services.EvenementService;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.net.URL;
import javafx.scene.input.MouseEvent;



/**
 * FXML Controller class
 *
 * @author Bouazza Med
 */
public class AffevController_1 implements Initializable {

    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String> ville;
    @FXML
    private TableColumn<Evenement, Date> dated;
    @FXML
    private TableColumn<Evenement, Date> datef;
    @FXML
    private Button conf;
    @FXML
    private Button ann;
    @FXML
    private Label org;
    @FXML
    private Label des;
    @FXML
    private Label at;
    @FXML
    private Label at1;
    @FXML
    private ImageView photo;
static int id;
    @FXML
    private AnchorPane root;
    @FXML
    private Label prix1;
    @FXML
    private Label prix11;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
         titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
         dated.setCellValueFactory(new PropertyValueFactory<>("dated"));
         datef.setCellValueFactory(new PropertyValueFactory<>("datef"));
         ville.setCellValueFactory(new PropertyValueFactory<>("lieu"));
         ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
         selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if(table.getSelectionModel().getSelectedItem()!=null){
         org.setText(table.getSelectionModel().getSelectedItem().getOrganisation());
         des.setText(table.getSelectionModel().getSelectedItem().getDescription());
        
         if(table.getSelectionModel().getSelectedItem().getConfa()==1)
         {at.setText("Confirmé");}
         else{at.setText("Non Confirmé");}
         if(table.getSelectionModel().getSelectedItem().getEtat()==0)
         {at1.setText("à venir");}
         else if(table.getSelectionModel().getSelectedItem().getEtat()==1)
         {at1.setText("Annulé");}
         else {at1.setText("Terminé");}
         id=table.getSelectionModel().getSelectedItem().getId();
         EvenementService es =new EvenementService();
         Evenement e=es.getById(id);
                System.out.println(id);
                    System.out.println(e.getImage());
               File file = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\"+e.getImage());
        Image image=new Image(file.toURI().toString());
        System.out.println(file.toURI().toString());
     photo.setImage(image);
                }
            }
        });        
    }    
    public void load(){
         EvenementService es =new EvenementService();
         
                 ArrayList evenement=(ArrayList) es.getBync().stream().collect(Collectors.toList());  
                  ObservableList observableList = FXCollections.observableArrayList(evenement);
               table.setItems(observableList);                     
    }
 EvenementService ess =new EvenementService();
    @FXML
    private void conf(ActionEvent event) {
      
             EvenementService ps =new EvenementService();
ps.confa(id);
          load();

          
          
    }

    @FXML
    private void ann(ActionEvent event) {
           EvenementService ps =new EvenementService();
ps.etat1(id);
    }

    
   

}
