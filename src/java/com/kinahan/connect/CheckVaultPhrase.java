package com.kinahan.connect;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    created by JKinahan 
 */
public class CheckVaultPhrase extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String vaultPhrase = request.getParameter("vaultphrase");
        int userId = Integer.parseInt(request.getParameter("UserId"));
        
        String username= (String)request.getSession().getAttribute("username");
        System.out.println("user id= "+userId);
        
        try{
        ConnectToDB connect = new ConnectToDB();
        if(connect.ifPhraseLegit(username, vaultPhrase)){    
            request.setAttribute("userId", userId);
            request.getRequestDispatcher("openVault.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("home.jsp?hashError=true");
        }
        
        } catch (SQLException se){
            se.printStackTrace();
        }
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    } 

}
