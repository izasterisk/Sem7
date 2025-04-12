<%-- 
    Document   : list
    Created on : 09-07-2024, 19:40:56
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #wrapper{
                margin-left: 10%;
                margin-right: 10%;
                font-family: arial;
            }
            h3{
                text-align: left;
                color: blue;
            }
            ul{
                list-style: none;
            }
            li{
                width: 25%;
                text-align: center;
                float: left;
                margin: 25px;
                border: 2px solid chocolate;
                border-radius: 20px;
            }
            li img{
                width: 90%;
            }
            li a{
                text-decoration: none;
            }
            li:hover{
                background: bisque;
            }
            p{
                margin: 5px;
                color: blue;
                font-size: 12px;
            }
            .old{
                text-decoration: line-through;
                color: red;
            }
        </style>
        <script type="text/javascript">
            function change(){
                document.getElementById("f1").submit();
            }
        </script>
    </head>
    <body>
        <div id="wrapper">
            <c:set var="cid" value="${requestScope.cid}"/>
            <form id="f1" action="list">
                Dong dien thoai:
                <select name="key" onchange="change()">
                    <option value="0">All</option>
                    <c:forEach items="${requestScope.categories}" var="c">
                    <option ${(cid==c.id)?'selected':''} value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select>
            </form>
            <h3>Cac dong dien thoai</h3>
            <ul>
                <c:forEach items="${requestScope.products}" var="p">
                <li>
                    <a href="#">
                        <img src="${p.images}"/>
                        <p>${p.name}</p>
                        <p>Gia goc: <span class="old">${(p.price*3)}</span></p>
                        <p>Gia ban: ${(p.price*2)}</p>
                    </a>
                </li>
                </c:forEach>
            </ul>

        </div>
    </body>
</html>
