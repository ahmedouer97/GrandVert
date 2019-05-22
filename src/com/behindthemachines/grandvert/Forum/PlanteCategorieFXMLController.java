/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Forum;

import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.services.PlanteService;
import com.behindthemachines.grandvert.services.SujetService;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author ROCH
 */
public class PlanteCategorieFXMLController implements Initializable {

    private String SelectedCategorie;
    @FXML
    private JFXListView<HBoxCell> ListPlante;
    @FXML
    private AnchorPane InterfacePlante;
    private ArrayList<Plante> Data;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            
        List<HBoxCell> list = new ArrayList<>();
        //List de plante Par categorie selectionner par l'utilisateur
        PlanteService ps = new PlanteService();
        Data = new ArrayList<>(ps.getByCat(SelectedCategorie));

        SujetService ss = new SujetService();

        //ajouter les plante au list
        for (Plante p :  Data){
            list.add(new HBoxCell(p.getPhoto() , p.getNom() , ss.getAll(p.getId()).size()+""));

        }

        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        ListPlante.setItems(myObservableList);
        ListPlante.setExpanded(true);
        ListPlante.depthProperty().set(1);
        
        //lorsque user click sur une categorie
        ListPlante.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBoxCell>() {
            @Override
            public void changed(ObservableValue<? extends HBoxCell> observable, HBoxCell oldValue, HBoxCell newValue) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ListSujetFXML.fxml"));
                    //passer le plante selectionner par user au controller
                    ListSujetFXMLController sujetcontroller = new ListSujetFXMLController(Data.get(ListPlante.getSelectionModel().getSelectedIndex()).getId());
                    
                    // Set it in the FXMLLoader
                    loader.setController(sujetcontroller);

                    InterfacePlante.getChildren().setAll((AnchorPane) loader.load());

                } catch (IOException ex) {
                    Logger.getLogger(CategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        });
       
    } 
    
    public PlanteCategorieFXMLController(String Categorie) {
        this.SelectedCategorie = Categorie;
    }
    
        public class HBoxCell extends HBox {
            Label LabelNom = new Label();
            ImageView ImgPlante = new ImageView();
            //Label LabelNbSujet = new Label("", this);


        HBoxCell(String UrlImage, String labelTextNom , String labelTextNbSujet ) {
                super();

                //Label Nom de plante
                LabelNom.setText(labelTextNom);
                LabelNom.setMaxWidth(Double.MAX_VALUE);
                LabelNom.setFont(Font.font("Cambria", 15));
                HBox.setHgrow(LabelNom, Priority.ALWAYS);


                //Img de Plante
                ImgPlante.setImage(new Image("Icon/imgplante.jpeg"));
                ImgPlante.setFitHeight(50);
                ImgPlante.setFitWidth(50);
                

                //Label Nb de sujet pour la plante
                Image image = new Image(getClass().getResourceAsStream("/Icon/comment.png"));
                Label LabelNbSujet = new Label(labelTextNbSujet, new ImageView(image));
                LabelNbSujet.setMaxWidth(Double.MAX_VALUE);
                LabelNbSujet.setFont(Font.font("Cambria", 15));
                

               this.getChildren().addAll(ImgPlante ,LabelNom, LabelNbSujet);
        }
    }
    
}
