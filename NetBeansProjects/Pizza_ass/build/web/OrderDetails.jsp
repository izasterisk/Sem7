<%-- 
    Document   : OrderDetails
    Created on : Mar 13, 2024, 11:04:17 AM
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
        <title>Order Details</title>
        <link rel="stylesheet" href="css/OrderDetails.css">
    </head>
    
    <c:if test="${account.role != 1}">
        <%
           response.sendRedirect("ShopPizzaServlet");
        %>
    </c:if>
    <body>

        <c:set var="o"  value="${order}"/>
        <div class="order-details">
            <h1>Order Details</h1>
            <div class="details-section">
                <h2>Customer Information</h2>
                <p><strong>Name:</strong>${o.customer.contactName}</p>
                <p><strong>Phone:</strong>${o.customer.phone}</p>
                <p><strong>Address:</strong>${o.customer.address}</p>
            </div>
            <div class="details-section">
                <h2>Order Information</h2>
                <p><strong>Order ID:</strong>${o.orderID}</p>
                <p><strong>Date Order:</strong> <fmt:formatDate value="${o.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
                <p><strong>Date Shipped:</strong> <fmt:formatDate value="${o.shippedDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>

                <p><strong>Status:</strong>
                    <c:choose>
                        <c:when test="${o.status}">
                            Đã giao
                        </c:when>
                        <c:otherwise>
                            Đang xử lý
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
            <div class="details-section">
                <h2>Items</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Item</th>
                            <th>Quantity</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="od" items="${listOrderDetails}">
                            <tr>
                                <td>${od.product.productID}</td>
                                <td>${od.product.productName}</td>
                                <td>${od.quantity}</td>
                                <td>$${od.unitPrice}</td>
                            </tr>
                        </c:forEach>
                        <!-- More items here -->
                    </tbody>
                </table>
            </div>
            <div class="details-section total-section">
                <a href="OrderShowServlet">back</a>
                <p><strong>Total:</strong>
                    $ <fmt:formatNumber type="number" pattern="#.##" value="${price}"/>
                </p>
            </div>
        </div>
    </body>
</html>

