<%-- 
    Document   : encrypt
    Created on : Jul 31, 2017, 2:26:09 PM
    Author     : Jkinahan
--%>
<%@page import="com.kinahan.connect.ConnectToDB" %>
<%@page import="com.kinahan.encryption.SecurityUtil" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PassSaviour Encrypt</title>
        <link href="pass.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        
        <%  String user= (String)request.getSession().getAttribute("username");
        
            int userId = Integer.parseInt(request.getParameter("userId")); 
            String appSite=(String)request.getParameter("appSiteName");
            if(appSite == null){
                appSite=(String)request.getAttribute("appSiteName");
            }
            String appUsername=(String)request.getParameter("appUserName");
            String appPassword=(String)request.getParameter("appPassword");
            String hashOut = (String) request.getAttribute("hashOut");
           %>
           <h2>You are logged in as  <%=user %> </h2>
      
        <p>Back to Home <a href="home.jsp">Home</a></p>
            <form action ="EncryptIt" method="post">
                

                        <input type="hidden" name="userId" value="<%= userId %>"><br>  
<p>To encrypt and save your login details for: 
                        <input type="text"  class="hideInputBackground"  name="appSite" 
                               value="<%= appSite %>"></p><br>

                        <label for="secretKey">Please enter/ paste your secret Key</label>
                        <input type="text" size="55" name="secretKey"><br>
                        <input type="hidden" name="appUserName" value="<%= appUsername %>"><br>
                        <input type="hidden" name="appPassword" value="<%= appPassword %>"><br>
                        <input type="submit" value="To encrypt and save to your database" >      
            </form><br><br><br>

            
                <h5><% if(hashOut!= null){
                    if (hashOut.equals("true")){
                    out.print("<p>Sorry, this doesnt seem to be the same one that you used before"
                            + ", remember- you hold the only key so please, check that "
                            + "you are using the correct personal Secret Key ");
                }  
                }
                
                %>
                </h5>
            
            
            
            
    </body>
</html>
