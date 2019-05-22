package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Reaction_Reponse;
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
public class ReactionReponseService implements IService<Reaction_Reponse>{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;


    public ReactionReponseService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }
    
    @Override
    public void add(Reaction_Reponse r) {
        String req="insert into reaction_reponse(reponse_id , user_id ,reaction) values (?,?,?)";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,r.getReponse().getId());
            pst.setInt(2,r.getUser_id());
            pst.setString(3, r.getReaction());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Reaction_Reponse r) {
               String req="delete from reaction_reponse where reponse_id = ? and user_id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,r.getReponse().getId());
            pst.setInt(2,r.getUser_id());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionReponseService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(Reaction_Reponse r) {
        String req="update reaction_reponse set reaction = ? where reponse_id = ? and user_id = ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, r.getReaction());
            pst.setInt(2,r.getReponse().getId());
            pst.setInt(3,r.getUser_id());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reaction_Reponse> getAll() {
        return null;
    }

    @Override
    public Reaction_Reponse getById(int id) {
        return null;
    }
    
    public List<Reaction_Reponse> getAll(int reponse_id , String reaction) {
        List<Reaction_Reponse> List = new ArrayList<>();

        try {
            String req = "select * from reaction_reponse where reponse_id = ? and reaction = ?";
            
            pst = connection.prepareStatement(req);
            pst.setInt(1,reponse_id);
            pst.setString(2, reaction );
            
            result = pst.executeQuery();
            
            ReponseService sr = new ReponseService();
            while (result.next()){
                List.add(new Reaction_Reponse(sr.getById(result.getInt("reponse_id")),result.getInt("user_id"), result.getString("reaction")  ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }
    
    public List<Reaction_Reponse> getUserReaction(int reponse_id , int user_id) {
        List<Reaction_Reponse> List = new ArrayList<>();

        try {
            String req = "select * from reaction_reponse where reponse_id = ? and user_id = ?";
            
            pst = connection.prepareStatement(req);
            pst.setInt(1,reponse_id);
            pst.setInt(2, user_id );
            
            result = pst.executeQuery();
            
            ReponseService rs = new ReponseService();
            while (result.next()){
                List.add(new Reaction_Reponse(rs.getById(result.getInt("reponse_id")),result.getInt("user_id"), result.getString("reaction")  ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReactionSujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }     
    
}
