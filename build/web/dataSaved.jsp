<%-- 
    Document   : home
    Created on : Jul 30, 2017, 11:57:50 PM
    Author     : jk
--%>
 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.kinahan.connect.VerifyLogin" %>
<%@page import="com.kinahan.encryption.SecurityUtil" %>
<%@page import="com.kinahan.encryption.EncryptIt" %>
<%@page import="com.kinahan.connect.ConnectToDB" %>
 

   <% final String username= (String)request.getSession().getAttribute("username");
       ConnectToDB dbconn = new ConnectToDB();
       int UserId = dbconn.getUserId(username);
      String keyPhrase = request.getParameter("vaultphrase");
            if(dbconn.ifPhraseLegit(username, keyPhrase)){
                response.sendRedirect("openVault.jsp");
                }

    %>   
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="pass.css" rel="stylesheet" type="text/css">     
      
        <title>PassSaviour</title>
      

        
    </head>

    <body>
        
        <h1>Successful data deposit, <%= username %> </h1>
             </h1> 
             
        <div class="userProfile">    
        <h2>Your sensitive data is firmly locked, and you have the only key.</h2>
        <br><br><br><br><br>
 
        <p>You can click the link to return to the home page: <a href="home.jsp">Home</a><p>
        <p>Click this link to securely log out of your session: <a href="userlogin.jsp">Log out</a> <p>

        
        </div>
     
         
    </body>
</html>
