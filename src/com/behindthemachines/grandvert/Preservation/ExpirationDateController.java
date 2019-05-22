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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Ghaith
 */
public class ExpirationDateController implements Initializable {

    @FXML
    private TableView<Reservation> tableExpire;
    @FXML
    private TableColumn<Reservation, Date> dateDebut;
    @FXML
    private TableColumn<Reservation, Date> dateFin;
    @FXML
    private TableColumn<Reservation, Integer> nbPlaces;
    @FXML
    private Label nbResExp;
    @FXML
    private Label TotalPlace;
    @FXML
    private Label espaceAss;
    @FXML
    private TextField searchBox;
    @FXML
    private Label LieuAss;
    @FXML
    private Label capAct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     ReservationService rs = new ReservationService();
        ArrayList<Reservation> esp = new ArrayList<Reservation>(rs.getExpiredReservation());
        ObservableList obs = FXCollections.observableArrayList(esp);    
        tableExpire.setItems(obs);
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        nbPlaces.setCellValueFactory(new PropertyValueFactory<>("NbPlaces"));
       
        // espaceDePreservation.setCellValueFactory(new PropertyValueFactory<>("espace_de_preservation_id"));
        String nbResExprStr = "";
   
        try {
            nbResExprStr = String.valueOf(rs.nbReservExp());
        } catch (SQLException ex) {
            Logger.getLogger(ExpirationDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String totalPlaceStr = "";
        try {
            totalPlaceStr = String.valueOf(rs.sumReservExp());
        } catch (SQLException ex) {
            Logger.getLogger(ExpirationDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nbResExp.setText(nbResExprStr);
        TotalPlace.setText(totalPlaceStr);
        
        tableExpire.setOnMouseClicked(e->{
       String espNom=  tableExpire.getSelectionModel().getSelectedItem().getEspace().getNom();
       String espLieu=  tableExpire.getSelectionModel().getSelectedItem().getEspace().getLieu();
       int espCapacity=  tableExpire.getSelectionModel().getSelectedItem().getEspace().getCapacity();
      String espCapacityStr = String.valueOf(espCapacity);
     espaceAss.setText(espNom);
     LieuAss.setText(espLieu);
     capAct.setText(espCapacityStr);
             

    }  );  
    
}

    @FXML
    private void searchRecord(KeyEvent event) {
        ReservationService rs = new ReservationService();
        ArrayList<Reservation> esp = new ArrayList<Reservation>(rs.getExpiredReservation());
        ObservableList obs = FXCollections.observableArrayList(esp);    
        tableExpire.setItems(obs);
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        nbPlaces.setCellValueFactory(new PropertyValueFactory<>("NbPlaces"));

        FilteredList<Reservation> filteredData = new FilteredList<>(obs, p -> true);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reservation -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String strDateDebut = dateFormat.format(reservation.getDateDebut());
                String strDateFin = dateFormat.format(reservation.getDateFin());
                String nbPlacesStr = String.valueOf(nbPlaces);
                if (strDateDebut.toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (strDateFin.toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (nbPlacesStr.contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });
        SortedList<Reservation> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableExpire.comparatorProperty());
        tableExpire.setItems(sortedData);

    }

    
}
