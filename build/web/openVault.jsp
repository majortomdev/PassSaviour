<%-- 
    Document   : openVault
    Created on : Jul 31, 2017, 2:45:21 PM
    Author     : JKinahan
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="com.kinahan.connect.ConnectToDB"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="pass.css" rel="stylesheet" type="text/css">
         
        <title>PassSaviour - Open Vault </title>
    </head>
    <body>
        
        
        <%            

            String user = (String)request.getSession().getAttribute("username");

            String userId = request.getParameter("UserId");
            String error = (String)request.getAttribute("error");
            request.setAttribute("userId", userId);

            %>
        
        <h1><%= user %>'s vault</h1>
        <form action="finalAuthentication" method="post">
            <label for="key">Enter in your secret key to open your vault</label>
            <input type="text" name="key" size="60"><br>
            <input type="hidden" name="userId" value="<%= userId %>"/>
            <input type="hidden" name="username" value="<%= user %>"/>
            <input type="submit" value="Open Vault"/>
        </form>
            
        <% 
            if(error!=null){
               if (error.equals("true")){
                out.print("<p class=warningText><br><br><br>***   The Secret Key you entered produces a hash"
                    + " that doesnt match with your stored hash value, please revise and resubmit   ***</p>");
            }
            }
            %>

        
    </body>
</html>
