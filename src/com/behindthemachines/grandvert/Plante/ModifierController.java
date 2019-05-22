/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Plante;

import static com.behindthemachines.grandvert.Plante.AddController.isNumeric;
import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.services.PlanteService;
import com.behindthemachines.grandvert.utils.Alertbox;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ModifierController implements Initializable {
@FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField stock;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField hauteur;
    @FXML
    private JFXTextField fertiliseur;
    @FXML
    private ChoiceBox<String> cat;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXButton image;
    @FXML
    private ImageView photo;
    private String ph;
    private String pi;
    @FXML
    private AnchorPane root;
    @FXML
    private ChoiceBox<String> seas;
    @FXML
    private JFXButton add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cat.getItems().addAll("Fruit","Legume","Fleur","Herbe");
      
       seas.getItems().addAll("Hiver","Printemps","été","Automne");
       
           PlanteService ps =new PlanteService();
         Plante p=ps.getById(ConsulterController.id);
         pi=p.getNom();
        nom.setText(p.getNom());
        stock.setText(Integer.toString(p.getStock()));
    seas.setValue(p.getSeason());
        fertiliseur.setText(p.getFertiliseur());
        hauteur.setText(Integer.toString(p.getHauteur()));
        prix.setText(Float.toString(p.getPrix()));
       cat.setValue(p.getCategorie());
        ph=p.getPhoto();
        File file = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\"+p.getPhoto());
        desc.setText(p.getDescription());
        Image image=new Image(file.toURI().toString());
     photo.setImage(image);
    }    
    @FXML
    private void handlermod(ActionEvent event) throws IOException {
         PlanteService ps =new PlanteService();
       
            
    Boolean dis=false;
          ArrayList<Plante>p=(ArrayList) ps.getAll();
         
         for(int i=0;i<p.size();i++){
         if(p.get(i).getNom().equals(nom.getText()))dis=true;
             }
       if(dis&&!pi.equals(nom.getText()))Alertbox.display("Erreur", "le nom de la plante "+nom.getText()+" existe");
         else if(nom.getText().isEmpty())Alertbox.display("Erreur", "veullez saisir le nom de la plante");
         else if(ph=="")Alertbox.display("Erreur", "veuillez saisir une photo");
       else if(isNumeric(stock.getText())&&isNumeric(prix.getText())&&isNumeric(hauteur.getText()))
        {   if(Integer.parseInt(stock.getText())<0)Alertbox.display("Erreur", "le stock doit étre positive");
        else if(Float.parseFloat(prix.getText())<0)Alertbox.display("Erreur", "le prix doit étre positive");
        else if(Integer.parseInt(hauteur.getText())<0)Alertbox.display("Erreur", "le hauteur doit étre positive");
        else{
        Alertbox.display("Succes", "votre plante est modifier avec succés");
          ps.update(new Plante(ConsulterController.id,ph,nom.getText(),desc.getText(), Integer.parseInt(stock.getText()), Float.parseFloat(prix.getText()), Integer.parseInt(hauteur.getText()), fertiliseur.getText(), cat.getValue(), seas.getValue(),0,StageManager.getStageManager().getUser()));
       StageManager stageManager = StageManager.getStageManager();
            stageManager.Load("Plante/Consulter");
                }
      
            }
      else if(isNumeric(stock.getText())==false&&isNumeric(prix.getText())&&isNumeric(hauteur.getText()))
        {   Alertbox.display("Erreur", "le stock doit étre no vide et se compose de nombre");
        
            }
       else if(isNumeric(stock.getText())&&isNumeric(prix.getText())==false&&isNumeric(hauteur.getText()))
        {   Alertbox.display("Erreur", "le prix doit étre no vide et se compose de nombre");
        
            }
       else if(isNumeric(stock.getText())&&isNumeric(prix.getText())&&isNumeric(hauteur.getText())==false)
        {   Alertbox.display("Erreur", "le hauteur doit étre no vide et se compose de nombre");
        
            }
       else if(isNumeric(stock.getText())==false&&isNumeric(prix.getText())==false&&isNumeric(hauteur.getText()))
        {   Alertbox.display("Erreur", "le hauteur et prix doit étre no vide et se compose de nombre");
        
            }
        else if(isNumeric(stock.getText())==false&&isNumeric(prix.getText())&&isNumeric(hauteur.getText())==false)
        {   Alertbox.display("Erreur", "le stock et hauteur doit étre no vide et se compose de nombre");
        
            }
       else if(isNumeric(stock.getText())&&isNumeric(prix.getText())==false&&isNumeric(hauteur.getText())==false)
        {   Alertbox.display("Erreur", "le prix et hauteur doit étre no vide et se compose de nombre");
        
            }
       else if(isNumeric(stock.getText())==false&&isNumeric(prix.getText())==false&&isNumeric(hauteur.getText())==false)
        {   Alertbox.display("Erreur", "le prix , hauteur et stock doit étre no vide et se compose de nombre");
        
            }
    }

    @FXML
    private void upload(ActionEvent event) {
         FileChooser fc=new FileChooser();
    FileChooser.ExtensionFilter fe=  new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif", "*.jpeg");
    fc.getExtensionFilters().add(fe);
        File selectedFile =fc.showOpenDialog(new Stage());
          if (selectedFile != null){
           System.out.println(selectedFile.getName());
           System.out.println(selectedFile.getAbsolutePath());
           image.setText("changer image");
           String imagepath = selectedFile.toURI().toString();
           Image image=new Image(imagepath);
        photo.setImage(image);
        File source = new File(selectedFile.getAbsolutePath());
        File dest = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar");
            try {
                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException ex) {
                Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ph=selectedFile.getName();
    }
    }
}
