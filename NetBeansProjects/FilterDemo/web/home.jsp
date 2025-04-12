<%-- 
    Document   : home
    Created on : 10-07-2024, 19:30:04
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
        <h1>Hello World!</h1>
        <h3><a href="add.jsp">Goi add.jsp</a></h3>
        <h3>Number of people loged in: ${sessionScope.counter}</h3>
    </body>
</html>
