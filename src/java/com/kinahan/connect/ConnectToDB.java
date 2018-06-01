 
package com.kinahan.connect;

import com.kinahan.encryption.SecurityUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
 

/*
 created by JKinahan
 */
public class ConnectToDB extends HttpServlet {
        private static Connection conn = null;
        private static final String DB_URL = "jdbc:mysql://localhost:3306/psaviour?autoReconnect=true&useSSL=false";
        private static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
        private static final String USER = "root";
        private static final String PASS = "root";

    public ConnectToDB() {
    
}    
    
        
        
    public Connection connect(){
        
        try {
            Class.forName(JDBC_DRIVER);
            try {
                conn=DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected");
            }
            catch (SQLException se){
                System.out.println("Couldn't connect to D/B....");
            }
        } catch (ClassNotFoundException ce){
            System.out.println("Driver not found");
        }
        
       return conn; 
    }
    
    public boolean enterVault(String key) throws SQLException {
    

        PreparedStatement stmt;
        ResultSet rs;
          boolean granted = false;

        try{
            ConnectToDB connectA = new ConnectToDB(); 
            conn= connectA.connect();
            stmt=conn.prepareStatement("SELECT * FROM users WHERE vaultkey = '"+ key + "'");
            rs = stmt.executeQuery();              
            if (rs.next()){
                  granted=true; 
              }
                        
        } catch (SQLException es) {
            System.out.println("No matching record...");
        } 
        if(granted){
            
        }
        return granted;
}
    
    public boolean isValid(String user, String pass) throws IOException 
      
    { 
        PreparedStatement pstmt;
        ResultSet rs;
        boolean validIf= false;
        try{
            ConnectToDB connectA = new ConnectToDB(); 
            conn= connectA.connect();
            pstmt=conn.prepareStatement("SELECT * FROM users WHERE username = '" 
                      + user+"' AND password = '"+ pass + "'");
              rs = pstmt.executeQuery();              
        if (rs.next()){
            validIf = true;
        } else {
            return validIf;
}
                 
          } catch (SQLException e){
              e.printStackTrace();
          }
            return validIf;
} 
    

    public void populateUserDatabase(String user, String password, 
            String email, String perskey, String hashKey)  
            throws NoSuchAlgorithmException{
        //Connection conn;            
        PreparedStatement pstmt; 
        ResultSet rs;
        try{   
            ConnectToDB connectA = new ConnectToDB(); 
            conn= connectA.connect();
            pstmt = conn.prepareStatement("INSERT INTO users (username, password, "
                      + " personalKey, email, secretKeyHash) VALUES (?,?,?,?,?)");
            
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            pstmt.setString(3, perskey);
            pstmt.setString(4, email);
            pstmt.setString(5, hashKey);
       
        int i = pstmt.executeUpdate();
             if(i==1){
              System.out.println("Successful update to DB");
             }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Couldn't process data jim.....");
            }

    }
    
    public void readFromVault(String key, String user ){
        PreparedStatement pstmt;
        Statement stmt= null;
        ResultSet rs = null;
        try {
            ConnectToDB connectA = new ConnectToDB(); 
            conn= connectA.connect();
            SecurityUtil util = new SecurityUtil();
            
            
            String query = "SELECT * FROM encrypted_data WHERE userid = '"+user+"'";
            
            
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }
    
    public void enterRowInDB(int id, String key, String app, String username, String password)
    throws Exception{
            PreparedStatement pstmt;
            ConnectToDB connectA = new ConnectToDB(); 
            conn= connectA.connect();
            SecurityUtil sc = new SecurityUtil();
            try {
                String encSite = sc.encrypt(app, key);
                String encUsername = sc.encrypt(username, key);
                String encPassW = sc.encrypt(password, key);
                
            pstmt = conn.prepareStatement("INSERT INTO encrypted_data (userid, siteApp, username,"
                        + " password)  VALUES (?,?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, encSite);
            pstmt.setString(3, encUsername);
            pstmt.setString(4, encPassW);
                
            int i = pstmt.executeUpdate();   
                 if(i==1){
                 System.out.println("Successful update to DB");
                   }
                
            } catch (SQLException ex) {
                Logger.getLogger(ConnectToDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public int getUserId(String user) throws SQLException{
        Statement stmt;
        ResultSet rs;
        ConnectToDB connectB= new ConnectToDB();
        conn= connectB.connect();
        int id=0;
        try {

            stmt = conn.createStatement();
            String query = "SELECT ID FROM users WHERE username = '"+user+"'";
            rs = stmt.executeQuery(query);

            if(rs.next()){
                id=rs.getInt(1);
            }

            return id;
        } catch (Exception ex){
            ex.printStackTrace();
            return id;
        }
        
    }
    
    public boolean ifPhraseLegit(String user, String phrase) throws SQLException {
        Statement stmt;
        ResultSet rs;
        ConnectToDB connectC= new ConnectToDB();
        conn= connectC.connect();
        boolean legit= false;
        try{
            stmt = conn.createStatement();
            String query = "SELECT personalKey FROM users WHERE username = '"+user+"'";
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                String pkey = rs.getString("personalKey");
                if(pkey.equals(phrase)){
                    legit=true;
                }
            }
            conn.close();
            return legit;
            
        }catch (Exception e){
            e.printStackTrace();
            conn.close();
            return legit;
        }
    }
    
    public boolean doHashesMatch(String hashA, String user ) 
            throws SQLException{
        Statement stmt;
        ResultSet rs;
        ConnectToDB connectC= new ConnectToDB();
        conn= connectC.connect();
        boolean match= false;
        try{
            
            stmt = conn.createStatement();
            String query = "SELECT secretKeyHash FROM "
                    + "users WHERE username = '"+user+"'";
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                String userHash = rs.getString("secretKeyHash");
                if(userHash.equals(hashA)){
                    match=true;
                }
            }
            conn.close();
            return match;
            
        }catch (SQLException se){
            se.printStackTrace();
            conn.close();
            return match;
        }
    } 
        
      
    
}   

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    








































//    public static void main (String[] args){
//        //ConnectToDB.connect();
//        Connection conn;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try{
//              conn = ConnectToDB.connect();
//              stmt=conn.prepareStatement("SELECT vaultKey FROM users WHERE username = 'Patrick'"); 
//              rs = stmt.executeQuery();              
//              if (rs.()){
//                  System.out.println(rs.next());
//                  
//              }
//                      }catch (SQLException ex){
//                      ex.printStackTrace();
//    }
//}
