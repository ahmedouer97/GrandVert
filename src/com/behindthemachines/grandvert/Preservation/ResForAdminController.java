/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Preservation;

import com.behindthemachines.grandvert.entity.Reservation;
import com.behindthemachines.grandvert.services.ReservationService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ghaith
 */
public class ResForAdminController implements Initializable {

    @FXML
    private TableView<Reservation> tableReservation;
    @FXML
    private TableColumn<Reservation, Date> dateDebut;
    @FXML
    private TableColumn<Reservation, Date> dateFin;
    @FXML
    private TableColumn<Reservation, Integer> nbPlaces;
    @FXML
    private DatePicker rechDebut;
    @FXML
    private DatePicker rechFin;
    @FXML
    private Button rechercher;
    @FXML
    private Label espAss;
    @FXML
    private Label totalPlaces;
    @FXML
    private Label nbRes;
    @FXML
    private Label capAct;
    @FXML
    private Label lieuAss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
       rechercher.setOnAction((event) -> {
       ReservationService rs = new ReservationService();
        ArrayList<Reservation> esp;
          try {
              Date date1 = java.sql.Date.valueOf(rechDebut.getValue());
              Date date2 = java.sql.Date.valueOf(rechFin.getValue());
              esp = new ArrayList<Reservation>(rs.getReservationForAdmin(date1,date2));
              
           ObservableList obs = FXCollections.observableArrayList(esp);
        tableReservation.setItems(obs);
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        nbPlaces.setCellValueFactory(new PropertyValueFactory<>("NbPlaces"));
        
         try {
                      nbRes.setText(String.valueOf(rs.nbReservAdmin(date1, date2)));
                  } catch (SQLException ex) {
                      Logger.getLogger(ResForAdminController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                      totalPlaces.setText(String.valueOf(rs.sumReservAdmin(date1, date2)));
                  } catch (SQLException ex) {
                      Logger.getLogger(ResForAdminController.class.getName()).log(Level.SEVERE, null, ex);
                  }
        
           tableReservation.setOnMouseClicked(e->{
       String espNom=  tableReservation.getSelectionModel().getSelectedItem().getEspace().getNom();
       String espLieu=  tableReservation.getSelectionModel().getSelectedItem().getEspace().getLieu();
       int espCapacity=  tableReservation.getSelectionModel().getSelectedItem().getEspace().getCapacity();
      String espCapacityStr = String.valueOf(espCapacity);
     espAss.setText(espNom);
     lieuAss.setText(espLieu);
     capAct.setText(espCapacityStr);
                 

             

    }  );
          
          } catch (SQLException ex) {
              Logger.getLogger(ResForAdminController.class.getName()).log(Level.SEVERE, null, ex);
          }
       
        
       // rechDebut.setValue(LocalDate.now());
       // rechFin.setValue(LocalDate.now());
        
         });
        
      //   java.sql.Date dateDebut = java.sql.Date.valueOf(rechDebut.getValue());
        //  java.sql.Date dateFin = java.sql.Date.valueOf(rechFin.getValue());
       
    }    
    
}
