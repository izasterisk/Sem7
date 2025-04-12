<%-- 
    Document   : Details
    Created on : Jan 28, 2024, 11:57:17 PM
    Author     : vipha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Page</title>
        <link rel="stylesheet" href="css/Delete.css"/>
    </head>
    <body>
        <img src="https://khoinguonsangtao.vn/wp-content/uploads/2021/10/background-vang-dep-nhat.jpg" alt="alt"/>
        <h1>Deletes</h1>
        <h2>Are you want to delete this?</h2>
        <h3>Product</h3>
        <form action="ProccessServlet" accept-charset="UTF-8" method="post"> 
            <table>
                <tr>
                    <th>ProductID</th>
                    <th><input type="hidden" name="txtProductID" value="${products.productID}">${products.productID}</th>
                </tr>
                <tr>
                    <td>ProductName</td>
                    <td>${products.productName}</td>
                </tr>
                <tr>
                    <td>CategoryID</td>
                    <td>${products.category.categoryName}</td>
                </tr>
                <tr>
                    <td>Supplier</td>
                    <td>${products.supplier.companyName}</td>
                </tr>
                <tr>
                    <td>QuantityPerUnit</td>
                    <td>${products.quantityPerUnit}</td>
                </tr>
                <tr>
                    <td>UnitPrice</td>
                    <td>${products.unitPrice}</td>
                </tr>
                <tr>
                    <td>ProductImage</td>
                    <td>${products.productImage}</td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td>${products.description}</td>
                </tr>
                </tbody>
            </table>
            <div>
                <input onclick="doDelete('${products.productID}')" type="button" name="btnAction" value="Delete">
                <a href="ShopPizzaServlet?id=admin">Back to List</a>
            </div>   
        </form>
    </body>


    <script type="text/javascript">
        function doDelete(id) {
            if (confirm("Are you sure to delete?")) {
                var form = document.createElement("form");
                form.setAttribute("method", "post");
                form.setAttribute("action", "DeleteServlet");
                
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", "ProductID");
                hiddenField.setAttribute("value", id);
                
                form.appendChild(hiddenField);
                document.body.appendChild(form);
                
                form.submit();
            }
        }
    </script>

</html>
