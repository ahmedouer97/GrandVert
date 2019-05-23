package com.behindthemachines.grandvert.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ROCH
 */
public class WorkshopConnexion {

    private String Url="jdbc:mysql://localhost:3306/garden";
    private String Login="root";
    private String Password="";
    
    private Connection cnx; 
    private static WorkshopConnexion Instance;
    
    private WorkshopConnexion(){
        try {
            cnx = DriverManager.getConnection(Url, Login, Password);
            System.out.println("Connextion Etablie");
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static WorkshopConnexion getInstance(){
        if(Instance == null)
            Instance = new WorkshopConnexion();
        
        return Instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    
}
