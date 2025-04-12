<%-- 
    Document   : menu
    Created on : Mar 7, 2024, 3:03:24 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <!-- header section starts  -->

        <header class="header">

            <section class="flex">

                <a href="ShopPizzaServlet" class="logo">Pizza</a>

                <nav class="navbar">
                    <a href="ShopPizzaServlet#home">Home</a>
                    <a href="ShopPizzaServlet#about">About</a>
                    <a href="ShopPizzaServlet#menu">Menu</a>
                    <a href="ShopPizzaServlet#order">Order</a>
                </nav>

                <div class="icons">
                    <div id="menu-btn" class="fas fa-bars"></div>
                    <div id="user-btn" class="fas fa-user"></div>
                    <div id="order-btn" class="fas fa-box"></div>
                    <div id="cart-btn" class="fas fa-shopping-cart"><span>(4)</span></div>
                </div>

            </section>     
            <c:if test="${account != null}">
                <center>
                    <h1 style="color: #e74c3c; margin-bottom: 8px" >Welcome to ${account.fullName}</h1>
                </center>
            </c:if>
        </header>


        <!-- header section ends -->
    </body>
    <script src="js/script.js"></script>
</html>
