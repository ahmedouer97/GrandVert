/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Reservation;
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
 * @author Ghaith
 */
public class ReservationService implements IService <Reservation> {
    
    private Connection connection;
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;
    public ReservationService() {
        connection=WorkshopConnexion.getInstance().getCnx();
    }
    
    

    @Override
    public void add(Reservation t) {
    
     
        
             String req = "insert into preservation (DateDebut,DateFin,NbPlaces,espace_de_preservation_id,user_id) values ('"+t.getDateDebut()+"','"+t.getDateFin()+"','"+t.getNbPlaces()+"','"+t.getEspace().getId()+"','"+t.getUser().getId()+"')";
        try {
           
            ste=connection.createStatement();
            ste.executeUpdate(req);
                    } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
      public void updateNew(int newCapacity,int idEspace) {
       
       
           String req = "update espace_de_preservation set Capacity= ? where id = ?";
        try {
            ps= connection.prepareStatement(req);
            
            ps.setInt(1, newCapacity);
            ps.setInt(2, idEspace);
          
            ps.executeUpdate();
                    } catch (SQLException ex) {
            Logger.getLogger(EspaceService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @Override
    public void delete(Reservation t) {
        
               String requete = "delete from preservation where id = '"+t.getId()+"' ";
        try {
            ps = connection.prepareStatement(requete);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Reservation t) {
             String req = "update preservation set DateDebut = ? , DateFin= ? , NbPlaces = ? where id = ?";
        try {
           
            ps= connection.prepareStatement(req);
            ps.setDate(1, t.getDateDebut());
  
            ps.setDate(2, t.getDateFin());
            ps.setInt(3, t.getNbPlaces());
           
            ps.setInt(4, t.getId());
            
            ps.executeUpdate();
                    } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }
    
    public void updateEsp(Reservation t,int idEspace) {
             String req = "update preservation set DateDebut = ? , DateFin= ? , NbPlaces = ? , espace_de_preservation_id = ? where id = ?";
        try {
           
            ps= connection.prepareStatement(req);
             ps.setDate(1, t.getDateDebut());
  
            ps.setDate(2, t.getDateFin());
            ps.setInt(3, t.getNbPlaces());
            ps.setInt(4, idEspace);
           
            ps.setInt(5, t.getId());
  
            
            
            ps.executeUpdate();
                    } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }
    
    

    @Override
    public List<Reservation> getAll() {
     String requete= "SELECT p.id,p.DateDebut,p.DateFin,p.NbPlaces,e.Nom,p.espace_de_preservation_id FROM espace_de_preservation e , preservation p where espace_de_preservation_id=e.id";
            List <Reservation> list = new ArrayList();
        try {
   
            ste = connection.createStatement();
            rs=ste.executeQuery(requete);
            while (rs.next())
              
             
                list.add(new Reservation(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getInt(6)));
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
/*
   public Integer getAllWithJoin() throws SQLException {
     String requete= "SELECT count(*) as total FROM preservation  ";
            List <Integer> list = new ArrayList();
        try {
   
            ste = connection.createStatement();
            rs=ste.executeQuery(requete);
            
             
              
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  rs.getInt(1); 
    }
  */
    
     public List<Reservation> getReservationForAdmin(Date datePickerDebut, Date datePickerFin) throws SQLException {
       
     String requete= "SELECT p.id,p.DateDebut,p.DateFin,p.NbPlaces,e.Lieu,e.Capacity,e.Nom,p.espace_de_preservation_id FROM espace_de_preservation e , preservation p where espace_de_preservation_id=e.id and DateDebut > '"+datePickerDebut+"' and DateFin < '"+datePickerFin+"' ";
            List <Reservation> list = new ArrayList();
        try {
            
            ste = connection.createStatement();
            rs=ste.executeQuery(requete);
            while (rs.next())
               
                list.add(new Reservation(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8)));
      
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
    
    public List<Reservation> getExpiredReservation() {
        
     String requete= "SELECT p.id,p.DateDebut,p.DateFin,p.NbPlaces,e.Lieu,e.Capacity,e.Nom,p.espace_de_preservation_id FROM espace_de_preservation e , preservation p where espace_de_preservation_id=e.id and dateFin < CURDATE() ";
            List <Reservation> list = new ArrayList();
        try {
            
            ste = connection.createStatement();
            rs=ste.executeQuery(requete);
            while (rs.next())
                list.add(new Reservation(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8)));
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public int nbReservExp() throws SQLException
    {
       
        int nbResExp=0;
      String req = "SELECT count(*) as nb FROM preservation where dateFin < CURDATE()";
        PreparedStatement ste = connection.prepareStatement(req);
       // ste.setInt(1, id);
        ResultSet rs = ste.executeQuery(); 
        while(rs.next()){
         nbResExp=rs.getInt("nb");
        }
    
    return nbResExp;
    
    
}
    
       public int sumReservExp() throws SQLException
    {
       
        int sumResExp=0;
      String req = "SELECT SUM(NbPlaces) as nb FROM preservation where dateFin < CURDATE()";
        PreparedStatement ste = connection.prepareStatement(req);
       // ste.setInt(1, id);
        ResultSet rs = ste.executeQuery(); 
        while(rs.next()){
         sumResExp=rs.getInt("nb");
        }
    
    return sumResExp;
    
    
}
       
           public int nbReservAdmin(Date datePickerDebut, Date datePickerFin) throws SQLException
    {
       
        int nbResAdmin=0;
      String req = "SELECT count(*) as nb FROM preservation where DateDebut > '"+datePickerDebut+"' and DateFin < '"+datePickerFin+"'";
        PreparedStatement ste = connection.prepareStatement(req);
       // ste.setInt(1, id);
        ResultSet rs = ste.executeQuery(); 
        while(rs.next()){
         nbResAdmin=rs.getInt("nb");
        }
    
    return nbResAdmin;
    
    
}
           
            public int sumReservAdmin(Date datePickerDebut, Date datePickerFin) throws SQLException
    {
       
        int sumResAdm=0;
      String req = "SELECT SUM(NbPlaces) as nb FROM preservation where DateDebut > '"+datePickerDebut+"' and DateFin < '"+datePickerFin+"'";
        PreparedStatement ste = connection.prepareStatement(req);
       // ste.setInt(1, id);
        ResultSet rs = ste.executeQuery(); 
        while(rs.next()){
         sumResAdm=rs.getInt("nb");
        }
    
    return sumResAdm;
    
    
}
    
    @Override
    public Reservation getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List <Reservation> forUser (int idUsr)
    {
        String requete= "SELECT p.id,p.DateDebut,p.DateFin,p.NbPlaces,e.Nom,p.espace_de_preservation_id FROM espace_de_preservation e , preservation p where espace_de_preservation_id=e.id and p.user_id='"+idUsr+"'";
            List <Reservation> list = new ArrayList();
        try {
   
            ste = connection.createStatement();
            rs=ste.executeQuery(requete);
            while (rs.next())
              
             
                list.add(new Reservation(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getInt(6)));
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
