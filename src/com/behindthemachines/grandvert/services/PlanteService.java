package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Plante;
import com.behindthemachines.grandvert.utils.StageManager;
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
public class PlanteService implements IService<Plante>{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;


    public PlanteService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }

    @Override
    public void add(Plante t) {
       String req="INSERT INTO plante(nom,description,stock,prix,hauteur,fertiliseur,categorie,season,proposition,photo,user_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
     try {
         pst=connection.prepareStatement(req);
         pst.setString(1, t.getNom());
         pst.setString(2, t.getDescription());
         pst.setInt(3, t.getStock());
         pst.setFloat(4, t.getPrix());
         pst.setInt(5, t.getHauteur());
         pst.setString(6, t.getFertiliseur());
         pst.setString(7, t.getCategorie());
         pst.setString(8, t.getSeason());
         pst.setInt(9, t.getProposition());
         pst.setString(10, t.getPhoto());
         pst.setInt(11, StageManager.getStageManager().getUser().getId());
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(PlanteService.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @Override
    public void delete(Plante t) {
     String req="DELETE FROM plante WHERE id = ?";
        try {
           pst=connection.prepareStatement(req);
             pst.setInt(1, t.getId());
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Plante t) {
      String req="UPDATE plante SET nom= ?,description= ?,stock=?,prix=?,hauteur=?,fertiliseur=?,categorie=?,season=?,proposition=?,photo=? WHERE id=?";
     try {
         pst=connection.prepareStatement(req);
         pst.setString(1, t.getNom());
         pst.setString(2, t.getDescription());
         pst.setInt(3, t.getStock());
         pst.setFloat(4, t.getPrix());
         pst.setInt(5, t.getHauteur());
         pst.setString(6, t.getFertiliseur());
         pst.setString(7, t.getCategorie());
         pst.setString(8, t.getSeason());
         pst.setInt(9, t.getProposition());
         pst.setString(10, t.getPhoto());
            pst.setInt(11, t.getId());
          pst.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(PlanteService.class.getName()).log(Level.SEVERE, null, ex);
     }   
    }

    @Override
    public List<Plante> getAll() {
        String req="SELECT * FROM plante";
         List<Plante>list =new ArrayList<Plante>();
         UserService us=new UserService();
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next()){
              list.add(new Plante(rs.getInt("id"),rs.getString("photo") ,rs.getString("nom"), rs.getString("description"),rs.getInt("stock"), rs.getFloat("prix"), rs.getInt("hauteur"), rs.getString("fertiliseur"), rs.getString("categorie"), rs.getString("season"),rs.getInt("proposition"),us.getById(rs.getInt("user_id"))));
                    }
        } catch (SQLException ex) {
            Logger.getLogger(PlanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Plante> getByCat(String categorie) {
        List<Plante> List = new ArrayList<>();
UserService us=new UserService();
        String req="select * from plante where categorie = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1,categorie);
            
            rs = pst.executeQuery();
            
            while (rs.next()){
                List.add(new Plante(rs.getInt("id"),rs.getString("photo") ,rs.getString("nom"), rs.getString("description"),rs.getInt("stock"), rs.getFloat("prix"), rs.getInt("hauteur"), rs.getString("fertiliseur"), rs.getString("categorie"), rs.getString("season"),rs.getInt("proposition"),us.getById(rs.getInt("user_id"))));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PlanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }
public List<Plante> getBynom(String nom) {
        List<Plante> List = new ArrayList<>();
UserService us=new UserService();
        String req="select * from Plante where nom LIKE ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1,nom+"%");
            
            rs = pst.executeQuery();
            
            while (rs.next()){
List.add(new Plante(rs.getInt("id"),rs.getString("photo") ,rs.getString("nom"), rs.getString("description"),rs.getInt("stock"), rs.getFloat("prix"), rs.getInt("hauteur"), rs.getString("fertiliseur"), rs.getString("categorie"), rs.getString("season"),rs.getInt("proposition"),us.getById(rs.getInt("user_id"))));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PlanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }
    @Override
    public Plante getById(int id) {
         Plante p=null;
   String req="SELECT * FROM plante WHERE id =?";
   UserService us=new UserService();
         List<Plante>list =new ArrayList<Plante>();
        try {
            pst=connection.prepareStatement(req);
             pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next()){
           p=new Plante(rs.getInt("id"),rs.getString("photo") ,rs.getString("nom"), rs.getString("description"),rs.getInt("stock"), rs.getFloat("prix"), rs.getInt("hauteur"), rs.getString("fertiliseur"), rs.getString("categorie"), rs.getString("season"),rs.getInt("user_id"),us.getById(rs.getInt("user_id")));
                    }
        } catch (SQLException ex) {
            Logger.getLogger(PlanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    
}
