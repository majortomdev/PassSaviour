package com.kinahan.connect;

import com.kinahan.encryption.HashKey;
import com.kinahan.encryption.SecurityUtil;
import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 created by JKinahan
 */
@WebServlet(name = "CreateNewUser", urlPatterns = {"/CreateNewUser"})
public class CreateNewUser extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
                String userName=request.getParameter("username"); 
                String password=request.getParameter("passwordA");
                String email=request.getParameter("email");
                String perskey=request.getParameter("perskey");
                HttpSession session = request.getSession();
                session.setAttribute("username", userName);
                ConnectToDB connectA= new ConnectToDB();
            
            if(StringUtils.isNullOrEmpty(userName)|| StringUtils.isNullOrEmpty(password)
                || StringUtils.isNullOrEmpty(email) || StringUtils.isNullOrEmpty(perskey))  {
                System.out.println("oooooopssss a daisy");
                response.sendRedirect("signup.jsp");
            }
            else{
                try {
                    SecurityUtil sec = new SecurityUtil();
                    String usersKey = sec.generateKey();
                    HashKey hashKey = new HashKey();
                    String hash= hashKey.HashIt(usersKey);
                    session.setAttribute("SecretKey", usersKey);
                    request.setAttribute("SecretKey", usersKey);
                    connectA.populateUserDatabase(userName, password, email, perskey, hash);
                            
                    response.sendRedirect("keyGenAfter.jsp");
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(CreateNewUser.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("userlogin.jsp");
                }
            }
            
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet verifyLogin</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet verifyLogin at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

//}
}