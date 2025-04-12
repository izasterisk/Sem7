<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <center>
        <h1>List users</h1>
        <table border="1px" width="40%">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th style="color: red">Action</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="u">
                <c:set var="id" value="${u.id}"/>
                <tr>
                    <td>${id}</td>
                    <td>${u.firstName}</td>
                    <td>${u.lastName}</td>
                    <td>${u.email}</td>
                    <td>
                        <a href="update?id=${id}">Update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=${id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h2>Search User by Last Name</h2>
        <form action="search" method="post">
            <div>
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" required>
            </div>
            <div>
                <button type="submit">Search</button>
            </div>
        </form>
        <h3 style="color:red">${requestScope.err}</h3>
    </center>
</html>
