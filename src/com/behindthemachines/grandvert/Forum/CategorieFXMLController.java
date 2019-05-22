/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Forum;

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
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author ROCH
 */
public class CategorieFXMLController implements Initializable {

    @FXML
    private ListView<HBoxCell> ListCategorie;
    @FXML
    private AnchorPane InterfaceCategorie;
    private ArrayList<String> Data;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<HBoxCell> list = new ArrayList<>();
        //Categorie
        Data = new ArrayList<>();
        Data.add("Fruit");
        Data.add("Herbe");
        Data.add("Fleur");
        Data.add("Légume");
        
        //ajouter les categorie au list
        list.add(new HBoxCell(Data.get(0)));
        list.add(new HBoxCell(Data.get(1)));
        list.add(new HBoxCell(Data.get(2)));
        list.add(new HBoxCell(Data.get(3)));

        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        ListCategorie.setItems(myObservableList);
        
        //lorsque user click sur une categorie
        ListCategorie.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBoxCell>() {
            @Override
            public void changed(ObservableValue<? extends HBoxCell> observable, HBoxCell oldValue, HBoxCell newValue) {
                try {
                    //System.out.println(ListCategorie.getSelectionModel().getSelectedIndex());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanteCategorieFXML.fxml"));
                    //PlanteCategorieFXMLController plantcontroller = loader.getController();
                    //passer le categorie selectionner par user au controller
                    PlanteCategorieFXMLController controller = new PlanteCategorieFXMLController(Data.get(ListCategorie.getSelectionModel().getSelectedIndex()));
                    // Set it in the FXMLLoader
                    loader.setController(controller);
                    

                    InterfaceCategorie.getChildren().setAll((AnchorPane) loader.load());

                } catch (IOException ex) {
                    Logger.getLogger(CategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        });

       
    }  

    
    public class HBoxCell extends HBox {
          Label label = new Label();
          ImageView img = new ImageView();

          HBoxCell(String labelText) {
               super();

               label.setText(labelText);
               label.setMaxWidth(Double.MAX_VALUE);
               label.setFont(Font.font("Cambria", 15));
               HBox.setHgrow(label, Priority.ALWAYS);

               img.setImage(new Image("/Icon/Arrow_20px.png"));

               this.getChildren().addAll(label, img);
          }
    }
        
    
}
