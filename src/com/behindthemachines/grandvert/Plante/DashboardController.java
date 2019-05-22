/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Plante;

import com.behindthemachines.grandvert.entity.Commande;
import com.behindthemachines.grandvert.services.CommandeService;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class DashboardController implements Initializable {

    @FXML
    private LineChart<String, Integer> line;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Date date=new Date(); // your date
        Calendar cal = Calendar.getInstance();
int year = cal.get(Calendar.YEAR);    
        CommandeService cs=new CommandeService();
        int x=0,y=0,z=0;
        List<Commande>c=cs.getAll();
        for (int i = 0; i < c.size(); i++) {
            cal.setTime(new Date( c.get(i).getDate().getTime()));
        int yea = cal.get(Calendar.YEAR);
         if(yea==year)x++;
         if(yea==year-1)y++;
         if(yea==year-2)z++;
        }
           XYChart.Series<String, Integer>series=new XYChart.Series<String, Integer>();
            series.getData().add(new XYChart.Data<String,Integer>(Integer.toString(year-2),z));
           series.getData().add(new XYChart.Data<String,Integer>(Integer.toString(year-1),y));
           series.getData().add(new XYChart.Data<String,Integer>(Integer.toString(year),x));
           line.getData().add(series);
       ;
    }    
    
}
