/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;


import com.behindthemachines.grandvert.entity.Evenement;
import com.behindthemachines.grandvert.entity.Participants;
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
 * @author Bouazza Med
 */
public class ParticipantsService {
    
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ParticipantsService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }
    public void add(Participants e) {
       String req="INSERT INTO participants (event_id,user_id,statut) VALUES (?,?,?)";
     try {
         pst=connection.prepareStatement(req);
         pst.setInt(1, e.getEvent_id());
         pst.setInt(2, e.getUser_id());
         pst.setInt(3, e.getStatut());
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(ParticipantsService.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    public void delete(Participants t) {
     String req="DELETE FROM participants WHERE event_id = ? and user_id= ?";
        try {
           pst=connection.prepareStatement(req);
             pst.setInt(1, t.getEvent_id());
             pst.setInt(2, t.getUser_id());
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Participants> getAll(){
    List<Participants>list =new ArrayList<Participants>();
    return list;
    }
   
    public Participants getBy2Id(int eid,int uid) {
    Participants e=null;
   String req="SELECT * FROM participants WHERE event_id =? and user_id= ?  ";
         List<Participants>list =new ArrayList<Participants>();
        try {
            pst=connection.prepareStatement(req);
             pst.setInt(1, eid);
             pst.setInt(2, uid);
            rs=pst.executeQuery();
            while(rs.next()){
           e=new Participants(rs.getInt("event_id"),rs.getInt("user_id") );
                    }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    public List<Evenement> getByuId(int uid) {
    List<Evenement> List = new ArrayList<>();

        String req="SELECT *  FROM evenement JOIN participants ON evenement.id = participants.event_id WHERE participants.user_id= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,uid);
            
            rs = pst.executeQuery();
            
            while (rs.next()){
                List.add(new Evenement(rs.getInt("id"),rs.getInt("user_id") ,rs.getString("categorie"), rs.getString("titre"),rs.getString("organisation"), rs.getString("description"), rs.getString("image"), rs.getString("lieu"), rs.getString("adresse"), rs.getString("gouvernorat"), rs.getDate("dated"), rs.getDate("datef"), rs.getDouble("prix"), rs.getInt("confa"),rs.getInt("etat")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }
}
