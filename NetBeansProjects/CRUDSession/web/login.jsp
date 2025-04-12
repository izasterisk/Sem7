<%-- 
    Document   : login
    Created on : 10-07-2024, 13:26:41
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login form</h1>
        <h3 style="color: red">${requestScope.err}</h3>
        <form action="login" method="post">
            Enter username: <input type="text" name="user"/><br/>
            Enter password: <input type="password" name="pass"/><br/>
            <input type="submit" value="login" name="name"/>
        </form>
    </body>
</html>
