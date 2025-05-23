<%-- 
    Document   : cart
    Created on : 10-07-2024, 17:00:01
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
        <form action="cart">
            Nhập ID:<input type="text" name="id"/><br/>
            Số lượng mua:<input type="number" name="num"/><br/>
            <input type="submit" value="BỎ VÀO GIỎ"/>
        </form>
    <c:set var="cookie" value="${pageContext.request.cookies}"/>
    <jsp:useBean id="list" class="Model.Cart"/>
    <h1>Danh sách hàng mua </h1>
    <table border="1px" width="30%">
        <tr>
            <th>ID</th>
            <th>Số lượng</th>
        </tr>
        <c:forEach items="${list.getCart(cookie.cart.value)}" var="i">
            <tr>
                <td>${i.id}</td>
                <td>${i.quantity}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
