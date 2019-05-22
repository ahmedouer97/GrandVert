/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.Preservation;

import com.behindthemachines.grandvert.entity.Espace;
import com.behindthemachines.grandvert.entity.Reservation;
import com.behindthemachines.grandvert.services.EspaceService;
import com.behindthemachines.grandvert.services.ReservationService;
import com.behindthemachines.grandvert.utils.StageManager;
import com.teknikindustries.bulksms.SMS;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author Ghaith
 */
public class GestionReservationController implements Initializable {

    int tmpId = 0;
    int id=0;

    @FXML
    private ComboBox<Espace> combobox;

    @FXML
    private DatePicker dateDebutPicker;
    @FXML
    private DatePicker dateFinPicker;
    @FXML
    private TextField nbPlacesFiled;
    @FXML
    private TableView<Reservation> tableReservation;
    @FXML
    private TableColumn<Reservation, Date> dateDebut;
    @FXML
    private TableColumn<Reservation, Date> dateFin;
    @FXML
    private TableColumn<Reservation, Integer> nbPlaces;
    @FXML
    private TableColumn<Reservation, String> espaceDePreservation;
    @FXML
    private TextField searchBox;

    @FXML
    private AnchorPane comboEspace;
    @FXML
    private Button Annuler;
    @FXML
    private Button reserver;
    @FXML
    private Button modifier;

    StageManager stage = StageManager.getStageManager();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        ReservationService rs = new ReservationService();
        int idUsrCon = stage.getUser().getId();
        ArrayList<Reservation> esp = new ArrayList<Reservation>(rs.forUser(idUsrCon));
        ObservableList obs = FXCollections.observableArrayList(esp);
        tableReservation.setItems(obs);
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        nbPlaces.setCellValueFactory(new PropertyValueFactory<>("NbPlaces"));
        espaceDePreservation.setCellValueFactory(new PropertyValueFactory<>("espace"));
        //dateDebut.setCellFactory(TextFieldTableCell.forTableColumn());
        // dateFin.setCellFactory(TextFieldTableCell.forTableColumn());
        //nbPlaces.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        // espaceDePreservation.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        // tableReservation.setEditable(true);
        
        dateDebutPicker.setValue(LocalDate.now());
        dateFinPicker.setValue(LocalDate.now());

        tableReservation.setOnMouseClicked((MouseEvent e) -> {
            Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();
            tmpId = selectedItem.getId();

            //dateDebutPicker.setValue(selectedItem.getDateDebut());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Date dateDebut = selectedItem.getDateDebut();
            Date dateFin = selectedItem.getDateFin();
            //  LocalDate localDate1 = LocalDate.parse(dateDebut, formatter);
            // LocalDate localDate2 = LocalDate.parse(dateFin, formatter);
            LocalDate date1 = dateDebut.toLocalDate();
            LocalDate date2 = dateFin.toLocalDate();
            dateDebutPicker.setValue(date1);
            dateFinPicker.setValue(date2);
            nbPlacesFiled.setText(selectedItem.getNbPlaces() + "");
            combobox.setValue(selectedItem.getEspace());
            //lieuEspace.setText(selectedItem.getLieu());
        });

        /*   FilteredList<Reservation> filteredData = new FilteredList<>(obs, p -> true);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reservation -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (reservation.getDateDebut().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (reservation.getDateFin().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (reservation.getDateFin().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });
        SortedList<Reservation> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableReservation.comparatorProperty());
        tableReservation.setItems(sortedData);
         */
        EspaceService es = new EspaceService();
        ObservableList<Espace> espaces = FXCollections.observableArrayList(es.getAll());
        combobox.setItems(espaces);
        combobox.setOnAction((event) -> {
             id = combobox.getSelectionModel().getSelectedItem().getId();
            
        });

    }


    public void refreshTableView() {

        ReservationService rs = new ReservationService();
        int idUsrCon = stage.getUser().getId();
        ArrayList<Reservation> esp = new ArrayList<Reservation>(rs.forUser(idUsrCon));
        ObservableList obs = FXCollections.observableArrayList(esp);
        tableReservation.setItems(obs);
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        nbPlaces.setCellValueFactory(new PropertyValueFactory<>("NbPlaces"));
        espaceDePreservation.setCellValueFactory(new PropertyValueFactory<>("espace"));
    }

    public void clearFields() {
        dateDebutPicker.setValue(null);
        dateFinPicker.setValue(null);
        nbPlacesFiled.clear();
        
        
    }
    
     public void sendPlainTextEmail(final String email, final String password,String toAddress , String Title , String Subjet) {
        try{
            String host ="smtp.gmail.com" ;
            String user = email;
            String pass = password;
            String to = toAddress;
            String from = email;
            String subject = Title;
            String messageText = Subjet;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
          //  msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(MessagingException ex)
        {
            System.out.println(ex);
        }

        }

    @FXML
    private void onAnullerRes(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez Vous Vraiment Annuler la Réservation N° " + tableReservation.getSelectionModel().getSelectedItem().getId() + " ?");

        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            ReservationService s = new ReservationService();

            Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();
            tableReservation.getItems().remove(selectedItem);
            s.delete(selectedItem);
            
            
        }
    }
    
    

    


    @FXML
    private void onReserver(ActionEvent event) {

        Reservation r = new Reservation();

        if (dateFinPicker.getValue().isBefore(dateDebutPicker.getValue())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La date de la debut de réservation doit étre supérieure à la date du fin");
            alert.showAndWait();

        }
        
  
        
        else if (combobox.getValue()==null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Séléctionner Un Espace SVP !!");
            alert.showAndWait();

        }
        else if (dateDebutPicker.getValue().isBefore(LocalDate.now())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La date de la debut de réservation doit étre supérieure à la date d'aujourd'hui");
            alert.showAndWait();

        } else if (nbPlacesFiled.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le Nombre de Places Doit étre Rempli");
            alert.showAndWait();
        } else if (Integer.parseInt(nbPlacesFiled.getText()) < 1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le Nombre de Places Doit étre Supériure à 1");
            alert.showAndWait();
        } else {
            java.util.Date dateDebut = java.sql.Date.valueOf(dateDebutPicker.getValue());
            r.setDateDebut((Date) dateDebut);
            java.util.Date dateFin = java.sql.Date.valueOf(dateFinPicker.getValue());
            r.setDateFin((Date) dateFin);
            r.setNbPlaces(Integer.parseInt(nbPlacesFiled.getText()));
            Espace e = (Espace) combobox.getValue();
            if (Integer.parseInt(nbPlacesFiled.getText()) > e.getCapacity()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Vous Avez Dépassé " + e.getCapacity() + " <<La Quantité De L'espace !! >>");
                alert.showAndWait();

            } else {
                int newCapacity = e.getCapacity() - Integer.parseInt(nbPlacesFiled.getText());

                r.setEspace(e);

                ReservationService crud = new ReservationService();
                        System.out.println(stage.getUser());
                        System.out.println(stage.getUser().getId());
                        r.setUser(stage.getUser());
                        
                crud.add(r);
                crud.updateNew(newCapacity, e.getId());
                refreshTableView();
                clearFields();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Votre Réservation est prise en compte");
                
                /*
                
                SMS smsTut = new SMS ();
                String numTel="+21623879127";
                String ContenuSMS = "Une Réservation a été établie";
                String CompteSMS="GhaithBelkhir150";
                String PasswordSMS="gb1996anelka";
                String URLSMS = "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0";
                smsTut.SendSMS(CompteSMS,PasswordSMS,ContenuSMS ,numTel, URLSMS);
                */
                SMS smsTut = new SMS ();
                smsTut.SendSMS("GhaithBelkhir150", "gb1996anelka", "Une Réservation a Ete Etablie", "+21623879127", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0"); 
                
                alert.showAndWait();
                Notifications n = Notifications.create().title("Notification D'information").text("Votre Réservation est prise en compte").hideAfter(Duration.seconds(7)).position(Pos.BOTTOM_RIGHT);
                    n.darkStyle();
                    n.showConfirm();
                   //System.out.println(combobox.getValue());
            }
        }
    }

      private boolean validateNumber () {
Pattern p = Pattern.compile("[0-9]+");
Matcher m = p.matcher(nbPlaces.getText());
  if (m.find() && m.group().equals(nbPlaces.getText()) )
      return true;
  else {    
 return false; }
  }
      
    @FXML
    private void onClickModfier(ActionEvent event) {
        
        Reservation r = new Reservation();
        
         if (dateFinPicker.getValue().isBefore(dateDebutPicker.getValue())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La date de la debut de réservation doit étre supérieure à la date du fin");
            alert.showAndWait();

        }
         
     
         else if (dateDebutPicker.getValue().isBefore(LocalDate.now())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La date de la debut de réservation doit étre supérieure à la date d'aujourd'hui");
            alert.showAndWait();

        } else if (nbPlacesFiled.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le Nombre de Places Doit étre Rempli");
            alert.showAndWait();
        } else if (Integer.parseInt(nbPlacesFiled.getText()) < 1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le Nombre de Places Doit étre Supériure à 1");
            alert.showAndWait();
        }
       
           else {
            java.util.Date dateDebut = java.sql.Date.valueOf(dateDebutPicker.getValue());
            r.setDateDebut((Date) dateDebut);
            java.util.Date dateFin = java.sql.Date.valueOf(dateFinPicker.getValue());
            r.setDateFin((Date) dateFin);
            r.setNbPlaces(Integer.parseInt(nbPlacesFiled.getText()));
            Espace e = (Espace) combobox.getValue();
            if (Integer.parseInt(nbPlacesFiled.getText()) > e.getCapacity()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Vous Avez Dépassé " + e.getCapacity() + " <<La Quantité De L'espace !! >>");
                alert.showAndWait();

            }
            else {
        
        r.setId(tmpId);
         dateDebut = java.sql.Date.valueOf(dateDebutPicker.getValue());
        r.setDateDebut((Date) dateDebut);
         dateFin = java.sql.Date.valueOf(dateFinPicker.getValue());
        r.setDateFin((Date) dateFin);
        r.setNbPlaces(Integer.parseInt(nbPlacesFiled.getText()));
        ReservationService crud = new ReservationService();
    
        crud.updateEsp(r,id);
         
        clearFields();
        refreshTableView();
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(null);
        alert.setContentText("La Réservation Numéro '" + tmpId + "' a été modifié");

        alert.showAndWait();

    }}}
    

    @FXML
    private void searchRecord(KeyEvent event) {
        ReservationService rs = new ReservationService();
        int idUsrCon = stage.getUser().getId();
        ArrayList<Reservation> esp = new ArrayList<Reservation>(rs.forUser(idUsrCon));
        ObservableList obs = FXCollections.observableArrayList(esp);
        tableReservation.setItems(obs);
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        nbPlaces.setCellValueFactory(new PropertyValueFactory<>("NbPlaces"));
        espaceDePreservation.setCellValueFactory(new PropertyValueFactory<>("espace"));

        FilteredList<Reservation> filteredData = new FilteredList<>(obs, p -> true);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reservation -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String strDateDebut = dateFormat.format(reservation.getDateDebut());
                String strDateFin = dateFormat.format(reservation.getDateFin());
                String nbPlaceStr = String.valueOf(reservation.getNbPlaces());
                if (strDateDebut.toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (strDateFin.toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } 
                else if (nbPlaceStr.toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }else if (reservation.getEspace().getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }

                return false; 
            });
        });
        SortedList<Reservation> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableReservation.comparatorProperty());
        tableReservation.setItems(sortedData);

    }

}
