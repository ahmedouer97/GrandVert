package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Sujet;
import com.behindthemachines.grandvert.utils.WorkshopConnexion;
import java.sql.Connection;
import java.sql.Date;
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
public class SujetService implements IService<Sujet>{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;


    public SujetService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }
    
    @Override
    public void add(Sujet s) {
        String req="insert into sujet(plante_id , user_id , sujet_original , sujet_edited , date_original , date_edited , nbshare , nbviews , open , resolu , archive , nbsignal) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,s.getPlante_id());
            pst.setInt(2,s.getUser().getId());
            pst.setString(3, s.getSujet_original());
            pst.setString(4, s.getSujet_edited());
            pst.setDate(5, new java.sql.Date(s.getDate_original().getTime()));
            pst.setDate(6, new java.sql.Date(s.getDate_edited().getTime()));
            pst.setInt(7, s.getNbshare());
            pst.setInt(8, s.getNbviews());
            pst.setString(9, s.getOpen());
            pst.setString(10, s.getResolu());
            pst.setInt(11, s.getArchive());
            pst.setInt(12, s.getNbsignal());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Sujet s) {
        String req="update sujet set archive=? where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,1);
            pst.setInt(2,s.getId());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(Sujet s) {
        int nbviews = s.getNbviews();
        String req="update sujet set nbviews=? where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,nbviews+1);
            pst.setInt(2,s.getId());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public List<Sujet> getAll() {
        List<Sujet> List = new ArrayList<>();

        try {
            String req = "select * from sujet where archive = false";
            
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            UserService us = new UserService();
            while (result.next()){
                List.add(new Sujet(result.getInt("id"), result.getInt("plante_id"),us.getById(result.getInt("user_id")), result.getString("sujet_original") , result.getString("sujet_edited"),result.getDate("date_original") , result.getDate("date_edited"), result.getInt("nbshare") ,result.getInt("nbviews"), result.getString("open"), result.getString("resolu") , result.getInt("archive"),result.getInt("nbsignal") ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }
    
    public List<Sujet> getAll(int plante_id) {
        List<Sujet> List = new ArrayList<>();

        try {
            String req = "select * from sujet where archive = false and plante_id ="+plante_id;
            
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            UserService us = new UserService();

            while (result.next()){
                List.add(new Sujet(result.getInt("id"), result.getInt("plante_id"),us.getById(result.getInt("user_id")), result.getString("sujet_original") , result.getString("sujet_edited"),result.getDate("date_original") , result.getDate("date_edited"), result.getInt("nbshare") ,result.getInt("nbviews"), result.getString("open"), result.getString("resolu") , result.getInt("archive"),result.getInt("nbsignal") ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }

    @Override
    public Sujet getById(int id) {
        try {
            String req = "select * from sujet where id = "+id;
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            UserService us = new UserService();

            while (result.next()){
                return (new Sujet(result.getInt("id"), result.getInt("plante_id"),us.getById(result.getInt("user_id")), result.getString("sujet_original") , result.getString("sujet_edited"),result.getDate("date_original") , result.getDate("date_edited"), result.getInt("nbshare") ,result.getInt("nbviews"), result.getString("open"), result.getString("resolu") , result.getInt("archive"),result.getInt("nbsignal") ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void signaler(Sujet s) {
        
        String req="update sujet set nbsignal = ? , archive = ? where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,s.getNbsignal());
            pst.setInt(2,s.getArchive());
            pst.setInt(3,s.getId());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void ouvrir(int id_sujet) {
        
        String req="update sujet set open = 'true' where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,id_sujet);

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void fermer(int id_sujet) {
        
        String req="update sujet set open = 'false' where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,id_sujet);

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void resolu(int id_sujet) {
        
        String req="update sujet set resolu = 'true' where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,id_sujet);

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void NonResolu(int id_sujet) {
        
        String req="update sujet set resolu = 'false' where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,id_sujet);

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
   
    
    public List<Sujet> getAllSujetUser(int user_id) {
        List<Sujet> List = new ArrayList<>();

        try {
            String req = "select * from sujet where archive = 0 and user_id = "+user_id;
            
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            UserService us = new UserService();

            while (result.next()){
                List.add(new Sujet(result.getInt("id"), result.getInt("plante_id"),us.getById(result.getInt("user_id")), result.getString("sujet_original") , result.getString("sujet_edited"),result.getDate("date_original") , result.getDate("date_edited"), result.getInt("nbshare") ,result.getInt("nbviews"), result.getString("open"), result.getString("resolu") , result.getInt("archive"),result.getInt("nbsignal") ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }    
}
