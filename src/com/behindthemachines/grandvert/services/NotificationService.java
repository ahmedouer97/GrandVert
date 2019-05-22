package com.behindthemachines.grandvert.services;


import com.behindthemachines.grandvert.entity.Notification;
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
public class NotificationService implements IService<Notification>{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;


    public NotificationService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }
    
    @Override
    public void add(Notification n) {
        String req="insert into notification(id , user_id , title , description , notification_date , route , seen ) values (?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,n.getId());
            pst.setInt(2,n.getId_user());
            pst.setString(3, n.getTitle());
            pst.setString(4, n.getDescription());
            pst.setDate(5, new java.sql.Date(n.getNotification_date().getTime()));
            pst.setString(6, n.getDescription());
            pst.setInt(7, n.getSeen());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Notification s) {

    }

    @Override
    public void update(Notification n) {
        String req="update notification set seen=? where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,n.getSeen());
            pst.setInt(2,n.getId());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificationService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public List<Notification> getAll() {
        List<Notification> List = new ArrayList<>();
        
        return List;
    }
    
    public List<Notification> getAll(int id_user) {
        List<Notification> List = new ArrayList<>();

        try {
            String req = "select * from notification where user_id ="+id_user+" and seen = 0";
            
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            
            while (result.next()){
                List.add(new Notification(result.getInt("id"), result.getInt("user_id"), result.getString("title") , result.getString("description"),result.getDate("notification_date") ,result.getString("route"),result.getInt("seen") ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }

    @Override
    public Notification getById(int id) {
        try {
            String req = "select * from notification where id = "+id;
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            
            while (result.next()){
                return (new Notification(result.getInt("id"), result.getInt("user_id"), result.getString("title") , result.getString("description"),result.getDate("notification_date") ,result.getString("route"),result.getInt("seen") ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
}
