/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Jardin;

import com.behindthemachines.grandvert.entity.Note;
import com.behindthemachines.grandvert.entity.Plantation;
import com.behindthemachines.grandvert.entity.User;
import com.behindthemachines.grandvert.services.NoteService;
import com.behindthemachines.grandvert.services.PlantationService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author psn
 */
public class DaysListController implements Initializable {
    private int currentWeek;
    private int currentYear;
   
    @FXML
    private GridPane oneGrid;
    @FXML
    private Label oneLbl;
    @FXML
    private JFXButton oneNote;
    @FXML
    private TableView<Note> oneNoteTable;
    @FXML
    private TableColumn<Note, Boolean> oneNoteModCol;
    @FXML
    private TableColumn<Note, Boolean> oneNoteDelCol;
    @FXML
    private TableView<Plantation> onePlantTable;
    @FXML
    private TableColumn<Plantation, Boolean> onePlantModCol;
    @FXML
    private TableColumn<Plantation, Boolean> onePlantDelCol;
    
    @FXML
    private GridPane oneGrid1;

    @FXML
    private Label oneLbl1;

    @FXML
    private JFXButton oneNote1;

    @FXML
    private GridPane oneGrid2;

    @FXML
    private Label oneLbl2;

    @FXML
    private JFXButton oneNote2;

    @FXML
    private GridPane oneGrid3;

    @FXML
    private Label oneLbl3;

    @FXML
    private JFXButton oneNote3;

    @FXML
    private GridPane oneGrid4;

    @FXML
    private Label oneLbl4;

    @FXML
    private JFXButton oneNote4;

    @FXML
    private GridPane oneGrid5;

    @FXML
    private Label oneLbl5;

    @FXML
    private JFXButton oneNote5;

    @FXML
    private GridPane oneGrid6;

    @FXML
    private Label oneLbl6;

    @FXML
    private JFXButton oneNote6;

    @FXML
    private TableView<Note> oneNoteTable1;

    @FXML
    private TableColumn<Note, Boolean> oneNoteModCol1;

    @FXML
    private TableColumn<Note, Boolean> oneNoteDelCol1;

    @FXML
    private TableView<Plantation> onePlantTable1;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantModCol1;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantDelCol1;

    @FXML
    private TableView<Note> oneNoteTable2;

    @FXML
    private TableColumn<Note, Boolean> oneNoteModCol2;

    @FXML
    private TableColumn<Note, Boolean> oneNoteDelCol2;

    @FXML
    private TableView<Plantation> onePlantTable2;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantModCol2;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantDelCol2;

    @FXML
    private TableView<Note> oneNoteTable3;

    @FXML
    private TableColumn<Note, Boolean> oneNoteModCol3;

    @FXML
    private TableColumn<Note, Boolean> oneNoteDelCol3;

    @FXML
    private TableView<Plantation> onePlantTable3;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantModCol3;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantDelCol3;

    @FXML
    private TableView<Note> oneNoteTable4;

    @FXML
    private TableColumn<Note, Boolean> oneNoteModCol4;

    @FXML
    private TableColumn<Note, Boolean> oneNoteDelCol4;

    @FXML
    private TableView<Plantation> onePlantTable4;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantModCol4;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantDelCol4;

    @FXML
    private TableView<Note> oneNoteTable5;

    @FXML
    private TableColumn<Note, Boolean> oneNoteModCol5;

    @FXML
    private TableColumn<Note, Boolean> oneNoteDelCol5;

    @FXML
    private TableView<Plantation> onePlantTable5;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantModCol5;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantDelCol5;

    @FXML
    private TableView<Note> oneNoteTable6;

    @FXML
    private TableColumn<Note, Boolean> oneNoteModCol6;

    @FXML
    private TableColumn<Note, Boolean> oneNoteDelCol6;

    @FXML
    private TableView<Plantation> onePlantTable6;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantModCol6;

    @FXML
    private TableColumn<Plantation, Boolean> onePlantDelCol6;
    
    @FXML
    private JFXButton onePlant;
    
    @FXML
    private JFXButton onePlant1;
    
    @FXML
    private JFXButton onePlant2;
    
    @FXML
    private JFXButton onePlant3;
    
    @FXML
    private JFXButton onePlant4;
    
    @FXML
    private JFXButton onePlant5;
    
    @FXML
    private JFXButton onePlant6;
    
    StageManager stager = StageManager.getStageManager();
    
    NoteService noteService = new NoteService();
    
    PlantationService plService = new PlantationService();
    
    private ObservableList<Note> oneNoteList = FXCollections.observableArrayList();
    
    private ObservableList<Note> oneNoteList1 = FXCollections.observableArrayList();
    
    private ObservableList<Note> oneNoteList2 = FXCollections.observableArrayList();
    
    private ObservableList<Note> oneNoteList3 = FXCollections.observableArrayList();
    
    private ObservableList<Note> oneNoteList4 = FXCollections.observableArrayList();
    
    private ObservableList<Note> oneNoteList5 = FXCollections.observableArrayList();
    
    private ObservableList<Note> oneNoteList6 = FXCollections.observableArrayList();
    
    private ObservableList<Plantation> onePlantList = FXCollections.observableArrayList();
    
    private ObservableList<Plantation> onePlantList1 = FXCollections.observableArrayList();
    
    private ObservableList<Plantation> onePlantList2 = FXCollections.observableArrayList();
    
    private ObservableList<Plantation> onePlantList3 = FXCollections.observableArrayList();
    
    private ObservableList<Plantation> onePlantList4 = FXCollections.observableArrayList();
    
    private ObservableList<Plantation> onePlantList5 = FXCollections.observableArrayList();
    
    private ObservableList<Plantation> onePlantList6 = FXCollections.observableArrayList();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i=1;i<=7;i++){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.WEEK_OF_YEAR, currentWeek);
                cal.set(Calendar.DAY_OF_WEEK, i);
                cal.set(Calendar.YEAR, currentYear);
                String dateString = sdf.format(cal.getTime());
                System.out.println(sdf.format(cal.getTime()));
                String dayOfWeek = new SimpleDateFormat("EEEE", Locale.FRENCH).format(cal.getTime());
                String dayName = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
                dayName = dayName + " le " + dateString;
                switch (i) {
                    case 1:
                        oneLbl.setText(dayName);
                        oneNote.setText(dateString);
                        onePlant.setText(dateString);
                        
                                loadNotes(dateString,i);
                            
                        break;
                        
                    case 2:
                        oneLbl1.setText(dayName);
                        oneNote1.setText(dateString);
                        onePlant1.setText(dateString);
                        loadNotes(dateString,i);
                        break;
                    case 3:
                        oneLbl2.setText(dayName);
                        oneNote2.setText(dateString);
                        onePlant2.setText(dateString);
                        loadNotes(dateString,i);
                        break;
                    case 4:
                        oneLbl3.setText(dayName);
                        oneNote3.setText(dateString);
                        onePlant3.setText(dateString);
                        loadNotes(dateString,i);
                        break;
                    case 5:
                        oneLbl4.setText(dayName);
                        oneNote4.setText(dateString);
                        onePlant4.setText(dateString);
                        loadNotes(dateString,i);
                        break;
                    case 6:
                        oneLbl5.setText(dayName);
                        oneNote5.setText(dateString);
                        onePlant5.setText(dateString);
                        loadNotes(dateString,i);
                        break;
                    case 7:
                        oneLbl6.setText(dayName);
                        oneNote6.setText(dateString);
                        onePlant6.setText(dateString);
                        loadNotes(dateString,i);
                        break;
                    default:
                        oneLbl.setText("Not Set");
                        break;
                }
            } catch (ParseException ex) {
                Logger.getLogger(DaysListController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
        initCols();
    }
    
    public DaysListController(int currentWeek, int currentYear){
        this.currentWeek=currentWeek;
        this.currentYear=currentYear;
    }
    
    @FXML
    private void addNote(ActionEvent event) throws IOException {
        JFXButton source = (JFXButton) event.getSource();
        String date = source.getText();
        System.out.println("button date: "+ date);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/behindthemachines/grandvert/Jardin/AddNote.fxml"));
            AddNoteController controller = new AddNoteController(stager.getJardinId(),date);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajouter une note");
            stage.show();
    }

    @FXML
    private void addPlantation(ActionEvent event) throws IOException {
        JFXButton source = (JFXButton) event.getSource();
        String date = source.getText();
        System.out.println("button date: "+ date);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/behindthemachines/grandvert/Jardin/AddPlantation.fxml"));
            AddPlantationController controller = new AddPlantationController(stager.getJardinId(),date);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajouter une plantation");
            stage.show();
    }
    
    private void loadNotes(String date, int i) throws ParseException{
        System.out.println("LoadNotes method lunched");
        java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        switch (i) { 
                case 1: 
                    oneNoteList.clear();
                    oneNoteList.setAll(noteService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    oneNoteTable.setItems(oneNoteList);
                    break; 
 
                case 2: 
                    oneNoteList1.clear();
                    oneNoteList1.setAll(noteService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    oneNoteTable1.setItems(oneNoteList1);
                    break; 
                case 3: 
                    oneNoteList2.clear();
                    oneNoteList2.setAll(noteService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    oneNoteTable2.setItems(oneNoteList2);
                    break; 
                case 4: 
                    oneNoteList3.clear();
                    oneNoteList3.setAll(noteService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    oneNoteTable3.setItems(oneNoteList3);
                    break; 
                case 5: 
                    oneNoteList4.clear();
                    oneNoteList4.setAll(noteService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    oneNoteTable4.setItems(oneNoteList4);
                    break; 
                case 6: 
                    oneNoteList5.clear();
                    oneNoteList5.setAll(noteService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    oneNoteTable5.setItems(oneNoteList5);
                    break; 
                case 7: 
                    oneNoteList6.clear();
                    oneNoteList6.setAll(noteService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    oneNoteTable6.setItems(oneNoteList6);
                    break; 
                default: 
                    System.out.println("wrong i");
                    break; 
                } 
    }
    
    private void loadPlantations(String date, int i) throws ParseException{
        System.out.println("LoadNotes method lunched");
        java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        switch (i) { 
                case 1: 
                    onePlantList.clear();
                    onePlantList.setAll(plService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    onePlantTable.setItems(onePlantList);
                    break; 
 
                case 2: 
                    onePlantList1.clear();
                    onePlantList1.setAll(plService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    onePlantTable1.setItems(onePlantList1);
                    break; 
                case 3: 
                    onePlantList2.clear();
                    onePlantList2.setAll(plService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    onePlantTable2.setItems(onePlantList2);
                    break; 
                case 4: 
                    onePlantList3.clear();
                    onePlantList3.setAll(plService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    onePlantTable3.setItems(onePlantList3);
                    break; 
                case 5: 
                    onePlantList4.clear();
                    onePlantList4.setAll(plService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    onePlantTable4.setItems(onePlantList4);
                    break; 
                case 6: 
                    onePlantList5.clear();
                    onePlantList5.setAll(plService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    onePlantTable5.setItems(onePlantList5);
                    break; 
                case 7: 
                    onePlantList6.clear();
                    onePlantList6.setAll(plService.getByJardinAndDate(stager.getJardinId(), sqlDate));
                    onePlantTable6.setItems(onePlantList6);
                    break; 
                default: 
                    System.out.println("wrong i");
                    break; 
                } 
    }
    
    private void initCols(){
        System.out.println("InitCols method lunched");
        oneNoteTable.getSelectionModel().setCellSelectionEnabled(false);
        oneNoteTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        oneNoteModCol.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        oneNoteModCol.setCellFactory(fn_edit_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        oneNoteDelCol.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        oneNoteDelCol.setCellFactory(fn_delete_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Note note = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : noteID= "+note.getId());
                    noteService.delete(note);
                });
            } 
            }
        });
        
        oneNoteTable1.getSelectionModel().setCellSelectionEnabled(false);
        oneNoteTable1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        oneNoteModCol1.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        oneNoteModCol1.setCellFactory(fn_edit_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        oneNoteDelCol1.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        oneNoteDelCol1.setCellFactory(fn_delete_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Note note = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : noteID= "+note.getId());
                    noteService.delete(note);
                });
            } 
            }
        });
        
        oneNoteTable2.getSelectionModel().setCellSelectionEnabled(false);
        oneNoteTable2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        oneNoteModCol2.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        oneNoteModCol2.setCellFactory(fn_edit_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        oneNoteDelCol2.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        oneNoteDelCol2.setCellFactory(fn_delete_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Note note = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : noteID= "+note.getId());
                    noteService.delete(note);
                });
            } 
            }
        });
        
        oneNoteTable3.getSelectionModel().setCellSelectionEnabled(false);
        oneNoteTable3.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        oneNoteModCol3.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        oneNoteModCol3.setCellFactory(fn_edit_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        oneNoteDelCol3.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        oneNoteDelCol3.setCellFactory(fn_delete_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Note note = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : noteID= "+note.getId());
                    noteService.delete(note);
                });
            } 
            }
        });
        
        oneNoteTable4.getSelectionModel().setCellSelectionEnabled(false);
        oneNoteTable4.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        oneNoteModCol4.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        oneNoteModCol4.setCellFactory(fn_edit_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        oneNoteDelCol4.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        oneNoteDelCol4.setCellFactory(fn_delete_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Note note = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : noteID= "+note.getId());
                    noteService.delete(note);
                });
            } 
            }
        });
        
        oneNoteTable5.getSelectionModel().setCellSelectionEnabled(false);
        oneNoteTable5.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        oneNoteModCol5.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        oneNoteModCol5.setCellFactory(fn_edit_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        oneNoteDelCol5.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        oneNoteDelCol5.setCellFactory(fn_delete_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Note note = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : noteID= "+note.getId());
                    noteService.delete(note);
                });
            } 
            }
        });
        
        oneNoteTable6.getSelectionModel().setCellSelectionEnabled(false);
        oneNoteTable6.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        oneNoteModCol6.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        oneNoteModCol6.setCellFactory(fn_edit_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        oneNoteDelCol6.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        oneNoteDelCol6.setCellFactory(fn_delete_produit ->
    new TableCell<Note, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Note note = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : noteID= "+note.getId());
                    noteService.delete(note);
                });
            } 
            }
        });
        
    }
    
    private void initColss(){
        System.out.println("InitCols method lunched");
        onePlantTable.getSelectionModel().setCellSelectionEnabled(false);
        onePlantTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        onePlantModCol.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        onePlantModCol.setCellFactory(fn_edit_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        onePlantModCol.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        onePlantModCol.setCellFactory(fn_delete_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Plantation plant = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : plantationID= "+plant.getId());
                    plService.delete(plant);
                });
            } 
            }
        });
        
        onePlantTable1.getSelectionModel().setCellSelectionEnabled(false);
        onePlantTable1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        onePlantModCol1.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        onePlantModCol1.setCellFactory(fn_edit_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        onePlantModCol1.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        onePlantModCol1.setCellFactory(fn_delete_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Plantation plant = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : plantationID= "+plant.getId());
                    plService.delete(plant);
                });
            } 
            }
        });
        
        onePlantTable2.getSelectionModel().setCellSelectionEnabled(false);
        onePlantTable2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        onePlantModCol2.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        onePlantModCol2.setCellFactory(fn_edit_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        onePlantModCol2.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        onePlantModCol2.setCellFactory(fn_delete_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Plantation plant = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : plantationID= "+plant.getId());
                    plService.delete(plant);
                });
            } 
            }
        });
        
        onePlantTable3.getSelectionModel().setCellSelectionEnabled(false);
        onePlantTable3.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        onePlantModCol3.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        onePlantModCol3.setCellFactory(fn_edit_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        onePlantModCol3.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        onePlantModCol3.setCellFactory(fn_delete_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Plantation plant = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : plantationID= "+plant.getId());
                    plService.delete(plant);
                });
            } 
            }
        });
        
        onePlantTable4.getSelectionModel().setCellSelectionEnabled(false);
        onePlantTable4.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        onePlantModCol4.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        onePlantModCol4.setCellFactory(fn_edit_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        onePlantModCol4.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        onePlantModCol4.setCellFactory(fn_delete_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Plantation plant = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : plantationID= "+plant.getId());
                    plService.delete(plant);
                });
            } 
            }
        });
        
        onePlantTable5.getSelectionModel().setCellSelectionEnabled(false);
        onePlantTable5.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        onePlantModCol5.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        onePlantModCol5.setCellFactory(fn_edit_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        onePlantModCol5.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        onePlantModCol5.setCellFactory(fn_delete_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Plantation plant = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : plantationID= "+plant.getId());
                    plService.delete(plant);
                });
            } 
            }
        });
        
        onePlantTable6.getSelectionModel().setCellSelectionEnabled(false);
        onePlantTable6.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        onePlantModCol6.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        onePlantModCol6.setCellFactory(fn_edit_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton("");
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/edit.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    
                });
            } 
            }
        });
        onePlantModCol6.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        onePlantModCol6.setCellFactory(fn_delete_produit ->
    new TableCell<Plantation, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/delete.png"));
                
                ImageView iv = new ImageView();
                iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
                iv.setCache(true);
                iv.setFitHeight(20);
                iv.setFitWidth(20);
                
                viewBtn.setGraphic(iv);
                setGraphic(viewBtn);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
                setText(null);
                // ** button action
                viewBtn.setOnAction(e -> {
                    //buttonAction
                    Plantation plant = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : plantationID= "+plant.getId());
                    plService.delete(plant);
                });
            } 
            }
        });
        
    }
    
    private void loadModNote(String date){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/behindthemachines/grandvert/Jardin/AddNote.fxml"));
            AddNoteController controller = new AddNoteController(1,date);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajouter une note");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DaysListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
