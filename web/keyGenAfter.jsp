<%-- 
    Document   : login
    Created on : Jul 31, 2017, 9:44:48 PM
    Author     : Jkinahan
--%>
<%@page import="com.kinahan.connect.CreateNewUser" %>
<%@page import="com.kinahan.encryption.SecurityUtil" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="pass.css" rel="stylesheet" type="text/css">
        <title>PassSaviour new Key Page- URGENT</title>
    </head>
    <body>
        <h2>Below this message is your personal Encryption key. This is the key you will always use to encrypt
        and to decrypt your data</h2>
         
            
            <textarea name="displayKey" rows="5" cols="40" resize: none>
                    
                <%=                   
                    (String)request.getSession().getAttribute("SecretKey") 
                   
                %>
            </textarea>
      
            
        
            
            <br><br>
            
            <h3>VERY IMPORTANT- make a copy of this key NOW, and its our VITAL recommendation
                that you also copy it externally, you will need to use it whenever you want to
                add to your data store, and also, importantly, when you want to retrieve your data<h3>
                    <br><br><br>
                    <h4> Click this button to proceed to the home page, from there you can use your
                    key to encrypt your data</h4>
                    <a href="home.jsp"><button value="buttonHome">Proceed to home</button></a>
        
    

      
        
    </body>
</html>
