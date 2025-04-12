<%-- 
    Document   : add
    Created on : 26-05-2024, 21:37:04
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
        <h3 style="color:red">${requestScope.err}</h3>
        <h1>Add a new category.</h1>
        <form action="add">
            Enter ID: <input type="number" name="id"/><br/>
            Enter name: <input type="text" name="name"/><br/>
            Enter describe: <input type="text" name="describe"/><br/>
            <input type="submit" value="save"/>
        </form>
    </body>
</html>
