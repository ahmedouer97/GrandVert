package com.behindthemachines.grandvert.Preservation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Ghaith
 */
public class VideoController implements Initializable {

    @FXML
    private Button btnRetour;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnStop;
    @FXML
    private MediaView mv;

    MediaPlayer mediaplayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        String VUrl = "file:/C:/Users/ahmed/Documents/NetBeansProjects/GrandVert-Java/manuelle.mp4";
        Media media = new Media(VUrl);
        mediaplayer = new MediaPlayer(media);
        mv.setMediaPlayer(mediaplayer);

    }

    @FXML
    private void onClickRetour(ActionEvent event) {
    }

    @FXML
    private void onClickPlay(ActionEvent event) {

        if (mediaplayer.getStatus().equals(Status.PLAYING)) {
            mediaplayer.stop();
            mediaplayer.play();
        } else {
            mediaplayer.play();
        }
    }

    @FXML
    private void onClickStop(ActionEvent event) {

        mediaplayer.stop();
    }

}
