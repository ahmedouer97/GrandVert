/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Evenement;

import com.behindthemachines.grandvert.entity.Evenement;
import com.behindthemachines.grandvert.services.EvenementService;
import com.behindthemachines.grandvert.utils.StageManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import javafx.scene.control.DatePicker;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.Alert;



/**
 * FXML Controller class
 *
 * @author Bouazza Med
 */
public class AjouterEvController implements Initializable {
    

    @FXML
    private Label lbl;
  @FXML
    private Button ajouter;
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
     private String fichier="a";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleradd(ActionEvent event) {
        if (titre.getText().isEmpty() || lieu.getText().isEmpty()|| fichier=="a" || organisation.getText().isEmpty() || description.getText().isEmpty()|| adresse.getText().isEmpty()  || dated.getValue() == null || datef.getValue() == null) {
            lbl.setText("Tous les champs doivent etre remplis");
        } else {
        LocalDate localDate = datef.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date f = Date.from(instant);
            LocalDate localDat = dated.getValue();
            Instant instant1 = Instant.from(localDat.atStartOfDay(ZoneId.systemDefault()));
            Date d = Date.from(instant1);
            Date now = new Date();
            if ((d.before(now)) || (f.before(now)) || (f.before(d))) {
                lbl.setText("les dates doivent etre superieures à celle d'aujourdhui ," + "\n" + " et la date de fin superieur à celle de début");
            } else {
         EvenementService ev =new EvenementService();
        ev.add(new Evenement(StageManager.getStageManager().getUser().getId(),titre.getText(),organisation.getText(),description.getText(),fichier, lieu.getText(),adresse.getText(),d,f));
    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setTitle("INFORMATION");
                    al.setHeaderText(null);
                    al.setContentText("Evenement ajouté");
                    al.showAndWait();
                    System.out.println("ajouté");
            }
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
           image.setText("Modifier Image");
           String imagepath = selectedFile.toURI().toString();
           Image image=new Image(imagepath);
        imagev.setImage(image);
        File source = new File(selectedFile.getAbsolutePath());
        File dest = new File("C:\\wamp64\\www\\GrandVert\\web\\uploads\\avatar\\");
            try {
                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException ex) {
                Logger.getLogger(AjouterEvController.class.getName()).log(Level.SEVERE, null, ex);
            }
            fichier=selectedFile.getName();
    }
    }
       
    
}
