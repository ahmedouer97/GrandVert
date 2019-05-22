/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Note;
import com.behindthemachines.grandvert.entity.Plantation;
import com.behindthemachines.grandvert.utils.WorkshopConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author psn
 */
public class PlantationService implements IService<Plantation> {
    
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;
    
    public PlantationService(){
        connection = WorkshopConnexion.getInstance().getCnx();
    }

    @Override
    public void add(Plantation t) {
        String req = "INSERT INTO plantation(plante_id, jardin_id, date_plantation, type_plantation, quantite) "
                +"VALUES (?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,t.getPlante());
            pst.setInt(2, t.getJardin());
            pst.setDate(3, t.getDateP());
            pst.setString(4, t.getTypeP());
            pst.setInt(5, t.getQt());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Plantation t) {
        String req="DELETE FROM plantation WHERE id = ?";
        try {
           pst=connection.prepareStatement(req);
             pst.setInt(1, t.getId());
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Plantation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Plantation> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Plantation getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Plantation> getByJardinAndDate(int jardinId, Date dateN){
        List<Plantation> list = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(dateN);  
        System.out.println("NOTE SERVICE DATE; "+date);  
        String req = "SELECT * from plantation where jardin_id=? AND date_plantation=?";
        
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, jardinId);
            pst.setDate(2, dateN);
            result=pst.executeQuery();
            while(result.next()){
                Plantation plant = new Plantation();
                plant.setId(result.getInt("id"));
                plant.setPlante(result.getInt("plante_id"));
                plant.setJardin(result.getInt("jardin_id"));
                plant.setDateP(result.getDate("date_plantation"));
                plant.setTypeP(result.getString("type_plantation"));
                plant.setQt(result.getInt("quantite"));
                list.add(plant);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
