/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Jardin;

/**
 *
 * @author psn
 */
import com.behindthemachines.grandvert.entity.Note;
import com.behindthemachines.grandvert.services.NoteService;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AddNoteController implements Initializable{

    @FXML
    private JFXTextArea noteText;
    
    private int jardinId=1;
    private String date;
    
    NoteService noteService = new NoteService();
    
    public AddNoteController(int jardinId, String date){
        this.jardinId=jardinId;
        this.date=date;
    }

    public AddNoteController() {
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    @FXML
    void addNote(ActionEvent event) throws ParseException {
           Note note = new Note();
           note.setContenu(noteText.getText());
           note.setJardinId(jardinId);
           java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
           java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           note.setDateN(sqlDate);
           noteService.add(note);
    }

    @FXML
    void closeWindow(ActionEvent event) {

    }

}

