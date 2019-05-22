/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.Note;
import com.behindthemachines.grandvert.utils.WorkshopConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author psn
 */
public class NoteService implements IService<Note> {
    
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;
    
    public NoteService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }

    @Override
    public void add(Note t) {
        String req = "INSERT INTO note(jardin_id, contenu, date_note) "
                +"VALUES (?,?,?)";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,t.getJardinId());
            pst.setString(2, t.getContenu());
            pst.setDate(3, t.getDateN());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Note t) {
        String req="DELETE FROM note WHERE id = ?";
        try {
           pst=connection.prepareStatement(req);
             pst.setInt(1, t.getId());
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Note t) {
        String req = "UPDATE note SET contenu=? where id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, t.getContenu());
            pst.setInt(2, t.getId());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Note> getAll() {
        List<Note> list = new ArrayList<>();
        String req = "SELECT * from note";
        
        try {
            pst=connection.prepareStatement(req);
            result=pst.executeQuery();
            while(result.next()){
                Note note = new Note();
                note.setId(result.getInt("id"));
                note.setJardinId(result.getInt("jardin_id"));
                note.setContenu(result.getString("contenu"));
                note.setDateN(result.getDate("date_note"));
                list.add(note);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }

    @Override
    public Note getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Note> getAllByJardin(int jardinId){
        List<Note> list = new ArrayList<>();
        String req = "SELECT * from note where jardin_id="+jardinId;
        
        try {
            pst=connection.prepareStatement(req);
            result=pst.executeQuery();
            while(result.next()){
                Note note = new Note();
                note.setId(result.getInt("id"));
                note.setJardinId(result.getInt("jardin_id"));
                note.setContenu(result.getString("contenu"));
                note.setDateN(result.getDate("date_note"));
                list.add(note);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    public List<Note> getByJardinAndDate(int jardinId, Date dateN){
        List<Note> list = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(dateN);  
        System.out.println("NOTE SERVICE DATE; "+date);  
        String req = "SELECT * from note where jardin_id=? AND date_note=?";
        
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, jardinId);
            pst.setDate(2, dateN);
            result=pst.executeQuery();
            while(result.next()){
                Note note = new Note();
                note.setId(result.getInt("id"));
                note.setJardinId(result.getInt("jardin_id"));
                note.setContenu(result.getString("contenu"));
                note.setDateN(result.getDate("date_note"));
                list.add(note);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
}
