
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update an user.</h1>
        <c:set var="u" value="${requestScope.user}"/>
        <form action="update" method="post">
            Enter ID: <input type="number" readonly name="id" value="${u.id}"/><br/>
            Enter first name: <input type="text" name="firstName" value="${u.firstName}"/><br/>
            Enter last name: <input type="text" name="lastName" value="${u.lastName}"/><br/>
            Enter email: <input type="text" name="email" value="${u.email}"/><br/>
            <input type="submit" value="update"/>
        </form>
    </body>
</html>
