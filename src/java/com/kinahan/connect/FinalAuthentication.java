package com.kinahan.connect;

import com.kinahan.encryption.HashKey;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 created by JKinahan
 */
@WebServlet(name = "showData", urlPatterns = {"/showData"})
public class FinalAuthentication extends HttpServlet  {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException   {
        try{
         
        String user = (String)request.getSession().getAttribute("username");
        String secretKey = request.getParameter("key");
            try{
                HashKey hk = new HashKey();
                String hashedKey= hk.HashIt(secretKey);
                ConnectToDB conA = new ConnectToDB();
                    if(conA.doHashesMatch(hashedKey, user)){

                        int userId = Integer.parseInt(request.getParameter("userId"));
                        request.setAttribute("userId", userId);// to send to inside for db stuff
                        request.setAttribute("secretKey", secretKey); // also for db stuff on inside

                        request.getRequestDispatcher("inside.jsp").forward(request, response); 

                    }
                    else{
                         request.setAttribute("error", "true");
                         request.getRequestDispatcher("openVault.jsp").forward(request, response);
                    }

            }catch (NoSuchAlgorithmException nsa){
                nsa.printStackTrace();
            }
        } 
        catch(SQLException  ex){
            ex.printStackTrace();
        }

    }
}
