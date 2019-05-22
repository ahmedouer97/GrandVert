/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Membre;

import com.behindthemachines.grandvert.entity.Notification;
import com.behindthemachines.grandvert.services.NotificationService;
import com.behindthemachines.grandvert.utils.StageManager;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author psn
 */
public class MenuAdminController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private JFXButton profileBtn;
    @FXML
    private JFXButton logoutBtn;
    @FXML
    private MenuItem addItem;
    @FXML
    private MenuItem planteItem;
    @FXML
    private MenuItem propItem;
    @FXML
    private MenuItem archiveItem;
    
    StageManager stager = StageManager.getStageManager();
    @FXML
    private MenuButton plantebtn;
    @FXML
    private MenuButton preservationbtn;
    @FXML
    private Pane PaneListNotification;
    @FXML
    private JFXListView<HBoxCell> ListNotification;
    @FXML
    private Label nbNotification;
    @FXML
    private ImageView trianglenotification;
    
    private ArrayList<Notification> Data;
    private ObservableList<HBoxCell> myObservableList;
    @FXML
    private Button dash;
    @FXML
    private Button achat;
    @FXML
    private Button ev;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText("Bienvenue "+stager.getUsername());
        if(stager.getUser().getRoles().equals("a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}")){
            plantebtn.setVisible(true);
            preservationbtn.setVisible(true);
            achat.setVisible(true);
           
        }else
        {
            plantebtn.setVisible(false);
            preservationbtn.setVisible(false);
            achat.setVisible(false);
           
        }
        
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
                                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                        });
                    }
                }, 1000, 1000);        
        
    }    

    @FXML
    private void goDashboard(ActionEvent event) {
         stager.Load("Membre/Dashboard");
    }


    @FXML
    private void goProfile(ActionEvent event) {
    }

    @FXML
    private void disconnect(ActionEvent event) {
        StageManager.getStageManager().LoginScreen();
    }

    @FXML
    private void goAjouterPlante(ActionEvent event) {
         stager.Load("Plante/add");
    }

    @FXML
    private void goConsulterPlante(ActionEvent event) {
         stager.Load("Plante/Consulter");
    }

    @FXML
    private void goConsulterProp(ActionEvent event) {
         stager.Load("Plante/gestionproposition");
    }

    @FXML
    private void goArchive(ActionEvent event) {
        stager.Load("Plante/archiveplante");
    }

    
    private void goAjouterUser(ActionEvent event) {
    }

    @FXML
    private void goConsulterUser(ActionEvent event) {
        stager.Load("Membre/ListUsers");
        
    }

    @FXML
    private void goAchat(ActionEvent event) {
        stager.Load("Achat/gestionCommande");
    }

    @FXML
    private void goForum(ActionEvent event) {
         stager.Load("Forum/CategorieFXML");
    }

    @FXML
    private void goReserv(ActionEvent event) {
        stager.Load("Preservation/ResForAdmin");
    }

    @FXML
    private void goEspace(ActionEvent event) {
        stager.Load("Preservation/GestionEspace");
    }

    @FXML
    private void goHistor(ActionEvent event) {
        stager.Load("Preservation/ExpirationDate");
    }

    @FXML
    private void goev(ActionEvent event) {
        stager.Load("Evenement/affev_1");
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
