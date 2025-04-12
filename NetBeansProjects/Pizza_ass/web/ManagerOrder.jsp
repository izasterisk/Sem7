<%-- 
    Document   : CarShop
    Created on : Jan 25, 2024, 7:34:55 PM
    Author     : vipha
--%>



<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:if test="${account.role != 1}">
        <%
            response.sendRedirect("ShopPizzaServlet");
        %>
    </c:if>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/CarShop.css"/>
    </head>
    <body>

        <img src="images/MainCarShop.jpeg" width="width" height="height" alt="alt"/>
        <h1>CRUD Customer</h1>
        <div>
            <ul class="header">
                <li>AutomobileWebApp</li>
                <li>Home</li>
                <li>Privacy</li>
            </ul>
        </div>
        <jsp:useBean id="method" class="dal.OrderDAO"/>
        <h2>Order List</h2>

        <h2>Welcome to ${account.fullName}</h2>
        <form action="ProcessServlet" method="post">
            <a href="LogoutServlet">Logout</a>
            <a href="ShopPizzaServlet?id=admin">back</a>
            <div class="flex">
                    <h2 style=" background-color: #1668b2;
                        color: white;
                        padding: 10px;
                        border-radius: 5px;
                        width: fit-content;
                        margin: 20px auto;
                        box-shadow: 0px 8px 15px rgba(0,0,0,0.1);
                        ">Total Revenue: $<fmt:formatNumber type="number" pattern="#.##" value="${method.totalMoneyAll}"/> 
                    </h2>
                    <a href="ToltalMoney.jsp">Check Total Money All</a>     
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Customer</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Amount Money</th>
                        <th>Order Date Time</th>
                        <th>Status</th>
                        <th >
                            <div class="header-app">
                                Application
                            </div>
                        </th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="c" items="${orderList}">
                        <c:set var="id" value="${c.orderID}"/>
                        <tr>
                            <td>${id}</td>
                            <td>${c.customer.contactName}</td>    
                            <td>${c.customer.phone}</td>
                            <td>${c.customer.address}</td>


                            <td>
                                $<fmt:formatNumber type="number" pattern="#.##" value="${method.getTotalMoney(id)}"/>
                            </td>
                            <td>${c.orderDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${c.status}">
                                        Đã giao
                                    </c:when>
                                    <c:otherwise>
                                        Đang xử lý
                                    </c:otherwise>
                                </c:choose>
                                <br>        
                                <form action="OrderShowServlet" method="post">
                                    <input type="hidden" name="orderId" value="${c.orderID}" />
                                    <input type="checkbox" name="status" ${c.status ? 'checked' : ''} onclick="this.form.submit();" />
                                    <input type="hidden" name="action" value="updateStatus" />
                                </form>
                            </td>


                            <td class="app">
                                <div class="Details">
                                    <!-- DETAILS CAR-->
                                    <a href="OrderDetails?orderID=${id}" >Details</a>
                                </div>
                            </td>   
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
