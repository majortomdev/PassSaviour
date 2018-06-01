package com.kinahan.connect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
    created by JKinahan
 */
@WebServlet(name = "VerifyLogin", urlPatterns = {"/VerifyLogin"})
public class VerifyLogin extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            
            String userName=request.getParameter("username"); 
            String password=request.getParameter("passwordA");
            ConnectToDB connectA = new ConnectToDB();
            if(!connectA.isValid(userName, password)){
                
                request.setAttribute("errorMessage", "Invalid user or password");
                request.getRequestDispatcher("/userlogin.jsp").forward(request, response);
                   
            } else {
                
               HttpSession session = request.getSession();
               session.setAttribute("username", userName);
                                
                response.sendRedirect("home.jsp");
            } 
        
    }
    
    
}


