/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;

import com.behindthemachines.grandvert.entity.User;
import com.behindthemachines.grandvert.utils.WorkshopConnexion;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author psn
 */
public class UserService implements IService<User> {
    
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet result;
    
    public UserService() {
        connection = WorkshopConnexion.getInstance().getCnx();
    }

    
    public int addUser(User t) {
        String res="";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost/GrandVert/web/app_dev.php/api/registerApi");
        
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(4);
        params.add(new BasicNameValuePair("username", t.getUsername()));
        params.add(new BasicNameValuePair("password", t.getPassword()));
         params.add(new BasicNameValuePair("email", t.getEmail()));
        params.add(new BasicNameValuePair("nom", t.getNom()));
        params.add(new BasicNameValuePair("prenom", t.getPrenom()));
        
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            
            res = EntityUtils.toString(entity, "UTF-8");
            System.out.println("registerApi result: "+res);
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (res.length()<3){
            return Integer.parseInt(res);
        }
        return 0;
        
        
    }

    @Override
    public void delete(User t) {
        String req="update fos_user set enabled=? where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            if(t.getEnabled() == 0)
                pst.setInt(1, 1);
            else
                pst.setInt(1, 0);
            pst.setInt(2, t.getId());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User t) {
        String req="update fos_user set nom=?, prenom=?, tel=?, avatar=? where id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setInt(3, t.getTel());
            pst.setString(4, t.getAvatar());
            pst.setInt(5, t.getId());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String req = "select * from fos_user";
        
        try {
            pst=connection.prepareStatement(req);
            result=pst.executeQuery();
            while(result.next()){
                list.add(new User(
                            result.getInt("id"),result.getString("username"),result.getString("email"),result.getInt("enabled")
                        ,result.getDate("last_login"),result.getString("roles"),result.getString("nom")
                        ,result.getString("prenom"),result.getInt("tel"),result.getInt("level"),
                        result.getInt("score"),result.getString("avatar"),result.getString("adresse")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User getById(int id) {
        String req = "select * from fos_user where id="+id;
        
        try {
            pst=connection.prepareStatement(req);
            result=pst.executeQuery();
            while(result.next()){
                return (new User(
                            result.getInt("id"),result.getString("username"),result.getString("email"),result.getInt("enabled")
                        ,result.getDate("last_login"),result.getString("roles"),result.getString("nom")
                        ,result.getString("prenom"),result.getInt("tel"),result.getInt("level"),
                        result.getInt("score"),result.getString("avatar"),result.getString("adresse")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public User checkLogin(String username, String password){
        String res="";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost/GrandVert/web/app_dev.php/api/checkpassword");
        
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            
            res = EntityUtils.toString(entity, "UTF-8");
            System.out.println("checkPassword result: "+res);
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (res.equals("FALSE")) {
            return null;
        }
        
        return this.getById(Integer.parseInt(res));
        
    }
    
    public void changerRole(User u) {
        
        String req="update fos_user set roles = ? where id = ? ";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1,u.getRoles());
            pst.setInt(2,u.getId());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public List<User> getAllModerateur() {
        List<User> List = new ArrayList<>();
        try {
            String req = "select * from fos_user where roles LIKE '%a:1:{i:0;s:10:\"ROLE_ADMIN\";}%'";
            
            
            ste = connection.createStatement();
            result = ste.executeQuery(req);
            
            while (result.next()){
                List.add(new User(
                            result.getInt("id"),result.getString("username"),result.getString("email"),result.getInt("enabled")
                        ,result.getDate("last_login"),result.getString("roles"),result.getString("nom")
                        ,result.getString("prenom"),result.getInt("tel"),result.getInt("level"),
                        result.getInt("score"),result.getString("avatar"),result.getString("adresse")
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
    }

    @Override
    public void add(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
