<%-- 
    Document   : ToltalMoney
    Created on : Mar 15, 2024, 12:41:26 PM
    Author     : vipha
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Revenue Checker</title>
        <link rel="stylesheet" href="css/totalMoney.css"/>
    </head>
    <body>
        <div class="date-selection">
            <form action="TotalMoneyServlet" method="post">
                Start Day:<input type="date" id="startDate" name="startDate" value="${startDateStr}">
                End Day:<input type="date" id="endDate" name="endDate" value="${endDateStr}">
                <button type="submit">Check Revenue</button>
            </form>
        </div>
        <div id="revenueResult" class="revenue-result">Total Revenue:
            $<fmt:formatNumber type="number" pattern="#.##" value="${totalAll}"/>
            </div>
        <div>
            <a href="OrderShowServlet" >Back</a>
        </div>
    </body>
</html>

