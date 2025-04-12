<%-- 
    Document   : login
    Created on : 10-07-2024, 23:00:31
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>Login Page</h1>
        <h3 style="color: red">${requestScope.ms}</h3>
        <form action="login" method="post">
            enter username:<input type="text" name="user"/><br/>
            enter password:<input type="password" name="pass"/><br/>
            <input type="submit" value="LOGIN"/>
        </form>
    </body>
</html>
