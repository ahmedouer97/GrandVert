/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Forum;

import com.behindthemachines.grandvert.entity.Notification;
import com.behindthemachines.grandvert.entity.Reaction_Reponse;
import com.behindthemachines.grandvert.entity.Reaction_Sujet;
import com.behindthemachines.grandvert.entity.Reponse;
import com.behindthemachines.grandvert.entity.Sujet;
import com.behindthemachines.grandvert.entity.User;
import com.behindthemachines.grandvert.services.NotificationService;
import com.behindthemachines.grandvert.services.ReactionReponseService;
import com.behindthemachines.grandvert.services.ReactionSujetService;
import com.behindthemachines.grandvert.services.ReponseService;
import com.behindthemachines.grandvert.services.SujetService;
import com.behindthemachines.grandvert.services.UserService;
import com.behindthemachines.grandvert.utils.FacebookConnector;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ROCH
 */
public class ConsulterSujetFXMLController implements Initializable {

    private int SelectedSujet_id;
    @FXML
    private ImageView Sujetresoluimg;
    @FXML
    private JFXListView<HBoxCellSujet> SujetList;
    @FXML
    private JFXListView<HBoxCellReponse> ReponseList;
    
    private ArrayList<Sujet> DataS;
    private ArrayList<Reponse> DataR;
    @FXML
    private Label LabelSujetFermer;
    private JFXListView<HBoxCellSujet> jfxlistview;
    @FXML
    private AnchorPane ListReponseInterface;
    @FXML
    private JFXButton Ajouterbtn;
    @FXML
    private HTMLEditor HtmlEditor;
    @FXML
    private JFXButton Annulerbtn;
    @FXML
    private JFXButton Modifierbtn;

    private int selectedreponse_id;
    StageManager stageManager = StageManager.getStageManager();
    
    private int ConnectedUser= stageManager.getUser().getId();
    @FXML
    private Label ErrorMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ErrorMessage.setVisible(false);
        Annulerbtn.setVisible(false);
        Modifierbtn.setVisible(false);
        SujetList.getStylesheets().add("Stylesheet/stylesheet.css");
        
        List<HBoxCellSujet> lists = new ArrayList<>();
        List<HBoxCellReponse> listr = new ArrayList<>();

        //List de sujet selectionner par l'utilisateur
        SujetService ss = new SujetService(); //service sujet
        DataS = new ArrayList<>();
        Sujet suj = ss.getById(SelectedSujet_id);
        DataS.add(suj);
        
        if(suj.getResolu().equals("true"))
            Sujetresoluimg.setVisible(true);
        else
            Sujetresoluimg.setVisible(false);
        
        if(suj.getOpen().equals("true"))
            LabelSujetFermer.setVisible(false);
        else{
            LabelSujetFermer.setVisible(true);  
            HtmlEditor.setVisible(false);
            Ajouterbtn.setVisible(false);
        }

        UserService us = new UserService(); //service user

        //ajouter le Sujet à consulter à la list de sujet
        for (Sujet s :  DataS){
            lists.add(new HBoxCellSujet("UrlImageUser" , us.getById(s.getUser().getId()).getPrenom()+" "+us.getById(s.getUser().getId()).getNom() , s.getSujet_original() , s.getDate_original() , s.getId() ));
        }

        ObservableList<HBoxCellSujet> myObservableLists = FXCollections.observableList(lists);
        SujetList.setItems(myObservableLists);
       
        //List reponse de sujet
        ReponseService rs = new ReponseService(); // service reponse
        DataR = new ArrayList<>(rs.getAll(SelectedSujet_id));
        
        //ajouter les réponse à  du sujet  à la list de reponse
        for (Reponse r :  DataR){
            listr.add(new HBoxCellReponse("UrlImageUser" , us.getById(r.getUser().getId()).getPrenom()+" "+us.getById(r.getUser().getId()).getNom() , r.getReponse_original() , r.getDate_roriginal() , r.getId() ));
        }

        ObservableList<HBoxCellReponse> myObservableListr = FXCollections.observableList(listr);
        ReponseList.setItems(myObservableListr);        
        ReponseList.setExpanded(true);
        ReponseList.depthProperty().set(1);
    }  
    
    public ConsulterSujetFXMLController(int id){
        this.SelectedSujet_id = id;
    }

    @FXML
    private void AjouterReponse(ActionEvent event) throws IOException {
        if(HtmlEditor.getHtmlText().length() != 72){
            ReponseService rs = new ReponseService();
            SujetService ss = new SujetService();

            UserService us = new UserService();
            rs.add(new Reponse(ss.getById(SelectedSujet_id),us.getById(ConnectedUser), HtmlEditor.getHtmlText(), "", new Date() ,new Date() , 0 ,0));
            RefreshData();

            String Title = "Reponse Ajouter";
            String Description = "une réponse a été ajouter a votre sujet";

            //envoyer un email au responsable de sujet lorsque un user ajouter une reponse 
            if(ss.getById(SelectedSujet_id).getUser().getId() != ConnectedUser){
                sendPlainTextEmail("keeptooui@gmail.com", "azerty1+123azesprit", ss.getById(SelectedSujet_id).getUser().getEmail() , Title , Description);
                sendNotification(Title, Description, ss.getById(SelectedSujet_id).getUser().getId() );
            }

            //envoyer une notification 
            Notifier("Ajout réponse", "La réponse a été ajouter avec succés", NotificationType.SUCCESS);
        }
        else{
            ErrorMessage.setVisible(true);
            HtmlEditor.setStyle(
                "-fx-font: 12 cambria;"
                + "-fx-border-color: #ef5350; "
                + "-fx-border-style: solid;"
                + "-fx-border-width: 2;"
            );
        }
    }

    @FXML
    private void AnnulerAction(ActionEvent event) throws IOException {
        RefreshData();
    }

    @FXML
    private void ModifierReponse(ActionEvent event) throws IOException {
        if(HtmlEditor.getHtmlText().length() != 72){
            ReponseService rs = new ReponseService();

            Reponse r = rs.getById(selectedreponse_id);
            r.setReponse_original(HtmlEditor.getHtmlText());
            rs.update(r);

            Annulerbtn.setVisible(false);
            Modifierbtn.setVisible(false);
            Ajouterbtn.setVisible(true);
            Notifier("Modification sujet", "Le sujet a été modifier avec succés", NotificationType.SUCCESS);
            RefreshData();
        }
        else{
            ErrorMessage.setVisible(true);
            HtmlEditor.setStyle(
                "-fx-font: 12 cambria;"
                + "-fx-border-color: #ef5350; "
                + "-fx-border-style: solid;"
                + "-fx-border-width: 2;"
            );
        }
    }
    
    public class HBoxCellSujet extends HBox {
        
        HBoxCellSujet(String UrlImageuser, String labelUserName ,  String TextSujet , Date dateSujet , int id_sujet) {
            super();
            ReactionSujetService rss = new ReactionSujetService();
            
            this.getChildren().addAll(new VBoxRow1(UrlImageuser) ,new VBoxRow2Sujet(labelUserName, TextSujet , new HBoxCell2Sujet(rss.getAll(id_sujet, "like").size()+"", rss.getAll(id_sujet, "dislike").size()+"", dateSujet , id_sujet)) , new VBoxRow4Sujet(id_sujet));
        }
    }
    
    public class HBoxCellReponse extends HBox {
        
        HBoxCellReponse(String UrlImageuser, String labelUserName ,  String TextReponse , Date dateReponse , int id_reponse) {
            super();
            ReactionReponseService rrs = new ReactionReponseService();
            Region Reponsecontent = new Region();
            this.setHgrow(Reponsecontent, Priority.ALWAYS);
            this.getChildren().addAll(new VBoxRow1(UrlImageuser) ,new VBoxRow2Reponse(labelUserName, TextReponse , new HBoxCell2Reponse(rrs.getAll(id_reponse, "like").size()+"", rrs.getAll(id_reponse, "dislike").size()+"", dateReponse , id_reponse)) , Reponsecontent,new VBoxRow4Reponse(id_reponse));
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
    public class VBoxRow2Sujet extends VBox {
        Label LabelUser = new Label();
        Label LabelSujet = new Label();
        WebView webView = new WebView();
        WebEngine webEngineSujet = webView.getEngine();

        VBoxRow2Sujet(String labelUserName ,  String labelTextSujet , HBox Hboxcell) {
            super();

            //Label Nom user
            LabelUser.setText(labelUserName);
            LabelUser.setMaxWidth(Double.MAX_VALUE);
            LabelUser.setFont(Font.font("Cambria", 15));
            VBox.setVgrow(LabelUser, Priority.ALWAYS);
            
            //Sujet content
            webView.getEngine().loadContent(labelTextSujet, "text/html");
            webView.setPrefSize(900, 130);
            this.setPrefWidth(880);
            this.getChildren().addAll(LabelUser , webView , Hboxcell );
        }
    }
    
    //VBox for User & Sujet column 2 
    public class VBoxRow2Reponse extends VBox {
        Label LabelUser = new Label();
        Label LabelSujet = new Label();
        WebView webView = new WebView();
        WebEngine webEngineSujet = webView.getEngine();

        VBoxRow2Reponse(String labelUserName ,  String labelTextSujet , HBox Hboxcell) {
            super();

            //Label Nom user
            LabelUser.setText(labelUserName);
            LabelUser.setMaxWidth(Double.MAX_VALUE);
            LabelUser.setFont(Font.font("Cambria", 15));
            VBox.setVgrow(LabelUser, Priority.ALWAYS);
            
            //Sujet content
            webView.getEngine().loadContent(labelTextSujet, "text/html");
            webView.setMaxSize(1400, 130);
            this.getChildren().addAll(LabelUser , webView , Hboxcell );
        }
    }    
    
    //VBox for User & Sujet column 2 
    public class HBoxCell2Sujet extends HBox {
        Label LabelLike = new Label("", new ImageView());
        Label LabelDesLike = new Label("", new ImageView());
        Label LabelDate = new Label("" , new ImageView());
        ImageView imgShare = new ImageView("/Icon/fb.png");

        HBoxCell2Sujet(String labelNbLike ,  String labelNbDesLike , Date LabelDates,int id_sujet) {
            super();

            //Label Nb like
            LabelLike.setText(labelNbLike);
            LabelLike.setGraphic(new ImageView("Icon/Like.png"));
            LabelLike.setMaxWidth(Double.MAX_VALUE);
            LabelLike.setFont(Font.font("Cambria", 15));
            LabelLike.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setLikeSujet(id_sujet);
                    try {
                        RefreshData();
                    } catch (IOException ex) {
                        Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            HBox.setHgrow(LabelLike, Priority.ALWAYS);
            
            //Label Nb Deslike
            LabelDesLike.setText(labelNbDesLike);
            LabelDesLike.setGraphic(new ImageView("Icon/Deslike2.png"));
            LabelDesLike.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setDesLikeSujet(id_sujet);
                    try {
                        RefreshData();
                    } catch (IOException ex) {
                        Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            LabelDate.setText("                                                                                                                                                                    Publier le : "+LabelDates+"    ");
            //LabelDate.setGraphic(new ImageView("ICON/Clock.png"));
            imgShare.setFitWidth(90);
            imgShare.setFitHeight(25);
            imgShare.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Partager Sujet ==> id_sujet :"+id_sujet);
                    ClickConnecter();
                }
            });
            this.getChildren().addAll(LabelLike , LabelDesLike , LabelDate , imgShare );
        }
    }  
    
    
    //VBox for User & Sujet column 2 
    public class HBoxCell2Reponse extends HBox {
        Label LabelLike = new Label("", new ImageView());
        Label LabelDesLike = new Label("", new ImageView());
        Label LabelDate = new Label("" , new ImageView());

        HBoxCell2Reponse(String labelNbLike ,  String labelNbDesLike , Date LabelDates,int id_reponse) {
            super();

            //Label Nb like
            LabelLike.setText(labelNbLike);
            LabelLike.setGraphic(new ImageView("Icon/Like.png"));
            LabelLike.setMaxWidth(Double.MAX_VALUE);
            LabelLike.setFont(Font.font("Cambria", 15));
            LabelLike.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setLikeReponse(id_reponse);
                    try {
                        RefreshData();
                    } catch (IOException ex) {
                        Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            HBox.setHgrow(LabelLike, Priority.ALWAYS);
            
            //Label Nb Deslike
            LabelDesLike.setText(labelNbDesLike);
            LabelDesLike.setGraphic(new ImageView("Icon/Deslike2.png"));
            LabelDesLike.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setDesLikeReponse(id_reponse);
                    try {
                        RefreshData();
                    } catch (IOException ex) {
                        Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            LabelDate.setText("                                                                                                                                                                         Commenter le : "+LabelDates);
            //LabelDate.setGraphic(new ImageView("ICON/Clock.png"));

            this.getChildren().addAll(LabelLike , LabelDesLike , LabelDate );
        }
    }     

    //VBox for list action sujet
    public class VBoxRow4Sujet extends VBox {
        ChoiceBox<String> menuAction = new ChoiceBox<String>();
        
        VBoxRow4Sujet(int id_sujet) {
            super();

            SujetService ss = new SujetService();
            Sujet s = ss.getById(id_sujet);
            UserService us = new UserService();
            
            ArrayList<User> listModerateur = new ArrayList<User>(us.getAllModerateur());
            
            if((s.getUser().getId() == ConnectedUser) || (listModerateur.contains(us.getById(ConnectedUser))))
            {
                if(s.getResolu().equals("true"))
                    menuAction.getItems().add("Non Résolu");
                else
                    menuAction.getItems().add("Résolu");


                if(s.getOpen().equals("true"))
                    menuAction.getItems().add("Fermer");
                else
                    menuAction.getItems().add("Ouvrir");

                menuAction.getItems().add("Supprimer");
            }
            else
                menuAction.getItems().add("Signaler");

            menuAction.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    System.out.println(menuAction.getItems().get((Integer) newValue)+" id sujet : "+id_sujet);
                    if(menuAction.getItems().get((Integer) newValue).equals("Supprimer")){
                        
                        try {
                            Alert alertsup = new Alert(Alert.AlertType.CONFIRMATION);
                            alertsup.setTitle("Confirmation de la supression");
                            alertsup.setHeaderText("");
                            alertsup.setContentText("Voulez vous vraiment supprimer le sujet ?");
                            Optional<ButtonType> resultat = alertsup.showAndWait();
                            if(resultat.get()==ButtonType.OK)
                            {
                                supprimersujet(id_sujet);
                                RefreshData();
                            }
                            else
                            {
                                menuAction.getItems().add("                ");
                                menuAction.setValue("                ");
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if (menuAction.getItems().get((Integer) newValue).equals("Non Résolu")){
                        try {
                            SetNonResolu(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if (menuAction.getItems().get((Integer) newValue).equals("Résolu")){
                        try {
                            SetRésolu(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if (menuAction.getItems().get((Integer) newValue).equals("Fermer")){
                        try {
                            SetFermer(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if (menuAction.getItems().get((Integer) newValue).equals("Ouvrir")){
                        try {
                            SetOuvert(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                    else if (menuAction.getItems().get((Integer) newValue).equals("Signaler")){
                        try {
                            SetSignalersujet(id_sujet);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            });
            this.getChildren().addAll(menuAction);
        }
    }
    
    //VBox for list action pour reponse
    public class VBoxRow4Reponse extends VBox {
        ChoiceBox<String> menuAction = new ChoiceBox<String>();
        
        VBoxRow4Reponse(int id_reponse) {
            super();

            ReponseService rs = new ReponseService();
            Reponse r = rs.getById(id_reponse);
            
            UserService us = new UserService();
            ArrayList<User> listModerateur = new ArrayList<User>(us.getAllModerateur());

            if(r.getUser().getId() == ConnectedUser){
                menuAction.getItems().add("Modifier");
                menuAction.getItems().add("Supprimer");
            }
            else if (listModerateur.contains(us.getById(ConnectedUser)))
                menuAction.getItems().add("Supprimer");
            else
                menuAction.getItems().add("Signaler");

            menuAction.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    System.out.println(menuAction.getItems().get((Integer) newValue)+" id reponse : "+id_reponse);
                    if(menuAction.getItems().get((Integer) newValue).equals("Supprimer")){
                        
                        try {
                            Alert alertsup = new Alert(Alert.AlertType.CONFIRMATION);
                            alertsup.setTitle("Confirmation de la supression");
                            alertsup.setHeaderText("");
                            alertsup.setContentText("Voulez vous vraiment supprimer le sujet ?");
                            Optional<ButtonType> resultat = alertsup.showAndWait();
                            if(resultat.get()==ButtonType.OK)
                            {
                                supprimerReponse(id_reponse);
                                RefreshData();
                            }
                            else
                            {
                                menuAction.getItems().add("                ");
                                menuAction.setValue("                ");
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if (menuAction.getItems().get((Integer) newValue).equals("Modifier")){
                        modifierReponse(id_reponse);
                    } 
                    else if (menuAction.getItems().get((Integer) newValue).equals("Signaler")){
                        try {
                            SetSignalerReponse(id_reponse);
                            RefreshData();
                        } catch (IOException ex) {
                            Logger.getLogger(ConsulterSujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            });
            
            this.getChildren().addAll(menuAction);
        }
    } 
    
    public void SetRésolu(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.resolu(id_sujet);    
        
         String EmailTitle = "Etat Sujet";
        String SujetEmail = "L'etat de votre sujet a été modifier par le moderateur  ";
        
        //envoyer un email au user lorsque le moderateur à changer l'etat de leurs sujet 
        if(ss.getById(id_sujet).getUser().getId() != ConnectedUser)
            sendPlainTextEmail("keeptooui@gmail.com", "azerty1+123azesprit", ss.getById(id_sujet).getUser().getEmail() , EmailTitle , SujetEmail);
        
        
        Notifier("Etat sujet", "L'etat sujet a été modifier avec succés", NotificationType.SUCCESS); 
        
    }
    
    public void SetNonResolu(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.NonResolu(id_sujet); 

        String EmailTitle = "Etat Sujet";
        String SujetEmail = "L'etat de votre sujet a été modifier par le moderateur  ";
        
        //envoyer un email au user lorsque le moderateur à changer l'etat de leurs sujet 
        if(ss.getById(id_sujet).getUser().getId() != ConnectedUser)
            sendPlainTextEmail("keeptooui@gmail.com", "azerty1+123azesprit", ss.getById(id_sujet).getUser().getEmail() , EmailTitle , SujetEmail);
         
        Notifier("Etat sujet", "L'etat sujet a été modifier avec succés", NotificationType.SUCCESS); 
        
    }
    
    public void SetFermer(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.fermer(id_sujet); 
        
        String EmailTitle = "Etat Sujet";
        String SujetEmail = "L'etat de votre sujet a été modifier par le moderateur  ";
        
        //envoyer un email au user lorsque le moderateur à changer l'etat de leurs sujet 
        if(ss.getById(id_sujet).getUser().getId() != ConnectedUser)
            sendPlainTextEmail("keeptooui@gmail.com", "azerty1+123azesprit", ss.getById(id_sujet).getUser().getEmail() , EmailTitle , SujetEmail);
         
        Notifier("Etat sujet", "L'etat sujet a été modifier avec succés", NotificationType.SUCCESS);        
        
    }
    
    public void SetOuvert(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.ouvrir(id_sujet);  
        
        String EmailTitle = "Etat Sujet";
        String SujetEmail = "L'etat de votre sujet a été modifier par le moderateur  ";
        
        //envoyer un email au user lorsque le moderateur à changer l'etat de leurs sujet 
        if(ss.getById(id_sujet).getUser().getId() != ConnectedUser)
            sendPlainTextEmail("keeptooui@gmail.com", "azerty1+123azesprit", ss.getById(id_sujet).getUser().getEmail() , EmailTitle , SujetEmail);
         
        Notifier("Etat sujet", "L'etat sujet a été modifier avec succés", NotificationType.SUCCESS); 
        
    }
    
    public void SetSignalersujet(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        Sujet S = ss.getById(id_sujet);
        
        if(S.getNbsignal() == 9){
            S.setNbsignal(0);
            ss.signaler(S);
            supprimersujet(id_sujet);
        }else{
            S.setNbsignal(S.getNbsignal()+1);
            ss.signaler(S);    
        } 
        
        Notifier("Signaler sujet", "Le sujet a été signaler avec succés", NotificationType.SUCCESS);
        
    }

    public void supprimersujet(int id_sujet) throws IOException{
        SujetService ss = new SujetService();
        ss.delete(ss.getById(id_sujet));
        
        Notifier("Suppression sujet", "Le sujet a été supprimer avec succés", NotificationType.SUCCESS);
        
    }
    
    private void RefreshData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterSujetFXML.fxml"));
        //passer le plante selectionner par user au controller
        ConsulterSujetFXMLController sujetcontroller = new ConsulterSujetFXMLController(SelectedSujet_id);
                    
        // Set it in the FXMLLoader
        loader.setController(sujetcontroller);

        ListReponseInterface.getChildren().setAll((AnchorPane) loader.load());       
    }
    
    private void RefreshData() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterSujetFXML.fxml"));
        //passer le plante selectionner par user au controller
        ConsulterSujetFXMLController sujetcontroller = new ConsulterSujetFXMLController(SelectedSujet_id);
                    
        // Set it in the FXMLLoader
        loader.setController(sujetcontroller);

        ListReponseInterface.getChildren().setAll((AnchorPane) loader.load());       
    }
    
    public void supprimerReponse(int id_reponse) throws IOException{
        ReponseService sr = new ReponseService();
        sr.delete(sr.getById(id_reponse));  
        RefreshData();
        Notifier("Suppression réponse", "La réponse a été supprimer avec succés", NotificationType.SUCCESS);
        
    }
    
    public void modifierReponse(int id_reponse){
        ReponseService rs = new ReponseService();
        HtmlEditor.setHtmlText(rs.getById(id_reponse).getReponse_original());
        selectedreponse_id = id_reponse;
        Annulerbtn.setVisible(true);
        Modifierbtn.setVisible(true);
        Ajouterbtn.setVisible(false);
    }
    
    public void SetSignalerReponse(int id_reponse) throws IOException{
        ReponseService rs = new ReponseService();
        Reponse r = rs.getById(id_reponse);
        
        if(r.getNbsignal() == 9){
            r.setNbsignal(0);
            rs.signaler(r);
            supprimerReponse(id_reponse);
        }else{
            r.setNbsignal(r.getNbsignal()+1);
            rs.signaler(r);    
        }    
    }    
    
    public void setLikeSujet(int id_sujet){ 
        ReactionSujetService rss = new ReactionSujetService();
        SujetService ss = new SujetService();
        List<Reaction_Sujet> listReaction_Sujet = rss.getUserReaction(id_sujet, ConnectedUser);
        if(listReaction_Sujet.size()!=0){
            if(listReaction_Sujet.get(0).getReaction().equals("like"))
                rss.delete(new Reaction_Sujet(ss.getById(id_sujet),ConnectedUser,"like"));
            else{
                rss.delete(new Reaction_Sujet(ss.getById(id_sujet),ConnectedUser,"dislike"));
                rss.add(new Reaction_Sujet(ss.getById(id_sujet),ConnectedUser,"like"));
            }
        }    
        else
            rss.add(new Reaction_Sujet(ss.getById(id_sujet),ConnectedUser,"like"));
    }
    
    public void setDesLikeSujet(int id_sujet){
        ReactionSujetService rss = new ReactionSujetService();
        SujetService ss = new SujetService();
        List<Reaction_Sujet> listReaction_Sujet = rss.getUserReaction(id_sujet, ConnectedUser);
        if(listReaction_Sujet.size()!=0){
            if(listReaction_Sujet.get(0).getReaction().equals("dislike"))
                rss.delete(new Reaction_Sujet(ss.getById(id_sujet),ConnectedUser,"dislike"));
            else{
                rss.delete(new Reaction_Sujet(ss.getById(id_sujet),ConnectedUser,"like"));
                rss.add(new Reaction_Sujet(ss.getById(id_sujet),ConnectedUser,"dislike"));
            }
        }    
        else
            rss.add(new Reaction_Sujet(ss.getById(id_sujet),ConnectedUser,"dislike"));
    } 
    
    public void setLikeReponse(int id_reponse){
        ReactionReponseService rss = new ReactionReponseService();
        ReponseService ss = new ReponseService();
        List<Reaction_Reponse> listReaction_Reponse = rss.getUserReaction(id_reponse, ConnectedUser);
        if(listReaction_Reponse.size()!=0){
            if(listReaction_Reponse.get(0).getReaction().equals("like"))
                rss.delete(new Reaction_Reponse(ss.getById(id_reponse),ConnectedUser,"like"));
            else{
                rss.delete(new Reaction_Reponse(ss.getById(id_reponse),ConnectedUser,"dislike"));
                rss.add(new Reaction_Reponse(ss.getById(id_reponse),ConnectedUser,"like"));
            }
        }    
        else
            rss.add(new Reaction_Reponse(ss.getById(id_reponse),ConnectedUser,"like"));
    }
    
    public void setDesLikeReponse(int id_reponse){
        ReactionReponseService rss = new ReactionReponseService();
        ReponseService ss = new ReponseService();
        List<Reaction_Reponse> listReaction_Reponse = rss.getUserReaction(id_reponse, ConnectedUser);
        if(listReaction_Reponse.size()!=0){
            if(listReaction_Reponse.get(0).getReaction().equals("dislike"))
                rss.delete(new Reaction_Reponse(ss.getById(id_reponse),ConnectedUser,"dislike"));
            else{
                rss.delete(new Reaction_Reponse(ss.getById(id_reponse),ConnectedUser,"like"));
                rss.add(new Reaction_Reponse(ss.getById(id_reponse),ConnectedUser,"dislike"));
            }
        }    
        else
            rss.add(new Reaction_Reponse(ss.getById(id_reponse),ConnectedUser,"dislike"));
    }  
    
    public void Notifier(String title , String message , NotificationType type ){
        //Push Notification Succé !!
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(2));       
    }  
    
    public void sendPlainTextEmail(final String email, final String password,String toAddress , String Title , String Subjet) {
        try{
            String host ="smtp.gmail.com" ;
            String user = email;
            String pass = password;
            String to = toAddress;
            String from = email;
            String subject = Title;
            String messageText = Subjet;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    }    
    
    public void sendNotification(String title , String description , int id_user){
        NotificationService ns = new NotificationService();
        Notification n = new Notification(id_user, title, description, new Date(), "route", 0);
        ns.add(n);
    } 
    
    public void ClickConnecter() {

                
        String domain = "http://localhost/GrandVert/web/app_dev.php/";
        String appId = "298248927712899";
        String SecretApp = "824c8a5ab13296f163eb93a9e2bb4734";

       
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope="
        +"user_birthday, user_hometown, user_location, user_likes, user_events, user_photos, user_videos, user_friends, user_status,"
                +"user_tagged_places, user_posts, user_gender, user_link, user_age_range, email, read_insights, read_audience_network_insights,"
                +" publish_video, manage_pages, pages_manage_cta, pages_manage_instant_articles, pages_show_list, publish_pages, read_page_mailboxes,"
                +" ads_management, ads_read, business_management, pages_messaging, pages_messaging_phone_number, pages_messaging_subscriptions,"
                +" instagram_basic, instagram_manage_comments, instagram_manage_insights, publish_to_groups, groups_access_member_info, leads_retrieval, public_profile, basic_info";
    

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
       
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        while(true){
       
            if(!driver.getCurrentUrl().contains("facebook.com")){
            String url = driver.getCurrentUrl();
            accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                
            //System.out.println("Expired Access Token !! : "+accessToken);
            String AccesToken2 ="EAAEPQX1kFoMBAECLSKm38FYxEdfZCqNxQf7uON1Q0Mhx9czicnhhe59TWcD2IxJ0BIZBiyZBIMu1OZCOnoFur5iIPkCZCgeXcINHlJvKpbZBl4fBbB7EIf89pUGfQBaxD8wjHiaFoEWgYNvg72n6CgrLKdbVLHIhZCRWKZAHz4h3Y4OSjMxhBeVZBVUvTjiZBa7lBqMaBfZCAX4qQZDZD";

            driver.quit();

            FacebookConnector f = new FacebookConnector();
            f.makeTestPost();
           
            }
       
        }
           
    }    
    
}
