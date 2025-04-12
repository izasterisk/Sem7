<%-- 
    Document   : welcome
    Created on : 10-07-2024, 13:47:26
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
        <h2>This is user page</h2>
        <h1>Welcome, ${sessionScope.acc.username}</h1>
    </body>
</html>
