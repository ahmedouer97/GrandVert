package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Reaction_Sujet;
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
public class ReactionSujetService implements IService<Reaction_Sujet>{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;


    public ReactionSujetService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }
    
    @Override
    public void add(Reaction_Sujet r) {
        String req="insert into reaction_sujet(sujet_id , user_id ,reaction) values (?,?,?)";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,r.getSujet().getId());
            pst.setInt(2,r.getUser_id());
            pst.setString(3, r.getReaction());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionSujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Reaction_Sujet r) {
               String req="delete from reaction_sujet where sujet_id = ? and user_id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,r.getSujet().getId());
            pst.setInt(2,r.getUser_id());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionSujetService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(Reaction_Sujet r) {
        String req="update reaction_sujet set reaction = ? where sujet_id = ? and user_id = ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, r.getReaction());
            pst.setInt(2,r.getSujet().getId());
            pst.setInt(3,r.getUser_id());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionSujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reaction_Sujet> getAll() {
        return null;
    }

    @Override
    public Reaction_Sujet getById(int id) {
        return null;
    }
    
    public List<Reaction_Sujet> getAll(int sujet_id , String reaction) {
        List<Reaction_Sujet> List = new ArrayList<>();

        try {
            String req = "select * from reaction_sujet where sujet_id = ? and reaction = ?";
            
            pst = connection.prepareStatement(req);
            pst.setInt(1,sujet_id);
            pst.setString(2, reaction );
            
            result = pst.executeQuery();
            
            SujetService sr = new SujetService();
            while (result.next()){
                List.add(new Reaction_Sujet(sr.getById(result.getInt("sujet_id")),result.getInt("user_id"), result.getString("reaction")  ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionSujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }
    
    public List<Reaction_Sujet> getUserReaction(int sujet_id , int user_id) {
        List<Reaction_Sujet> List = new ArrayList<>();

        try {
            String req = "select * from reaction_sujet where sujet_id = ? and user_id = ?";
            
            pst = connection.prepareStatement(req);
            pst.setInt(1,sujet_id);
            pst.setInt(2, user_id );
            
            result = pst.executeQuery();
            
            SujetService sr = new SujetService();
            while (result.next()){
                List.add(new Reaction_Sujet(sr.getById(result.getInt("sujet_id")),result.getInt("user_id"), result.getString("reaction")  ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionSujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }    
    
}
