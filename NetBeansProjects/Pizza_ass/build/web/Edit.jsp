<%-- 
    Document   : Add
    Created on : Jan 25, 2024, 11:40:08 PM
    Author     : vipha
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EDIT Page</title>
        <link rel="stylesheet" href="css/Edit.css"/>
    </head>
    <body>
        <span id="title">Edit Car</span>
        <div class="form--create">
            <img
                src="https://vcdn-vnexpress.vnecdn.net/2023/06/08/Bugatti-Chiron-Super-Sport-1-2005-1686190331.jpg"
                alt=""
                />
            <form action="ProcessServlet" name="editMobileForm" method="post" accept-charset="UTF-8" onsubmit="return validateForm()">
                <div class="form--add">
                    <div class="MobilesID">
                        <span>ProductID</span><br />
                        <input type="text" name="txtProductID" 
                               value="${products.productID}"
                               readonly=""
                               /><br />
                    </div>
                    <div class="Price">
                        <span>ProductName</span><br />
                        <input
                            type="text"
                            name="txtProductName"
                            value="${products.productName}"
                            placeholder="9999.00"
                            /><br />
                    </div>
                    <div class="MobileName">
                        <span>Supplier</span><br />
                        <input
                            type="text"
                            value="${products.supplier.companyName}"
                            readonly
                            /><br />
                    </div>
                    <div class="MobileName">
                        <span>Category</span><br />
                        <input
                            type="text"
                            value="${products.category.categoryName}"
                            readonly
                            /><br />
                    </div>
                    <div class="MobileName">
                        <span>QuantityPerUnit</span><br />
                        <input
                            type="text"
                            name="txtQuantityPerUnit"
                            value="${products.quantityPerUnit}"
                            /><br />
                    </div>
                    <div class="MobileName">
                        <span>UnitPrice</span><br />
                        <input
                            type="text"
                            name="txtUnitPrice"
                            value="${products.unitPrice}"
                            /><br />
                    </div>
                    <div class="MobileName">
                        <span>ProductImage</span><br />
                        <input
                            type="text"
                            name="txtProductImage"
                            value="${products.productImage}"
                            /><br />
                    </div>
                    <div class="Description">
                        <span>Description</span><br />
                        <input
                            type="text"
                            name="txtDescription"
                            value="${products.description}"
                            /><br />
                        <br />
                    </div>  
                     
                            <div style="display: flex; justify-content: space-between";
                                 
                                 >
                                <div style="font-size: 20px">
                                    <label>Category</label>
                                    <select name="txtCategoryID" class="form-select" aria-label="Default select example">
                                        <option  value="${products.category.categoryID}">${products.category.categoryName}</option>
                                        <c:forEach items="${cateList}" var="p">
                                            <option  value="${p.categoryID}">${p.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <br>
                                <div style="font-size: 20px">
                                    <label>Suppliers</label>
                                    <select name="txtSupplierID" class="form-select" aria-label="Default select example">
                                       <option  value="${products.supplier.supplierID}">${products.supplier.companyName}</option>

                                        <c:forEach items="${suppList}" var="p">
                                            <option  value="${p.supplierID}">${p.companyName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                    <div>
                        ${requestScope.megasse}
                    </div>
                </div>
                <div class="submit">
                    <a href="ShopPizzaServlet?id=admin">Back</a>
                    <input id="create" type="submit" value="Edit" name="btnAction" />
                </div>

                
            </form>
        </div>
    </body>

    <script>
    function validateForm() {
        // Fetch values from the form
        var productName = document.forms["editMobileForm"]["txtProductName"].value;
        var supplierID = document.forms["editMobileForm"]["txtSupplierID"].value;
        var categoryID = document.forms["editMobileForm"]["txtCategoryID"].value;
        var quantityPerUnit = document.forms["editMobileForm"]["txtQuantityPerUnit"].value;
        var unitPrice = document.forms["editMobileForm"]["txtUnitPricet"].value; // Check if this name matches the form
        var productImage = document.forms["editMobileForm"]["txtProductImage"].value;
        var description = document.forms["editMobileForm"]["txtDescription"].value;

        // Validate each field
        if (productName === "" || supplierID === "" || categoryID === "" ||
            quantityPerUnit === "" || unitPrice === "" || productImage === "" || description === "") {
            alert("All fields must be filled out.");
            return false;
        }

        alert("Product edited successfully!");
        return true;
    }
</script>



</html>
