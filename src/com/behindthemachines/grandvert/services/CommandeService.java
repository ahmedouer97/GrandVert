/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Commande;
import com.behindthemachines.grandvert.utils.StageManager;
import com.behindthemachines.grandvert.utils.WorkshopConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class CommandeService implements IService<Commande>{
private Connection connection;
 private PreparedStatement pst;
    private ResultSet rs;
    public CommandeService() {
        connection=WorkshopConnexion.getInstance().getCnx();
    }
    @Override
    public void add(Commande t) {
        String req="INSERT INTO commande(plante_id,contite,etat,date,user_id) VALUES (?,?,?,?,?)";
     try {
         pst=connection.prepareStatement(req);
         pst.setInt(1, t.getPlante().getId());
         pst.setInt(2, t.getContite());
         pst.setString(3, t.getEtat());
         pst.setTimestamp(4, t.getDate());
          pst.setInt(5, StageManager.getStageManager().getUser().getId());
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    @Override
    public void delete(Commande t) {
     
    }

    @Override
    public void update(Commande t) {
        String req="UPDATE commande SET etat= ? WHERE id=?";
     try {
         pst=connection.prepareStatement(req);
         pst.setString(1, t.getEtat());       
         pst.setInt(2, t.getId());
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
     }  
    }

    @Override
    public List<Commande> getAll() {
     String req="SELECT * FROM commande";
         List<Commande>list =new ArrayList<Commande>();
        try {
            pst=connection.prepareStatement(req);
              PlanteService ps =new PlanteService();
               UserService us=new UserService();
            rs=pst.executeQuery();
            while(rs.next()){
            list.add(new Commande(rs.getInt("id"), ps.getById(rs.getInt("plante_id")), rs.getInt("contite"), rs.getString("etat"), rs.getTimestamp("date"), us.getById(rs.getInt("user_id"))));
                    }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 public List<Commande> getByetat(String etat) {
        String req="SELECT * FROM commande WHERE etat=?";
         List<Commande>list =new ArrayList<Commande>();
          UserService us=new UserService();
        try {
            pst=connection.prepareStatement(req);
             pst.setString(1,etat );
              PlanteService ps =new PlanteService();
            rs=pst.executeQuery();
            while(rs.next()){
            list.add(new Commande(rs.getInt("id"),ps.getById(rs.getInt("plante_id")) ,rs.getInt("contite"), rs.getString("etat"),rs.getTimestamp("date"),us.getById(rs.getInt("user_id"))));
                    }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
     
    }
    @Override
    public Commande getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
