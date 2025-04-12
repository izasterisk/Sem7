<%-- 
    Document   : update
    Created on : 26-05-2024, 22:58:30
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update a category.</h1>
        <c:set var="c" value="${requestScope.category}"/>
        <form action="update" method="post">
            Enter ID: <input type="number" readonly name="id" value="${c.id}"/><br/>
            Enter name: <input type="text" name="name" value="${c.name}"/><br/>
            Enter describe: <input type="text" name="describe" value="${c.describe}"/><br/>
            <input type="submit" value="update"/>
        </form>
    </body>
</html>
