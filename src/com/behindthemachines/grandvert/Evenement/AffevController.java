/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Evenement;

import com.behindthemachines.grandvert.entity.Evenement;
import com.behindthemachines.grandvert.entity.Participants;
import com.behindthemachines.grandvert.services.EvenementService;
import com.behindthemachines.grandvert.services.ParticipantsService;
import com.behindthemachines.grandvert.utils.StageManager;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import javax.swing.JOptionPane;
import com.teknikindustries.bulksms.SMS;






/**
 * FXML Controller class
 *
 * @author Bouazza Med
 */
public class AffevController implements Initializable {

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
    private Button p;
    @FXML
    private Button np;
    @FXML
    private Button inv;
    @FXML
    private TextField tel;
    @FXML
    private Label org;
    @FXML
    private Label des;
    @FXML
    private ImageView photo;
static int id;
    @FXML
    private AnchorPane root;
    
   
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
        
         id=table.getSelectionModel().getSelectedItem().getId();
         EvenementService es =new EvenementService();
         Evenement e=es.getById(id);
                System.out.println(id);
               File file = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\"+e.getImage());
        Image image=new Image(file.toURI().toString());
     photo.setImage(image);
                }
            }
        });        
    }    
    public void load(){
         EvenementService es =new EvenementService();
         
                 ArrayList evenement=(ArrayList) es.getByce().stream().filter(o->o.getConfa()==1).collect(Collectors.toList());  
                  ObservableList observableList = FXCollections.observableArrayList(evenement);
               table.setItems(observableList);                     
    }
 EvenementService ess =new EvenementService();
    @FXML
    private void p(ActionEvent event) {
    
          ParticipantsService ps =new ParticipantsService();
if(ps.getBy2Id(id,StageManager.getStageManager().getUser().getId())==null){
    Participants e = new Participants(id, StageManager.getStageManager().getUser().getId());
ps.add(e);
}
          
    }

    @FXML
    private void np(ActionEvent event) {
                  ParticipantsService ps =new ParticipantsService();
        if(ps.getBy2Id(id,1)!=null){
    Participants t = new Participants(id, 1);
ps.delete(t);
}
    }

    @FXML
    private void inv(ActionEvent event) {
        SMS smsTut = new SMS ();
                smsTut.SendSMS("hamma19", "Azerty/2019", "vous êtes invité", tel.getText(), "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
    }
        
   

}
