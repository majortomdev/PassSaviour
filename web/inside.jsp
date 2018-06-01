<%-- 
    Document   : Inside
    Created on : Jul 31, 2017, 9:27:04 PM
    Author     : JKinahan
--%>
<%@page import="com.kinahan.encryption.SecurityUtil"%>
<%@page import="com.kinahan.connect.ConnectToDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String user = (String)request.getSession().getAttribute("username");     

%>
<html>
    <body>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
        <title>The Inside</title>
        <style>
            body {
                text-align: center;
                background-color: #2e6349;
            }
           
            
        </style>
    </head>
    
        <h1>This is a dynamic page where you can view your records, which have just been 
            decrypted with your key.</h1>
        
    <table class="table">
        <thead>THE LOGIN DETAILS YOU HAVE SAVED ARE: </thead><br><br>
           
            </tr>     
     <%           
            int userId = Integer.parseInt(request.getParameter("userId"));
            String secretKey = (String)request.getAttribute("secretKey");
                
            ConnectToDB conA = new ConnectToDB();      
            Statement stmt;
            ResultSet rs;
            Connection conn= conA.connect();
        
        try {
            stmt = conn.createStatement();
            String query = "SELECT * FROM encrypted_data WHERE userid = '"+userId+"'";
            rs = stmt.executeQuery(query);
            SecurityUtil sec = new SecurityUtil();
           
                      %>
            <tr><td>SITE / APPLICATION</td>
                <td>USERNAME</td>
                <td>PASSWORD</td></tr>
            
            <%  
            
            while(rs.next()){

                String site = sec.decrypt(rs.getString("siteApp"), secretKey);
                String uname = sec.decrypt(rs.getString("username"), secretKey);
                String password = sec.decrypt(rs.getString("password"), secretKey);
      
                
                %>
                <tr><td>     ---------------------        <%=site%>  -------------------     </td>
                    <td>     ---------------------      <%=uname%>   -------------------       </td>
                    <td>     ---------------------     <%=password%> -------------------       </tr>

                <%
                
        }
          %>  
          
</table> 
          <%
            request.setAttribute("userId", userId);
            request.setAttribute("secretKey", secretKey);
              
        }catch (Exception se){
            System.out.println("sorry");
        }  
                      
          %>      
               
          <br><br><br>
          <p>Click this link to return to our home page/ lobby <a href="home.jsp">Home</a></p>

         <br><br><br>
         <p>Click this link to log out <a href="userlogin.jsp">Log Out</a> </p>     
                
                
           
            
            
        
</body>
</html>
