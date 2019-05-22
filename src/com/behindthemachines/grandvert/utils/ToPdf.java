/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class ToPdf {
       public static  void pdf(int id,String nom,int contite,Float prix,Float total,Timestamp date,String nomu){
    Document document= new Document();
    try{
        PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("C:\\pdf\\"+id+".pdf"));
        document.open();
        document.add(new Paragraph("pdf commande"));
        List orderlis=new List(List.UNORDERED);
        orderlis.add(new ListItem("nom de la plante: "+nom));
          orderlis.add(new ListItem("contité: "+contite));
          orderlis.add(new ListItem("prix: "+prix));
            orderlis.add(new ListItem("total: "+total));
              orderlis.add(new ListItem("date d'envoie: "+date));
                  orderlis.add(new ListItem("l'acheteur: "+nomu));
              document.add(orderlis);
        document.close();
        writer.close();
        
    
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(ToPdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ToPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
