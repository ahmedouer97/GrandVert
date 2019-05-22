/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Evenement;
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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
/**
 *
 * @author Bouazza Med
 */
public class EvenementService implements IService<Evenement>{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
public EvenementService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }

@Override
    public void add(Evenement e) {
       String req="INSERT INTO evenement(user_id,categorie,titre,organisation,description,image,lieu,adresse,gouvernorat,dated,datef,prix,confa,etat) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     try {
         pst=connection.prepareStatement(req);
         pst.setInt(1, e.getUser_id());
         pst.setString(2, e.getCategorie());
         pst.setString(3, e.getTitre());
         pst.setString(4, e.getOrganisation());
         pst.setString(5, e.getDescription());
         pst.setString(6, e.getImage());
         pst.setString(7, e.getLieu());
         pst.setString(8, e.getAdresse());
         pst.setString(9, e.getGouvernorat() );
         pst.setDate(10, new java.sql.Date(e.getDated().getTime()));
         pst.setDate(11, new java.sql.Date(e.getDatef().getTime()));
         pst.setDouble(12, e.getPrix());
         pst.setInt(13, e.getConfa());
         pst.setInt(14, e.getEtat());
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    @Override
    public void delete(Evenement e){}
   @Override
    public void update(Evenement e) {
        String req="UPDATE evenement SET titre= ?,organisation= ?,description=?,image=?,lieu=?,adresse=?,dated=?,datef=? WHERE id=?";
     try {
          pst=connection.prepareStatement(req);
         pst.setString(1, e.getTitre());
         pst.setString(2, e.getOrganisation());
         pst.setString(3, e.getDescription());
         pst.setString(4, e.getImage());
         pst.setString(5, e.getLieu());
         pst.setString(6, e.getAdresse());
         pst.setDate(7, new java.sql.Date(e.getDated().getTime()));
         pst.setDate(8, new java.sql.Date(e.getDatef().getTime()));
         pst.setInt(9, e.getId());
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }   
    }
    public void confa(int id) {
        String req="UPDATE evenement SET confa=? WHERE id=?";
     try {
          pst=connection.prepareStatement(req);
         pst.setInt(1, 1);
         pst.setInt(2,id);
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }   
    }
      public void etat1(int id) {
        String req="UPDATE evenement SET etat=? WHERE id=?";
     try {
          pst=connection.prepareStatement(req);
         pst.setInt(1, 1);
         pst.setInt(2,id);
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
      }
     public void etat2() {
        String req="UPDATE evenement SET etat=? WHERE dated<=CURRENT_DATE() and etat= ?";
     try {
          pst=connection.prepareStatement(req);
         pst.setInt(1, 2);
                  pst.setInt(2, 0);
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }   
    }
     public void etat22() {
        String req="UPDATE evenement SET etat=? WHERE dated>=CURRENT_DATE() and etat= ?";
     try {
          pst=connection.prepareStatement(req);
         pst.setInt(1, 0);
                  pst.setInt(2, 2);
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }   
    }
    @Override
    public List<Evenement> getAll(){
    List<Evenement>list =new ArrayList<Evenement>();
    return list;
    }
   
    @Override
    public Evenement getById(int id) {
    Evenement e=null;
   String req="SELECT * FROM evenement WHERE id =?";
         List<Evenement>list =new ArrayList<Evenement>();
        try {
            pst=connection.prepareStatement(req);
             pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next()){
           e=new Evenement(rs.getInt("id"),rs.getInt("user_id") ,rs.getString("categorie"), rs.getString("titre"),rs.getString("organisation"), rs.getString("description"), rs.getString("image"), rs.getString("lieu"), rs.getString("adresse"), rs.getString("gouvernorat"), rs.getDate("dated"), rs.getDate("datef"), rs.getDouble("prix"), rs.getInt("confa"),rs.getInt("etat"));
                    }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    public List<Evenement> getByuId(int id) {
    List<Evenement> List = new ArrayList<>();

        String req="select * from evenement where user_id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,id);
            
            rs = pst.executeQuery();
            
            while (rs.next()){
                List.add(new Evenement(rs.getInt("id"),rs.getInt("user_id") ,rs.getString("categorie"), rs.getString("titre"),rs.getString("organisation"), rs.getString("description"), rs.getString("image"), rs.getString("lieu"), rs.getString("adresse"), rs.getString("gouvernorat"), rs.getDate("dated"), rs.getDate("datef"), rs.getDouble("prix"), rs.getInt("confa"),rs.getInt("etat")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }

    public List<Evenement> getByce() {
        List<Evenement> List = new ArrayList<>();

        String req="select * from evenement where confa = ? and etat= ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,1);
            pst.setInt(2,0);
            
            rs = pst.executeQuery();
            
            while (rs.next()){
                List.add(new Evenement(rs.getInt("id"),rs.getInt("user_id") ,rs.getString("categorie"), rs.getString("titre"),rs.getString("organisation"), rs.getString("description"), rs.getString("image"), rs.getString("lieu"), rs.getString("adresse"), rs.getString("gouvernorat"), rs.getDate("dated"), rs.getDate("datef"), rs.getDouble("prix"), rs.getInt("confa"),rs.getInt("etat")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }
    public List<Evenement> getBync() {
        List<Evenement> List = new ArrayList<>();

        String req="select * from evenement where  etat= ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,0);
            
            
            rs = pst.executeQuery();
            
            while (rs.next()){
                List.add(new Evenement(rs.getInt("id"),rs.getInt("user_id") ,rs.getString("categorie"), rs.getString("titre"),rs.getString("organisation"), rs.getString("description"), rs.getString("image"), rs.getString("lieu"), rs.getString("adresse"), rs.getString("gouvernorat"), rs.getDate("dated"), rs.getDate("datef"), rs.getDouble("prix"), rs.getInt("confa"),rs.getInt("etat")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }
}