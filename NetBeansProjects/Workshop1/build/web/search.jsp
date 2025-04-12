<%-- 
    Document   : search
    Created on : 27-05-2024, 03:25:04
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
        <table border="1px" width="40%">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
            <c:forEach items="${requestScope.ln}" var="u">
                <c:set var="id" value="${u.id}"/>
                <tr>
                    <td>${id}</td>
                    <td>${u.firstName}</td>
                    <td>${u.lastName}</td>
                    <td>${u.email}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
