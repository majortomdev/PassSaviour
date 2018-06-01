package com.kinahan.encryption;

import com.kinahan.connect.ConnectToDB;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
created by jkinahan
 */
@WebServlet(name = "EncryptIt", urlPatterns = {"/EncryptIt"})
public class EncryptIt extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
           
            int userId = Integer.parseInt(request.getParameter("userId"));
            
            String secretKey=(String)request.getParameter("secretKey");
            String siteName=(String)request.getParameter("appSite"); 
            String appUserName=(String)request.getParameter("appUserName");
            String appPassword=(String)request.getParameter("appPassword");
            try{
                ConnectToDB connectA = new ConnectToDB();
                final String username= (String)request.getSession().getAttribute("username");
                HashKey hk= new HashKey();
                String userSecretKeyHashed= hk.HashIt(secretKey);
                //boolean hashOut=false;
                //String hashOut;
                try{
                    if(connectA.doHashesMatch(userSecretKeyHashed, username)){
                        try{
                        connectA.enterRowInDB(userId, secretKey, siteName, appUserName, appPassword);
                        response.sendRedirect("dataSaved.jsp");
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                                }
                    else {   
                         
                        request.setAttribute("errorMessage", "Sorry, this appears to be a different key");
                        request.setAttribute("appSiteName", siteName);
                        request.setAttribute("appUserName", appUserName);
                        request.setAttribute("appPassword", appPassword);
                        request.setAttribute("hashOut", "true");
                        request.getRequestDispatcher("encrypt.jsp?userId="+userId+"").forward(request, response);
                    }
                } catch(SQLException ex){
                            ex.printStackTrace();
                            }
              } 
            catch(NoSuchAlgorithmException exe){
                exe.printStackTrace();
            }
                 }  
    }
