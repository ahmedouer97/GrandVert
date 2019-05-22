/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Forum;

import com.behindthemachines.grandvert.entity.Sujet;
import com.behindthemachines.grandvert.services.ReponseService;
import com.behindthemachines.grandvert.services.SujetService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ROCH
 */
public class MesSujetsFXMLController implements Initializable {

    @FXML
    private AnchorPane ListSujetInterface;
    @FXML
    private Label LblListSujet;
    @FXML
    private JFXListView<HBoxCell> ListSujet;
    private ArrayList<Sujet> Data;
    
    StageManager stageManager = StageManager.getStageManager();
    
    private int ConnectedUser= stageManager.getUser().getId();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<HBoxCell> list = new ArrayList<>();
        //List de sujet de plante selectionner par l'utilisateur
        SujetService ss = new SujetService(); //service sujet
        Data = new ArrayList<>(ss.getAllSujetUser(ConnectedUser));

        ReponseService rs = new ReponseService(); //service reponse
        //UserService us = new UserService(); //service user
        
        //ajouter les Sujet au list
        for (Sujet s :  Data){
            list.add(new HBoxCell("UrlImageUser" , s.getUser().getPrenom()+" "+s.getUser().getNom() , s.getSujet_original() , s.getResolu() , rs.getAll(s.getId()).size() ,s.getNbviews() , s.getDate_original() , s.getId() ));
        }

        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        ListSujet.setItems(myObservableList); 
        
        ListSujet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBoxCell>() {
            @Override
            public void changed(ObservableValue<? extends HBoxCell> observable, HBoxCell oldValue, HBoxCell newValue) {

                try {
                    
                    SujetService ss = new SujetService();
                    //update nb de views aprés la consultation
                    ss.update(Data.get(ListSujet.getSelectionModel().getSelectedIndex()));
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterSujetFXML.fxml"));
                    ConsulterSujetFXMLController sujetcontroller = new ConsulterSujetFXMLController(Data.get(ListSujet.getSelectionModel().getSelectedIndex()).getId());
                    //passer le sujet selectionner par user au controller
                    
                    // Set it in the FXMLLoader
                    loader.setController(sujetcontroller);
                    
                
                    ListSujetInterface.getChildren().setAll((AnchorPane) loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(MesSujetsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                     
            }
        });         
        
    }    
    
    private void RefreshData() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesSujetsFXML.fxml"));

        ListSujetInterface.getChildren().setAll((AnchorPane) loader.load());       
    }    
    
 
    public class HBoxCell extends HBox {
        Label LabelNom = new Label();
        ImageView ImgPlante = new ImageView();
        //Label LabelNbSujet = new Label("", this);
        
        HBoxCell(String UrlImageuser, String labelUserName ,  String labelTextSujet , String resolu , int nbComment , int nbView , Date dateSujet , int id_sujet) {
            super();
            if(resolu.equals("true"))
                this.getChildren().addAll(new VBoxRow1(UrlImageuser) ,new VBoxRow2(labelUserName, labelTextSujet,id_sujet) , new VBoxRow3("/Icon/resolut.png", nbComment, nbView, dateSujet) , new VBoxRow4(id_sujet));
            else
                this.getChildren().addAll(new VBoxRow1(UrlImageuser) , new VBoxRow2(labelUserName, labelTextSujet,id_sujet) , new VBoxRow3("/Icon/nonresolut.png", nbComment, nbView, dateSujet) , new VBoxRow4(id_sujet));
            
            //this.get
        }
    } 
    
    //VBox for image user column 1 
    public class VBoxRow1 extends VBox {
        ImageView Imguser = new ImageView();
        
        VBoxRow1(String UrlImageuser) {
            super();

            //Img user
            Imguser.setImage(new Image("Icon/user4.jpeg"));
            Imguser.setFitHeight(50);
            Imguser.setFitWidth(50);

            this.getChildren().addAll(Imguser , new Label() , new Label());
        }
    }  
    
    //VBox for User & Sujet column 2 
    public class VBoxRow2 extends VBox {
        Label LabelUser = new Label();
        Label LabelSujet = new Label();
        WebView webView = new WebView();
        WebEngine webEngineSujet = webView.getEngine();

        VBoxRow2(String labelUserName ,  String labelTextSujet , int id_sujet) {
            super();

            //Label Nom user
            LabelUser.setText(labelUserName);
            LabelUser.setMaxWidth(Double.MAX_VALUE);
            LabelUser.setFont(Font.font("Cambria", 15));
            VBox.setVgrow(LabelUser, Priority.ALWAYS);
            
            //Sujet content
            webView.getEngine().loadContent(labelTextSujet, "text/html");
            webView.setPrefSize(850, 200);
            
            
            this.getChildren().addAll(LabelUser , webView);
        }
    }
    
    //VBox for nbcomment , etatsujet ,  nb view et Date sujet
    public class VBoxRow3 extends VBox {
        ImageView ImgEtatSujet = new ImageView();
        
        VBoxRow3(String UrlImageetat , int NbComment , int NbView , Date DateSujet ) {
            super();

            //Img user
            ImgEtatSujet.setImage(new Image(UrlImageetat));
            ImgEtatSujet.setFitHeight(35);
            ImgEtatSujet.setFitWidth(35);
            this.setAlignment(Pos.CENTER);
            this.getChildren().addAll(ImgEtatSujet , new Label(NbComment+"", new ImageView("Icon/comment.png")) , new Label(NbView+"", new ImageView("ICON/view.png")), new Label(DateSujet+"", new ImageView("ICON/Clock.png")));
        }
    }
    
    //VBox for list action
    public class VBoxRow4 extends VBox {
        ChoiceBox<String> menuAction = new ChoiceBox<String>();
        
        VBoxRow4(int id_sujet) {
            super();
                        
            SujetService ss = new SujetService();
            Sujet s = ss.getById(id_sujet);
            
                if(s.getResolu().equals("true"))
                    menuAction.getItems().add("Non Résolu");
                else
                    menuAction.getItems().add("Résolu");


                if(s.getOpen().equals("true"))
                    menuAction.getItems().add("Fermer");
                else
                    menuAction.getItems().add("Ouvrir");

                menuAction.getItems().add("Supprimer");

            menuAction.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    System.out.println(menuAction.getItems().get((Integer) newValue)+" id sujet : "+id_sujet);
                    if(menuAction.getItems().get((Integer) newValue).equals("Supprimer")){
                        
                            Alert alertsup = new Alert(Alert.AlertType.CONFIRMATION);
                            alertsup.setTitle("Confirmation de la supression");
                            alertsup.setHeaderText("");
                            alertsup.setContentText("Voulez vous vraiment supprimer le sujet ?");
                            Optional<ButtonType> resultat = alertsup.showAndWait();
                            if(resultat.get()==ButtonType.OK)
                            {
                                try {
                                    supprimer(id_sujet);
                                    RefreshData();
                                } catch (IOException ex) {
                                    Logger.getLogger(MesSujetsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else{
                                menuAction.getItems().add("                ");
                                menuAction.setValue("                ");
                            }
           
                    }
                    else if (menuAction.getItems().get((Integer) newValue).equals("Non Résolu")){
                        try {
                            SetNonResolu(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(MesSujetsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if (menuAction.getItems().get((Integer) newValue).equals("Résolu")){
                        try {
                            SetRésolu(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(MesSujetsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if (menuAction.getItems().get((Integer) newValue).equals("Fermer")){
                        try {
                            SetFermer(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(MesSujetsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if (menuAction.getItems().get((Integer) newValue).equals("Ouvrir")){
                        try {
                            SetOuvert(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(MesSujetsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                    else if (menuAction.getItems().get((Integer) newValue).equals("Signaler")){
                        try {
                            SetSignaler(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(MesSujetsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            });
            this.getChildren().addAll(menuAction);
        }
    } 
    
    public void supprimer(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.delete(ss.getById(id_sujet));  
        
        Notifier("Suppression sujet", "Le sujet a été supprimer avec succés", NotificationType.SUCCESS);
    }
    
    
    public void SetRésolu(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.resolu(id_sujet);   
        
        Notifier("Etat sujet", "L'etat sujet a été modifier avec succés", NotificationType.SUCCESS);
    }
    
    public void SetNonResolu(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.NonResolu(id_sujet); 

        Notifier("Etat sujet", "L'etat sujet a été modifier avec succés", NotificationType.SUCCESS); 
    }
    
    public void SetFermer(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.fermer(id_sujet); 

        Notifier("Etat sujet", "L'etat sujet a été modifier avec succés", NotificationType.SUCCESS); 
    }
    
    public void SetOuvert(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.ouvrir(id_sujet); 
        
        //Notifier("Etat sujet", "L'etat sujet a été modifier avec succés", NotificationType.SUCCESS);        
    }
    
    public void SetSignaler(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        Sujet S = ss.getById(id_sujet);
        
        if(S.getNbsignal() == 9){
            S.setNbsignal(0);
            ss.signaler(S);
            supprimer(id_sujet);
        }else{
            S.setNbsignal(S.getNbsignal()+1);
            ss.signaler(S);    
        }
        
        Notifier("Signaler sujet", "Le sujet a été signaler avec succés", NotificationType.SUCCESS);
    }
    
    public void Notifier(String title , String message , NotificationType type ){
        //Push Notification Succé !!
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(2));       
    }
    
}
