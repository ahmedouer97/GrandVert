/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Preservation;

import com.behindthemachines.grandvert.entity.Espace;
import com.behindthemachines.grandvert.services.EspaceService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import javax.management.Notification;
import static jdk.nashorn.tools.Shell.SUCCESS;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Ghaith
 */
public class GestionEspaceController implements Initializable {

    int from = 0, to = 0;
    int itemPerPage = 5;
    int tmpId=0;

    @FXML
    private Button ajouterEspace;
    @FXML
    private TextField nomEspace;
    @FXML
    private TextField capacityEspace;
    @FXML
    private TextField lieuEspace;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private TableColumn<Espace, String> Nom;
    @FXML
    private TableColumn<Espace, Integer> Capacity;
    @FXML
    private TableColumn<Espace, String> Lieu;
    @FXML
    private TableView<Espace> table;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField searchBox;
    @FXML
    private Button exportExcel;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
        EspaceService es = new EspaceService();   
        ArrayList<Espace> esp = new ArrayList<Espace>(es.getAll());
        ObservableList obs = FXCollections.observableArrayList(esp);
        table.setItems(obs);
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Capacity.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        Lieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
        Nom.setCellFactory(TextFieldTableCell.forTableColumn());
        Capacity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        Lieu.setCellFactory(TextFieldTableCell.forTableColumn());
        table.setEditable(true);
        
         table.setOnMouseClicked(e->{
        Espace selectedItem = table.getSelectionModel().getSelectedItem();
        tmpId=selectedItem.getId();
        nomEspace.setText(selectedItem.getNom());
        capacityEspace.setText(selectedItem.getCapacity() + "");
        lieuEspace.setText(selectedItem.getLieu());
    });
 
     ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList ();
        
        try {
            es.loadData(pieChartData);
             pieChart.setData(pieChartData);
        } catch (SQLException ex) {
            Logger.getLogger(GestionEspaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            pieChart.getData()

                .stream()

                .forEach(data ->{

                    data.getNode().addEventHandler(MouseEvent.ANY, e->{

                        label.setText( "L'Espace:  " +data.getName() + "  Contient:  " + (int)data.getPieValue()
                                +" Place "
      

                             );

                    });

                });

   
          
        /*  FilteredList<Espace> filteredData = new FilteredList<>(obs, p -> true);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(espace -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (espace.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (espace.getCapacity()==(Integer.parseInt(newValue))) {
                    return true; // Filter matches last name.
                } else if (espace.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });
        SortedList<Espace> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
         */
        exportExcel.setOnAction(e->{
            try {
                es.exportExcel();
                 Notifications n = Notifications.create().title("Notification De Confirmation").text("Votre Fichier Excel A été Enregistré Sous Le Répertoire C:\\Users\\Ghaith\\Documents\\NetBeansProjects\\Pepiniers").hideAfter(Duration.seconds(9)).position(Pos.BOTTOM_RIGHT);
                    n.darkStyle();
                    n.showConfirm();
            } catch (IOException ex) {
                Logger.getLogger(GestionEspaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
});
       
    }

    
  
    public void refreshTableView() {
        EspaceService es = new EspaceService();
        ArrayList<Espace> esp = new ArrayList<Espace>(es.getAll());
        ObservableList obs = FXCollections.observableArrayList(esp);
        table.setItems(obs);
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Capacity.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        Lieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));

    }

    public void clearFields() {
        nomEspace.clear();
        capacityEspace.clear();
        lieuEspace.clear();

    }
    
      public void refreshChart () {
           EspaceService es = new EspaceService();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList ();
        
        try {
            es.loadData(pieChartData);
             pieChart.setData(pieChartData);
        } catch (SQLException ex) {
            Logger.getLogger(GestionEspaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            pieChart.getData()

                .stream()

                .forEach(data ->{

                    data.getNode().addEventHandler(MouseEvent.ANY, e->{

                        label.setText( "L'Espace:  " +data.getName() + "  Contient:  " + (int)data.getPieValue()
                                +" Place "
      

                             );

                    });

                });
        
    }

    @FXML
    private void ajouter(ActionEvent event) {

        Espace es = new Espace();

        if ((nomEspace.getText().length() > 10)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La longeur du Nom de l'espace doit étre infériure à 10");
            alert.showAndWait();
        }
        else if (validateNumber ()==false) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Capacité doit étre un chiffre");
            alert.showAndWait();
        }
        else if ((nomEspace.getText().length() < 3)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La longeur du Nom de l'espace doit étre supérieure à 3 caractéres");
            alert.showAndWait();
        } else if (capacityEspace.getText().length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le champ Capacité Doit étre Rempli");
            alert.showAndWait();
        } else if (Integer.parseInt(capacityEspace.getText()) < 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Capacité Doit étre Positive");
            alert.showAndWait();
        } else if ((lieuEspace.getText().length() > 15)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La longeur du Lieu de l'espace doit étre inférieure à 15 caractéres");
            alert.showAndWait();
        } else if ((lieuEspace.getText().length() < 3)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La longeur du Lieu de l'espace doit étre supérieure à 3 caractéres");
            alert.showAndWait();
        } else {
            es.setNom(nomEspace.getText());
            es.setCapacity(Integer.parseInt(capacityEspace.getText()));
            es.setLieu(lieuEspace.getText());
            EspaceService crud = new EspaceService();
            crud.add(es);
            clearFields();
            refreshTableView();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'espace '"+es.getNom()+"' a été ajouté");
            refreshChart();

            alert.showAndWait();
          try {
                if (crud.nbEspaceVide()>0) {
                    Notifications n = Notifications.create().title("Warning Information").text("Il Ya "+crud.nbEspaceVide()+" Espaces Vides").hideAfter(Duration.seconds(7)).position(Pos.BOTTOM_RIGHT);
                    n.darkStyle();
                    n.showWarning(); }
            } catch (SQLException ex) {
                Logger.getLogger(GestionEspaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
          
         
           
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez Vous Vraiment Supprimer'"+table.getSelectionModel().getSelectedItem()+"' ?");

        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            EspaceService s = new EspaceService();
            Espace selectedItem = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedItem);
            s.delete(selectedItem);
            refreshChart();
        }
    }
        @FXML
    private void onEditNom(TableColumn.CellEditEvent<Espace, String> event) {
        EspaceService s = new EspaceService();
        Espace es = table.getSelectionModel().getSelectedItem();
        es.setNom(event.getNewValue());
    }
        @FXML
    private void onEditCapacity(TableColumn.CellEditEvent<Espace, Integer> event) {
        Espace es = table.getSelectionModel().getSelectedItem();
        es.setCapacity(event.getNewValue());
    }
        @FXML
    private void onEditLieu(TableColumn.CellEditEvent<Espace, String> event) {
        Espace es = table.getSelectionModel().getSelectedItem();
        es.setLieu(event.getNewValue());

    }

    @FXML
    private void searchRecord(KeyEvent event) {

        EspaceService es = new EspaceService();
        ArrayList<Espace> esp = new ArrayList<Espace>(es.getAll());
        ObservableList obs = FXCollections.observableArrayList(esp);
        table.setItems(obs);
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Capacity.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        Lieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
        Nom.setCellFactory(TextFieldTableCell.forTableColumn());
        Capacity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        FilteredList<Espace> filteredData = new FilteredList<>(obs, p -> true);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(espace -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                String capEspaceStr = String.valueOf(espace.getCapacity());

                if (espace.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (espace.getLieu().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (capEspaceStr.toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });
        SortedList<Espace> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }
    
  private boolean validateNumber () {
Pattern p = Pattern.compile("[0-9]+");
Matcher m = p.matcher(capacityEspace.getText());
  if (m.find() && m.group().equals(capacityEspace.getText()) )
      return true;
  else {    
 return false; }
  }
   

    @FXML
    private void onClickModifer(ActionEvent event) {
        
      /*
        Espace selectedItem = table.getSelectionModel().getSelectedItem();
        nomEspace.setText(selectedItem.getNom());
        capacityEspace.setText(selectedItem.getCapacity() + "");
        lieuEspace.setText(selectedItem.getLieu());
*/
      
        
        Espace es = new Espace();
        
         if ((nomEspace.getText().length() > 10)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La longeur du Nom de l'espace doit étre infériure à 10");
            alert.showAndWait();
        } else if ((nomEspace.getText().length() < 3)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La longeur du Nom de l'espace doit étre supérieure à 3 caractéres");
            alert.showAndWait();
        } 
        else if (validateNumber ()==false) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Capacité doit étre un chiffre");
            alert.showAndWait();
        } else if (capacityEspace.getText().length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le champ Capacité Doit étre Rempli");
            alert.showAndWait();
        } else if (Integer.parseInt(capacityEspace.getText()) < 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Capacité Doit étre Positive");
            alert.showAndWait();
        } else if ((lieuEspace.getText().length() > 15)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La longeur du Lieu de l'espace doit étre inférieure à 15 caractéres");
            alert.showAndWait();
        } else if ((lieuEspace.getText().length() < 3)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La longeur du Lieu de l'espace doit étre supérieure à 3 caractéres");
            alert.showAndWait();
        } else {
        es.setId(tmpId);
        es.setNom(nomEspace.getText());
        es.setCapacity(Integer.parseInt(capacityEspace.getText()));
        es.setLieu(lieuEspace.getText());
        EspaceService crud = new EspaceService();
        crud.update(es);
        clearFields();
        refreshTableView();
        refreshChart ();
         Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'espace Numéro '"+tmpId+"' a été modifié");

            alert.showAndWait();
        
       
        
    }}



  

  

 

    
  

}
