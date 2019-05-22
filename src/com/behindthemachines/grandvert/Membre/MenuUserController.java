/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Membre;

import com.behindthemachines.grandvert.entity.Notification;
import com.behindthemachines.grandvert.services.NotificationService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.behindthemachines.grandvert.utils.WeatherApi;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
//import net.aksingh.owmjapis.api.APIException;

/**
 * FXML Controller class
 *
 * @author psn
 */
public class MenuUserController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private JFXButton profileBtn;
    @FXML
    private JFXButton logoutBtn;
    
    StageManager stageManager = StageManager.getStageManager();
    @FXML
    private Pane PaneListNotification;
    @FXML
    private ImageView trianglenotification;
    @FXML
    private JFXListView<HBoxCell> ListNotification;
    @FXML
    private Label nbNotification;
    
    private ArrayList<Notification> Data;
    private ObservableList<HBoxCell> myObservableList;    
    @FXML
    private MenuItem addev;
    @FXML
    private MenuItem gomev;
    @FXML
    private MenuItem gopart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        usernameLabel.setText("Hello "+StageManager.getStageManager().getUsername());
        // TODO
        
        PaneListNotification.setVisible(false);
                trianglenotification.setVisible(false);

                List<HBoxCell> list = new ArrayList<>();
                //List de notification de user
                NotificationService ns = new NotificationService(); //Notification Service
                Data = new ArrayList<>(ns.getAll(StageManager.getStageManager().getUser().getId()));
                nbNotification.setText(Data.size()+"");
                ListNotification.getItems().clear();
                //UserService us = new UserService(); //service user

                //ajouter les notifications au list
                for (Notification n :  Data){
                    list.add(new HBoxCell(n.getTitle() , n.getDescription() , n.getId() ));
                }

                myObservableList = FXCollections.observableList(list);
                ListNotification.setItems(myObservableList); 


                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            try {
                                RefreshNotification();
                            } catch (IOException ex) {
                                Logger.getLogger(MenuUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }



                        });
                    }
                }, 1000, 1000);        
        WeatherApi w = new WeatherApi();
        try {
           // System.out.println(w.Getweather());
          //  usernameLabel.setText(w.Getweather());
        } catch (Exception ex) {
            Logger.getLogger(MenuUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void goProfile(ActionEvent event) {
        stageManager.Load("Membre/MonProfile");
    }

    @FXML
    private void disconnect(ActionEvent event) {
         StageManager.getStageManager().LoginScreen();
    }

    @FXML
    private void goForum(ActionEvent event) {
        stageManager.Load("Forum/CategorieFXML");
    }

    @FXML
    private void goplante(ActionEvent event) {
         stageManager.Load("Plante/affiche");
    }

    @FXML
    private void gocommande(ActionEvent event) {
         stageManager.Load("Achat/consultercommande");
    }

    @FXML
    private void gopanier(ActionEvent event) {
      stageManager.Load("Achat/panier");
    }

    @FXML
    private void goprop(ActionEvent event) {
         stageManager.Load("Plante/proposerplante");
    }

    @FXML
    private void goJardin(ActionEvent event) {
        stageManager.Load("Jardin/Jardin");
    }

    @FXML
    private void goReservation(ActionEvent event) {
        stageManager.Load("Preservation/GestionReservation");
    }

    @FXML
    private void goVideo(ActionEvent event) {
        stageManager.Load("Preservation/FXMLVideo");
    }

    @FXML
    private void goev(ActionEvent event) {
        stageManager.Load("Evenement/affev");
    }

    @FXML
    private void addev(ActionEvent event) {
         stageManager.Load("Evenement/AjouterEv");
    }

    @FXML
    private void gomev(ActionEvent event) {
        stageManager.Load("Evenement/affev_2");
    }

    @FXML
    private void gopart(ActionEvent event) {
         stageManager.Load("Evenement/mespart");
    }

    public class HBoxCell extends HBox {
        Label LabelNom = new Label();
        ImageView ImgPlante = new ImageView();
        //Label LabelNbSujet = new Label("", this);
        
        HBoxCell(String Title, String Description , int id_notification) {
            super();

            this.getChildren().addAll(new VBoxRow1(Title ,Description ) , new VBoxRow2(id_notification));
            //this.get
        }
    } 
    
    //VBox for image user column 1 
    public class VBoxRow1 extends VBox {
        Label LabelTitle = new Label();
        Label LabelDescription = new Label();
        
        VBoxRow1(String Title, String Description) {
            super();
            //Label titre notification
            LabelTitle.setText(Title);
            LabelTitle.setMaxWidth(Double.MAX_VALUE);
            LabelTitle.setFont(Font.font("Cambria", 15));
            VBox.setVgrow(LabelTitle, Priority.ALWAYS);
            
            //Label description notification
            LabelDescription.setText(Description);
            this.setPrefWidth(365);
            this.getChildren().addAll(LabelTitle , LabelDescription);
        }
    } 
    
    //VBox for image user column 1 
    public class VBoxRow2 extends VBox {
        ImageView Image = new ImageView("/Icon/close.png");
        
        VBoxRow2(int id_notification) {
            super();
            //Image titre notification
            Image.setFitHeight(15);
            Image.setFitWidth(15);
            this.setPrefSize(15, 50);
            
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("id_notification "+id_notification);
                    DeleteNotification(id_notification);                }
            });
            this.getChildren().addAll(Image);
        }
    }     

    
    public void RefreshNotification() throws IOException{
        
        List<HBoxCell> list = new ArrayList<>();
        //List de notification de user
        NotificationService ns = new NotificationService(); //Notification Service
        Data = new ArrayList<>(ns.getAll(StageManager.getStageManager().getUser().getId()));
        nbNotification.setText(Data.size()+"");

            ListNotification.getItems().clear();
            //UserService us = new UserService(); //service user

            //ajouter les notifications au list
            for (Notification n :  Data){
                list.add(new HBoxCell(n.getTitle() , n.getDescription(), n.getId() ));
            }

            ObservableList<HBoxCell> myObservableNewList = FXCollections.observableList(list);
            ListNotification.setItems(myObservableNewList); 

    }  
   
    
    public void DeleteNotification(int id_notification){
        NotificationService ns = new NotificationService(); //Notification Service
        Notification n = ns.getById(id_notification);
        n.setSeen(1);
        ns.update(n);
    }
    
    @FXML
    private void ShowNotification(MouseEvent event) {
        if(!PaneListNotification.isVisible()){
            PaneListNotification.setVisible(true);
            trianglenotification.setVisible(true);
        }else
        {
            PaneListNotification.setVisible(false);
            trianglenotification.setVisible(false);
        }
        
    }
    
    
}
