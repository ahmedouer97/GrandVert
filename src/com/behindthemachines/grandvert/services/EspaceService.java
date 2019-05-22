/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;


import com.behindthemachines.grandvert.entity.Espace;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ghaith
 */
public class EspaceService implements IService <Espace> {
    
    private Connection connection;
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;
    public EspaceService() {
        connection=WorkshopConnexion.getInstance().getCnx();
    }
 
    /* public List<Espace> getEspaceParent() throws SQLException {
        ArrayList<Espace> abc = new ArrayList<>();
        String req = "select * from espace_de_preservation";
        rs = ste.executeQuery(req);
        while (rs.next()) {
            int idEspace = rs.getInt("id");
            
            
            String nomEspace = rs.getString("Nom");
        
            abc.add(new Espace(idEspace, nomEspace));
        }
        return abc;
    }
*/
    @Override
    public void add(Espace t) {
           
         String req = "insert into espace_de_preservation (Nom,Capacity,Lieu) values ('"+t.getNom()+"','"+t.getCapacity()+"','"+t.getLieu()+"')";
        try {
           
            ste=connection.createStatement();
            ste.executeUpdate(req);
                    } catch (SQLException ex) {
            Logger.getLogger(EspaceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Espace t) {
        
        
          String requete = "delete from espace_de_preservation where id = '"+t.getId()+"' ";
        try {
            ps = connection.prepareStatement(requete);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EspaceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update(Espace t) {
       
       
           String req = "update espace_de_preservation set Nom = ? , Capacity= ? , Lieu = ? where id = ?";
        try {
            ps= connection.prepareStatement(req);
            
            ps.setString(1, t.getNom());
            ps.setInt(2, t.getCapacity());
            ps.setString(3, t.getLieu());
            ps.setInt(4, t.getId());
            ps.executeUpdate();
                    } catch (SQLException ex) {
            Logger.getLogger(EspaceService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @Override
    public List<Espace> getAll() {
        
          String requete= "select * from espace_de_preservation";
            List <Espace> list = new ArrayList();
        try {
            
            ste = connection.createStatement();
            rs=ste.executeQuery(requete);
            while (rs.next())
                list.add(new Espace(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
        } catch (SQLException ex) {
            Logger.getLogger(EspaceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
    }

    public void exportExcel () throws FileNotFoundException, IOException {
          try {

                String query = "Select * from espace_de_preservation";

                ps = connection.prepareStatement(query);

                rs = ps.executeQuery();

               

                //Apache POI Jar Link-

                //http://a.mbbsindia.com/poi/release/bin/poi-bin-3.13-20150929.zip

                XSSFWorkbook wb = new XSSFWorkbook();//for earlier version use HSSF

                XSSFSheet sheet = wb.createSheet("Espace Details");

                XSSFRow header = sheet.createRow(0);

                header.createCell(0).setCellValue("Nom");

                header.createCell(1).setCellValue("Capacity");

                header.createCell(2).setCellValue("Lieu");

             //   header.createCell(3).setCellValue("Email");

               

                sheet.autoSizeColumn(1);

                sheet.autoSizeColumn(2);

                sheet.setColumnWidth(3, 256*25);//256-character width

               

                sheet.setZoom(150);//scale-150%

               

               

                int index = 1;

                while(rs.next()){

                    XSSFRow row = sheet.createRow(index);

                    row.createCell(0).setCellValue(rs.getString("Nom"));

                    row.createCell(1).setCellValue(rs.getString("Capacity"));

                    row.createCell(2).setCellValue(rs.getString("Lieu"));

                //    row.createCell(3).setCellValue(rs.getString("Nom"));

                    index++;                   

                }

               

                FileOutputStream fileOut = new FileOutputStream("EspaceDetails.xlsx");// before 2007 version xls

                wb.write(fileOut);

                fileOut.close();

               

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("Espace Details Exported in Excel Sheet.");

                alert.showAndWait();

               

                ps.close();

                rs.close();

               

            } catch (SQLException | FileNotFoundException ex) {

                Logger.getLogger(EspaceService.class.getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {

                Logger.getLogger(EspaceService.class.getName()).log(Level.SEVERE, null, ex);

            }

           

    };
    
    public void loadData(ObservableList<PieChart.Data> pieChartData) throws SQLException {
        
       
         String requete= "select * from espace_de_preservation";
          ste = connection.createStatement();
            rs=ste.executeQuery(requete);
            while (rs.next()) {
            
               pieChartData.add(new PieChart.Data(rs.getString("Nom"),rs.getInt("Capacity")));
        } 
         
    }
    
      public int nbEspaceVide() throws SQLException
    {
       
        int nb=0;
      String req = "SELECT count(*) as nb FROM espace_de_preservation where Capacity=0 ";
        PreparedStatement ste = connection.prepareStatement(req);
       // ste.setInt(1, id);
        ResultSet rs = ste.executeQuery(); 
        while(rs.next()){
         nb=rs.getInt("nb");
        }
    
    return nb;
    
    
}

    
    
    @Override
    public Espace getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
