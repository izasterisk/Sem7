<%-- 
    Document   : HomeShop
    Created on : Feb 26, 2024, 6:46:44 PM
    Author     : vipha
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html><head>
        <meta charset="utf-8">
        <title></title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap/menuDoc.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap/show.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <!-- Bootstrap JS and Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-B1JlV5f5qKIexDE5LlWXMWoM9LgI3RjJYD8Dl6+JqBO5eDkD6egpT9HJWoDAZ8hF" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyIxO6U2S/Z9fNfEX3t77Enj06zn1V8J+1" crossorigin="anonymous"></script>

    </head>
    <body>



        <div class="banner">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="CartServlet?action=add">
                        <img src="images/Pizza-logo.png" alt="" width="100" height="100">
                    </a>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="CartServlet" style="padding-left: 25px; padding-right: 25px;"><strong>Giỏ hàng</strong></a>
                            </li>                               
                        </ul>
                        <form action="ProcessServlet" class="d-flex">
                            <input class="form-control me-2" type="text" name="txtSearch" value="${requestScope.search}" placeholder="Search" aria-label="Search" style="width: 326px;">
                            <button class="btn btn-outline-success" type="submit" name="btnAction" value="Search">Search</button>
                        </form>

                        <c:if test="${sessionScope.account == null}"> 
                            <a class="nav-link active" aria-current="page" href="Login.jsp" style="padding-left: 1px; padding-right: 1px;">
                                <img src="css/CssShop/image/right-to-bracket-solid.svg" alt="" width="180" height="36">
                            </a>
                        </c:if> 
                        <c:if test="${sessionScope.account != null}" > 
                            <a class="nav-link active" aria-current="page" href="#" style="padding-left: 1px; padding-right: 1px;" 
                               data-bs-toggle="modal" data-bs-target="#logoutModal">
                                <img src="css/CssShop/image/user-solid.svg" alt="" width="180" height="36">
                            </a>
                        </c:if>

                        <!-- Logout Modal -->
                        <div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="logoutModalLabel">Xác nhận đăng xuất</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Bạn có chắc chắn muốn đăng xuất không?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <button type="button" class="btn btn-primary" onclick="logout()">Đăng xuất</button>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>


                </div>
            </nav>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-2 primary">
                    <div id="menu">
                        <ul>
                            <li>
                                <a href="ShopPizzaServlet"> 
                                    <strong>Home Page</strong>
                                </a>
                            </li>
                            <li>
                                <a href="CheckCate"> 
                                    <strong>All Product</strong>
                                </a>
                            </li>
                            <c:forEach var="c" items="${listCate}">
                                <li>
                                    <a href="CheckCate?id=${c.categoryID}"> 
                                        <strong>${c.categoryName}</strong>
                                    </a>
                                </li>
                            </c:forEach>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>  


        
        <div> 
            <h4>${requestScope.message}</h4>
        </div>

        <div class="main" style="margin-top: 40px;">
            <div class="container-fluid">
                <div class="row">


                    <c:forEach var="m" items="${requestScope.listMobiles}" >

                        <div class="col-md-3 col-sm-6 col-xs-6 wow fadeInUp" style="margin-top:20px;">
                            <div class="item-product text-center">
                                <div class="image">
                                    <a href="DetailsServlet?ProductID=${m.productID}">
                                        <img src="${m.productImage}" style="width: 200px; height:200px;" alt="iphone13">
                                    </a>
                                    <h4>${m.productName}</h4>
                                </div>
                                <div class="price-c">
                                    <p class="price"><s>$4</s>
                                        <br><span class="giamoi">$${m.unitPrice}</span>
                                    </p>
                                </div>
                                <a href="DetailsServlet?ProductID=${m.productID}"><button type="button" class="btn btn-danger">Mua hàng</button></a>
                                <a href="DetailsServlet?ProductID=${m.productID}"><button type="button" class="btn btn-light">Sản phẩm</button></a>
                            </div>
                        </div>

                    </c:forEach>
                </div>   
            </div>





            <nav aria-label="Page navigation example" style="margin-top:20px">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">«</span>
                        </a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">»</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>



        <div style="background:#f1f2f6; margin-top: 50px;">
            <center>
                <h3> <strong>Công Ty Cổ Phần KuMo Haruki</strong></h3>
                <p> <strong>Địa chỉ:</strong>108/7/19J HCM</p>
                <p> <strong>Số ĐT:</strong> 090XXXXXX</p>
                <p><strong>Mail:</strong>thuanse171787@fpt.edu.vn</p>
            </center>
        </div>


        <script type="text/javascript" src="css/CssShop/bootstrap/js/bootstrap.js"></script>
        <script type="text/javascript" src="css/CssShop/bootstrap/js/jquery-3.6.0.min.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-B1JlV5f5qKIexDE5LlWXMWoM9LgI3RjJYD8Dl6+JqBO5eDkD6egpT9HJWoDAZ8hF" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyIxO6U2S/Z9fNfEX3t77Enj06zn1V8J+1" crossorigin="anonymous"></script>

        <script>
                                            function logout() {
                                                // Thực hiện hành động đăng xuất ở đây
                                                alert("Đã đăng xuất!");
                                                window.location.href = "LogoutServlet";

                                                // Đóng modal sau khi đăng xuất
                                                var modal = new bootstrap.Modal(document.getElementById('logoutModal'));
                                                modal.hide();
                                            }
        </script>



    </body></html>