<%-- 
    Document   : list
    Created on : 26-05-2024, 20:58:36
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("are U sure to delete category with id =" + id)) {
                    window.location = "check?action=delete&id=" + id;
                }
            }
        </script>        
    </head>    
    <body>
    <center>
        <h1>List categories</h1>
        <h3><a href="check?action=add">Add new!</a></h3>
        <table border="1px" width="40%">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Describe</th>
                <th style="color: red">Action</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="c">
                <c:set var="id" value="${c.id}"/>
                <tr>
                    <td>${id}</td>
                    <td>${c.name}</td>
                    <td>${c.describe}</td>
                    <td>
                        <a href="check?action=update&id=${id}">Update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDelete('${id}')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>            
</body>
</html>
