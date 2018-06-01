<%-- 
    Document   : index
    Created on : Jul 30, 2017, 11:45:03 PM
    Author     : jk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.kinahan.connect.ConnectToDB" %>
<%@page import="com.kinahan.connect.VerifyLogin" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="pass.css" rel="stylesheet" type="text/css">
        <title>PassSaviour</title>
                <style>
           
        </style>
    </head>
    <body>
        <%
            String error =(String)request.getAttribute("errorMessage");
            --%>
        <div class="Heading">
            
            <h1>PassSaviour- <i>The</i> Secure Password Vault</h1>
        </div>
        <div class="login">
            <p>To access your passSaviour account, enter your username and your password.</p>
            <br>
            <form name="loginform" method="post" action="VerifyLogin">
            <label for="username">Username: </label>
                <input TYPE = "Text" Size="50" Value = "" Name = "username" Maxlength="45"><br><br>
            <label for="passwordA">PassWord: </label>
                <input TYPE = "password" Size="50" Value = "" Name = "passwordA" Maxlength="45"><br><br><br>
                <br>
                <% if (error!=null){
                    
                    %>
                
                <div class="warningText">
                    <%= error %> 
                </div>
                    <% }%>
                <br>
                
                <input type="submit" value="Submit Details to Open PassSaviour" />
            </form>
        </div>
    
        

        
                    <p>Click here if you want to set up an account with 
                        us: <a href="signup.jsp">Create Account</a></p>
                    
     
    </body>
</html>
