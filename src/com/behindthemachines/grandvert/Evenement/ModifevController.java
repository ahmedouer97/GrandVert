/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Evenement;

import com.behindthemachines.grandvert.entity.Evenement;
import com.behindthemachines.grandvert.services.EvenementService;
import com.behindthemachines.grandvert.utils.StageManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author Bouazza Med
 */
public class ModifevController implements Initializable {

    @FXML
    private Button Modifier;
    @FXML
    private TextField titre;
    @FXML
    private TextField organisation;
    @FXML
    private TextField lieu;
    @FXML
    private TextField adresse;
    @FXML
    private TextArea description;
    @FXML
    private Button image;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private ImageView imagev;
     private String fichier;
     @FXML
    private AnchorPane root;
 StageManager stageManager = StageManager.getStageManager();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EvenementService ps =new EvenementService();
         Evenement p=ps.getById(AffevController_2.id);
        titre.setText(p.getTitre());
        organisation.setText(p.getOrganisation());
        description.setText(p.getDescription());
        lieu.setText(p.getLieu());
        adresse.setText(p.getAdresse());
        File file = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\"+p.getImage());
        Image imag=new Image(file.toURI().toString());
     imagev.setImage(imag);
    }    
    @FXML
    private void handleradd(ActionEvent event) throws IOException {            
             LocalDate localDate = datef.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date f = Date.from(instant);
            LocalDate localDat = dated.getValue();
            Instant instant1 = Instant.from(localDat.atStartOfDay(ZoneId.systemDefault()));
            Date d = Date.from(instant1);
            Date now = new Date();
         EvenementService ps =new EvenementService();
        ps.update(new Evenement(AffevController_2.id,StageManager.getStageManager().getUser().getId(),titre.getText(),organisation.getText(),description.getText(),fichier, lieu.getText(),adresse.getText(),d,f));
ps.etat22();
        stageManager.Load("Evenement/affev_2");        
        
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
           image.setText("Modifier Image");
           String imagepath = selectedFile.toURI().toString();
           Image image=new Image(imagepath);
        imagev.setImage(image);
        File source = new File(selectedFile.getAbsolutePath());
        File dest = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\");
            try {
                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException ex) {
                Logger.getLogger(ModifevController.class.getName()).log(Level.SEVERE, null, ex);
            }
            fichier=selectedFile.getName();
    }
    }
    
}
