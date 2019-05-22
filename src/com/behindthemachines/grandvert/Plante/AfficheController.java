/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Plante;

import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.services.PlanteService;
import com.behindthemachines.grandvert.utils.Alertbox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AfficheController implements Initializable {

  
    @FXML
    private ScrollPane imageslider;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label haut;
    @FXML
    private Label desc;
    @FXML
    private Label desct;
    @FXML
    private Label season;
    @FXML
    private Label cat;
    @FXML
    private Label fert;
    @FXML
    private Label tit;
    @FXML
    private ImageView photo;
    @FXML
    private Button panier;
    private int id;
   public static List<Plante>plantes=new ArrayList<Plante>();
    @FXML
    private Button pan;
    @FXML
    private AnchorPane root;
    @FXML
    private ChoiceBox<String> cate;
    @FXML
    private TextField rech;
    @FXML
    private Label lab;
    private int s;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
       panier.setVisible(false);
       cate.getItems().addAll("tous","Fruit","Legume","Fleur","Herbe");
       cate.setValue("tous");
       cate.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               loadbycat(cate.getItems().get((Integer) newValue));
               rech.setText("");
               lab.setText("");
            }
        });
       rech.textProperty().addListener((observable, oldValue, newValue) -> {
    loadbynom(newValue);
});
        
    }
private void load(){
      TilePane tilepane=new TilePane(Orientation.VERTICAL);
      PlanteService ps =new PlanteService(); 
        tilepane.setHgap(10);
      tilepane.setVgap(10);
      tilepane.setPadding(new Insets(10, 10, 10, 10));
       List<Plante> p= ps.getAll().stream().filter(o->o.getProposition()==0).collect(Collectors.toList());
       s=p.size();
        for(int i=0;i<p.size();i++){
         File file = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\"+p.get(i).getPhoto());   
            Image img=new Image(file.toURI().toString());
            ImageView imageview=new ImageView(img);
            imageview.setId(Integer.toString(p.get(i).getId()));
            imageview.setFitHeight(300);
            imageview.setFitWidth(300);
            
            imageview.setPreserveRatio(true);
            tilepane.getChildren().addAll(imageview);
            addeventtoimageview(imageview);
        
        }
        imageslider.setContent(tilepane);
}
private void loadbycat(String Cat){
    if(Cat=="tous")load();else{
      TilePane tilepane=new TilePane(Orientation.VERTICAL);
      PlanteService ps =new PlanteService(); 
      tilepane.setHgap(1);
      tilepane.setVgap(1);
      tilepane.setPadding(new Insets(10, 10, 10, 10));
       List<Plante> p= ps.getByCat(Cat).stream().filter(o->o.getProposition()==0).collect(Collectors.toList());
        for(int i=0;i<p.size();i++){
         File file = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\"+p.get(i).getPhoto());   
            Image img=new Image(file.toURI().toString());
            ImageView imageview=new ImageView(img);
            imageview.setId(Integer.toString(p.get(i).getId()));
            imageview.setFitHeight(300);
            imageview.setFitWidth(300);
            imageview.setPreserveRatio(true);
            tilepane.getChildren().addAll(imageview);
            addeventtoimageview(imageview);
        
        }
        imageslider.setContent(tilepane);}
}
private void loadbynom(String nom){
     TilePane tilepane=new TilePane(Orientation.VERTICAL);
        tilepane.setHgap(10);
      tilepane.setVgap(10);
      tilepane.setPadding(new Insets(10, 10, 10, 10));
      PlanteService ps =new PlanteService(); 
       List<Plante> p= ps.getBynom(nom).stream().filter(o->o.getProposition()==0).collect(Collectors.toList());
    if(nom.isEmpty()){lab.setText("");load();}else{
       lab.setText("il y a "+p.size()+" plante trouvé");
        for(int i=0;i<p.size();i++){
         File file = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\"+p.get(i).getPhoto());   
            Image img=new Image(file.toURI().toString());
            ImageView imageview=new ImageView(img);
            imageview.setId(Integer.toString(p.get(i).getId()));
            imageview.setFitHeight(300);
            imageview.setFitWidth(300);
            imageview.setPreserveRatio(true);
            tilepane.getChildren().addAll(imageview);
            addeventtoimageview(imageview);
        
        }
        imageslider.setContent(tilepane);}
}
    public void addeventtoimageview(ImageView img){
    img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            ImageView image=(ImageView)event.getSource();
               id=Integer.parseInt(image.getId());
               PlanteService ps =new PlanteService();
               panier.setVisible(true);
             Plante p=ps.getById(id);
             if(p.getStock()==0){panier.setDisable(true);panier.setText("hors stock");}else {panier.setDisable(false);panier.setText("ajouter au panier");}
            nom.setText("nom: "+p.getNom());
            prix.setText("prix: "+p.getPrix());
            haut.setText("Hauteur: "+p.getHauteur());
            desc.setText(p.getDescription());
            desct.setText("Description:");
            season.setText("season: "+p.getSeason());
            cat.setText("Catégorie: "+p.getCategorie());
            fert.setText("Fertiliseur: "+p.getFertiliseur());
            tit.setText("vous avez choisie cette plante: ");
            File file = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\"+p.getPhoto());   
            Image img=new Image(file.toURI().toString());
         photo.setImage(img);
            event.consume();
        }
    });
    
    }

    @FXML
    private void panier(ActionEvent event) {
        boolean test=true;
          PlanteService ps =new PlanteService();
          for(int i=0;i<plantes.size();i++){
          if(id==plantes.get(i).getId())
              test=false;}
        if(test)
        { plantes.add(ps.getById(id));
      Alertbox.display("Succés","plante ajouter avec succés");}else  Alertbox.display("erreur","cette plante existe dans le panier");;
    }

    @FXML
    private void pan(ActionEvent event) {
         try {
            System.out.println(id);
             FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/com/workshop/Achat/panier.fxml"));
            Parent root1=(Parent)fxmlloader.load();
             Stage stage= new Stage();
             stage.setScene(new Scene(root1));
             stage.show();
             
             
        } catch (IOException ex) {
            Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
