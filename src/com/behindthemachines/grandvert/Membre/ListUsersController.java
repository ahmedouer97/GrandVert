/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Membre;

import com.behindthemachines.grandvert.entity.User;
import com.behindthemachines.grandvert.services.UserService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author psn
 */
public class ListUsersController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Boolean> editCol;
    @FXML
    private TableColumn<User, Boolean> suppCol;
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXTextField filterTxt;
    @FXML
    private AnchorPane windowBar;
    @FXML
    private TableColumn<User, String> usernameCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, String> enabledCol;
    @FXML
    private TableColumn<User, String> rolesCol;
    @FXML
    private TableColumn<User, String> nomCol;
    @FXML
    private TableColumn<User, String> prenomCol;
    @FXML
    private TableColumn<User, Integer> telCol;
    
    StageManager stageManager = StageManager.getStageManager();
    
    UserService userService = new UserService();
    
    private ObservableList<User> userList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, Boolean> ChangeRoleCol;
    
    private StageManager stage = StageManager.getStageManager();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadProduits();
    }
    
    
    // *Initializing Table View
    private void initCol(){
        // ** init table
        table.getSelectionModel().setCellSelectionEnabled(false);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // ** init columns
        usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        enabledCol.setCellValueFactory(new PropertyValueFactory("enabled"));
        rolesCol.setCellValueFactory(new PropertyValueFactory("roles"));
        nomCol.setCellValueFactory(new PropertyValueFactory("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory("prenom"));
        telCol.setCellValueFactory(new PropertyValueFactory("tel"));
        editCol.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
        // ** setting the 'edit Product' button into the cell
        editCol.setCellFactory(fn_edit_produit ->
    new TableCell<User, Boolean>(){
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
                    User user = getTableView().getItems().get(getIndex());
                    System.out.println(user.getUsername());
                    
                });
            } 
            }
        });
        suppCol.setCellValueFactory(new PropertyValueFactory<>("DELETE"));
        // ** setting the 'delete product' button into the cell
        suppCol.setCellFactory(fn_delete_produit ->
    new TableCell<User, Boolean>() {
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
                    User user = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : User= "+user.getUsername()+", ID= "+user.getId());
                    userService.delete(user);
                    loadProduits();
                });
            } 
            }
        });
        
        if(stage.getUser().getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
            ChangeRoleCol.setVisible(false);
            editCol.setVisible(false);
        }
        else
        {
            ChangeRoleCol.setVisible(true);
            editCol.setVisible(true);
        }
    
        ChangeRoleCol.setCellValueFactory(new PropertyValueFactory<>("ChangeRole"));
        // ** setting the 'delete product' button into the cell
        ChangeRoleCol.setCellFactory(fn_delete_produit ->
    new TableCell<User, Boolean>() {
            final JFXButton viewBtn = new JFXButton();
            @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                Image img = new Image(getClass().getResourceAsStream("/Icon/Security.png"));
                
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
                    User user = getTableView().getItems().get(getIndex());
                    System.out.println("Deleting : User= "+user.getUsername()+", ID= "+user.getId());

                    ChnagerRole(user);
                    loadProduits();
                });
            } 
            }
        });        
    }

    // Loading all products into Talbe View
    private void loadProduits() {
        userList.clear();
        userList.setAll(userService.getAll());
        table.setItems(userList);
    }
    
    public void ChnagerRole(User u){
        UserService us = new UserService();
        if (u.getRoles().equals("a:0:{}"))
            u.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
        else
            u.setRoles("a:0:{}");
        
        us.changerRole(u);
    }
    
}
