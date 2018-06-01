<%-- 
    Document   : login
    Created on : Jul 31, 2017, 9:44:48 PM
    Author     : Jkinahan
--%>
<%@page import="com.kinahan.connect.CreateNewUser" %>
<%@page import="com.kinahan.connect.VerifyLogin" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="pass.css" rel="stylesheet" type="text/css">
        <title>PassSaviour New User Page</title>
    </head>
    <body>
        <h1>Create a new account at PassSaviour</h1>
        <div class="loginbox">
             <form action="CreateNewUser" method="post">
            <label for="username"><span class="tags">PassSaviour UserName: </span></label>
            <input type="text" name="username" size="60"><br/><br/>
            <label for="password"><span class="tags">PassSaviour Password: </span></label>
            <input type="password" name="passwordA" size="60"><br/><br/>
            <label for="email"><span class="tags">Email: </span></label>
            <input type="text" name="email" size="60">
            
            

            
            <br><br>
            <h3>You are now asked to enter a personal sentence, something 
                meaningful or in some other way completely unforgettable, 20 characters minimum is 
                recommended for optimum net security. This will be your private key 
                to your password vault. <h3>
                    <label for="perskey">Your private sentence key: </label>
                    <input type="text" name="perskey" size="100"><br><br><br>
            <input type="Submit" value="Submit these fields and a new account will be created">    
        </form>
    </div>
    </body>
</html>
