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
 


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="pass.css" rel="stylesheet" type="text/css">     
      
        <title>PassSaviour Welcome Page</title>
      

        
    </head>

    <body>
        
    <% final String username= (String)request.getSession().getAttribute("username");
        
        
       ConnectToDB dbconn = new ConnectToDB();
       int UserId = dbconn.getUserId(username);
       request.getSession().setAttribute("userId", UserId);
       
       String hashError = request.getParameter("hashError");
       


    %>   
        
        <h1>Welcome into the PassSaviour secure lobby, <%= username %> </h1>
             </h1> 
             
        <div class="userProfile">    
        <h2>From here you can enter details for a new online account and store them in your vault</h2>
        
 
        <form action="encrypt.jsp" method="post">
                    <label for="appSiteName">WebSite/ Application Name: <label> 
                    <input TYPE = "Text" Size="50" Value = "" Name = "appSiteName" Maxlength="45"><br><br>
                    
                    <label for="appUserName">Username: </label> 
                    <input TYPE = "Text" Size="50" Value = "" Name = "appUserName" Maxlength="45"><br><br>
                    
                   
                    <input TYPE = "hidden" Value = "<%= UserId %>" Name = "userId" > 
                    
                    <label for="appPassword">Password: </label>
                    <input TYPE = "password" Size="50" Value = "" Name = "appPassword" Maxlength="45"><br><br>
                    <input type="submit" value="Encrypt and send to Vault">
        </form> 
        </div>
        <br><br><br>
        <div class="key">
        <h3>To enter your vault to retrieve your data </h3>
            <form action="CheckVaultPhrase" method="post">
                <label for="vaultphrase">Enter your personal context password here</label>
                <input type="text" Size="100" value="" name="vaultphrase"><br><br>
                <input TYPE = "hidden" Value = "<%= UserId %>" Name = "UserId" > <br>
                <input type="submit" value="Submit passphrase to enter your PassSaviour vault">
            </form>
        
        <% if(hashError!= null){
            if (hashError.equals("true")){
                out.print("<p class=warningText><br><br><br>***   The passphrase you entered"
                    + " doesnt match with your stored phrase, please revise and resubmit   ***</p>");
            }
        }
       
        %>
            
        </div>
    </body>
</html>
