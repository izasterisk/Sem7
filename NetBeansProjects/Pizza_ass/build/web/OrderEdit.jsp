<%-- 
    Document   : OrderEdit
    Created on : Mar 13, 2024, 1:40:20 PM
    Author     : vipha
--%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Order</title>
        <link rel="stylesheet" href="css/OrderEdit.css">
    </head>
    <c:if test="${requestScope.account.role != 1}">
        <%
            response.sendRedirect("ShopPizzaServlet");
        %>
    </c:if>
    <body>
        <div class="container">
            <h1>Edit Order</h1>
            <form action="OrderEdit" class="order-form">
                <div class="form-group">
                    <label for="orderDate">Order Date:</label>
                    <input type="datetime-local" id="orderDate" name="orderDate">
                </div>
                <div class="form-group">
                    <label for="shippedDate">Shipped Date:</label>
                    <input type="datetime-local" id="shippedDate" name="shippedDate">
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select id="status" name="status">
                        <option value="processing">Đang xử lý</option>
                        <option value="shipped">Đã giao</option>
                    </select>
                </div>
                <button type="submit" class="submit-btn">Update Order</button>
            </form>
        </div>
    </body>
</html>

