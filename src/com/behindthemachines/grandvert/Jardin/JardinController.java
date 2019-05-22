/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Jardin;

/**
 *
 * @author psn
 */

import com.behindthemachines.grandvert.services.JardinService;
import com.behindthemachines.grandvert.services.NoteService;
import com.behindthemachines.grandvert.services.PlantationService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.jfoenix.controls.JFXSlider;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class JardinController implements Initializable{

    @FXML
    private JFXSlider slider;
    @FXML
    private AnchorPane daysAnchor;
    
    StageManager stager = StageManager.getStageManager();
    
    PlantationService plService = new PlantationService();
    
    NoteService noteService = new NoteService();
    
    JardinService jardinService = new JardinService();
    
    public int currentYear = 2019;
    
    public int currentWeek = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDaysList();
    }

    @FXML
    private void released(MouseEvent event) {
        
    }

    private void loadDaysList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/behindthemachines/grandvert/Jardin/DaysList.fxml"));
            DaysListController controller = new DaysListController(currentWeek,currentYear);
            loader.setController(controller);
            daysAnchor.getChildren().setAll((AnchorPane) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(JardinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changeDate(MouseEvent event) {
        int week = ( int ) slider.getValue();
        currentWeek = week;
        System.out.println(currentWeek);
        for (int i=1;i<=7;i++){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.WEEK_OF_YEAR, week);
            cal.set(Calendar.DAY_OF_WEEK, i);
            cal.set(Calendar.YEAR, currentYear);
            System.out.println(sdf.format(cal.getTime()));
        }
        
        loadDaysList();
    }

}

