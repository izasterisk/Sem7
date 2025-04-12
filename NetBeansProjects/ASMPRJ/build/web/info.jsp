<%-- 
    Document   : info
    Created on : 10-07-2024, 23:06:08
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Info Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>Profile page</h1>
        <h3>
            Username:${sessionScope.account.username}<br/>
            <c:if test="${sessionScope.account.role==1}">
                CHÀO MỪNG ADMIN!
            </c:if>
            <c:if test="${sessionScope.account.role==2}">
                CHÀO MỪNG USER!
            </c:if>
        </h3>
    </body>
</html>
