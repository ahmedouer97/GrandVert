/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Jardin;
import com.behindthemachines.grandvert.utils.WorkshopConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author psn
 */
public class JardinService implements IService<Jardin> {
    
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;
    
    public JardinService(){
        connection = WorkshopConnexion.getInstance().getCnx();
    }

    @Override
    public void add(Jardin t) {
        String req = "INSERT INTO jardin(nom, date_creation, user_id) "
                +"VALUES (?,?,?)";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1,t.getNom());
            pst.setDate(2, t.getDateC());
            pst.setInt(3, t.getUser());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Jardin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Jardin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jardin> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jardin getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Jardin getByUser(int id){
        String req = "select * from jardin where user_id="+id;
        
        try {
            pst=connection.prepareStatement(req);
            result=pst.executeQuery();
            while(result.next()){
                Jardin jardin = new Jardin();
                jardin.setId(result.getInt("id"));
                jardin.setNom(result.getString("nom"));
                jardin.setDateC(result.getDate("date_creation"));
                jardin.setUser(result.getInt("user_id"));
                return jardin;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
