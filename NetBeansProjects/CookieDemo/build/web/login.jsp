<%-- 
    Document   : login
    Created on : 10-07-2024, 13:26:41
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login form</h1>
        <h3 style="color: red">${requestScope.err}</h3>
        <c:set var="cookie" value="${pageContext.request.cookies}"/>
        <form action="login" method="post">
            Enter username: <input type="text" name="user" value="${cookie.cuser.value}"/><br/>
            Enter password: <input type="password" name="pass" value="${cookie.cpass.value}"/><br/>
            <input type="checkbox" ${(cookie.crem!=null?'checked':'')} name="remember" value="ON"/> Remember me<br/>
            <input type="submit" value="login" name="name"/>
        </form>
    </body>
</html>
