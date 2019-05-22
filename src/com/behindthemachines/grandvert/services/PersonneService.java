package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Personne;
import com.behindthemachines.grandvert.utils.WorkshopConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ROCH
 */
public class PersonneService implements IService<Personne>{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;


    public PersonneService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }
    
    @Override
    public void add(Personne t) {
        String req="insert into Personne(nom , prenom) values ('"+t.getNom()+"' , '"+t.getPrenom()+"')";
        try {
            ste = connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addPST(Personne t) {
        String req="insert into Personne(nom , prenom) values (? , ?)";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1,t.getNom());
            pst.setString(2,t.getPrenom());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Personne t) {
    }

    @Override
    public void update(Personne t) {
    }

    @Override
    public List<Personne> getAll() {
        List<Personne> List = new ArrayList<>();

        try {
            String req = "select * from personne";
            
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            
            while (result.next()){
                List.add(new Personne(result.getInt("id"), result.getString("nom") , result.getString("prenom")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }

    @Override
    public Personne getById(int id) {
        return null;
    }
    
}
