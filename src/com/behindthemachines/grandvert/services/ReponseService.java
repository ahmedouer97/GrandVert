package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Reponse;
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
public class ReponseService implements IService<Reponse>{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;


    public ReponseService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }
    
    @Override
    public void add(Reponse r) {
        String req="insert into reponse(sujet_id , user_id , reponse_original , reponse_edited , date_roriginal , date_redited , archive , nbsignal) values (?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,r.getSujet().getId());
            pst.setInt(2,r.getUser().getId());
            pst.setString(3, r.getReponse_original());
            pst.setString(4, r.getReponse_edited());
            pst.setDate(5, new java.sql.Date(r.getDate_roriginal().getTime()));
            pst.setDate(6, new java.sql.Date(r.getDate_redited().getTime()));
            pst.setInt(7, r.getArchive());
            pst.setInt(8, r.getNbsignal());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Reponse r) {
               String req="update reponse set archive=1 where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,r.getId());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(Reponse r) {
        String req="update reponse set reponse_original = ?, reponse_edited = ? , date_roriginal = ? , date_redited = ? where id = ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, r.getReponse_original());
            pst.setString(2, r.getReponse_edited());
            pst.setDate(3, new java.sql.Date(r.getDate_roriginal().getTime()));
            pst.setDate(4, new java.sql.Date(r.getDate_redited().getTime()));
            pst.setInt(5, r.getId());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reponse> getAll() {
        return null;
    }
    
    public List<Reponse> getAll(int sujet_id) {
        List<Reponse> List = new ArrayList<>();

        try {
            String req = "select * from reponse where archive = false and sujet_id = "+sujet_id;
            
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            
            SujetService sr = new SujetService();
            UserService us = new UserService();
            
            while (result.next()){
                List.add(new Reponse(result.getInt("id"), sr.getById(result.getInt("sujet_id")),us.getById(result.getInt("user_id")), result.getString("reponse_original") , result.getString("reponse_edited"),result.getDate("date_roriginal") , result.getDate("date_redited"), result.getInt("archive"),result.getInt("nbsignal") ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }

    @Override
    public Reponse getById(int id) {
        try {
            String req = "select * from reponse where id = "+id;
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            
            SujetService sr = new SujetService();
            UserService us = new UserService();
            
            while (result.next()){
                return (new Reponse(result.getInt("id"), sr.getById(result.getInt("sujet_id")),us.getById(result.getInt("user_id")), result.getString("reponse_original") , result.getString("reponse_edited"),result.getDate("date_roriginal") , result.getDate("date_redited"), result.getInt("archive"),result.getInt("nbsignal") ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void signaler(Reponse r) {
        
        String req="update reponse set nbsignal = ? , archive = ? where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,r.getNbsignal());
            pst.setInt(2,r.getArchive());
            pst.setInt(3,r.getId());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
