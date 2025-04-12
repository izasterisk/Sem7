
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Complete Responsive Pizza Shop Website Design</title>

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">


    </head>
    <body>

        <!-- header section starts  -->

        <header class="header">

            <section class="flex">

                <a href="#home" class="logo">Pizza.</a>

                <nav class="navbar">
                    <a href="#home">home</a>
                    <a href="#about">about</a>
                    <a href="#menu">menu</a>
                    <a href="#order">order</a>
                    <a href="#faq">faq</a>
                    <a href="CheckCate">Categories</a>
                    <c:if test="${sessionScope.account != null && account.role == 1}">
                        <a href="ShopPizzaServlet?id=admin">CRUD</a>
                    </c:if>

                </nav>

                <form action="ProcessServlet" method="get" class="form-inline my-2 my-lg-0">
                    <div class="input-group input-group-sm" style="display: flex">
                        <div>
                            <input name="txtSearch" value="${searchName}" type="text" class="form-control" 
                                   aria-label="Small" 
                                   aria-describedby="inputGroup-sizing-sm"
                                   placeholder="Search..."
                                   style="border: 1px #000 solid;
                                   margin-top: 5px;
                                   padding: 20px 20px">
                        </div>
                        <div class="input-group-append">
                            <button type="submit" name="btnAction" value="Search" class="btn btn-secondary btn-number">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <div class="icons">
                    <div id="menu-btn" class="fas fa-bars"></div>
                    <div id="user-btn" class="fas fa-user"></div>
                    <div id="order-btn" class="fas fa-box"></div>
                    <div id="cart-btn" class="fas fa-shopping-cart"><span>(${size})</span></div>
                </div>

            </section>

        </header>

        <!-- header section ends -->

        <div class="user-account">

            <section>

                <div id="close-account"><span>close</span></div>

                <div class="user">
                    <p><span>you are not logged in now!</span></p>
                </div>

                <div class="display-orders">
                    <p>pizza-1 <span>( $3/- x 2 )</span></p>
                    <p>pizza 03 <span>( $2/- x 1 )</span></p>
                    <p>pizza 06 <span>( $4/- x 4 )</span></p>
                    <p>pizza 07 <span>( $2/- x 1 )</span></p>
                </div>

                <c:if test="${sessionScope.account == null}">
                    <a href="Login.jsp" btn> <input type="button" value="login" name="btnAction" class="btn"></a>
                    </c:if>


                <c:if test="${sessionScope.account != null}">
                    <a href="LogoutServlet" btn> <input type="button" value="Log Out" class="btn"></a>
                    </c:if>

            </section>

        </div>

        <div class="my-orders">

            <section>

                <div id="close-orders"><span>close</span></div>

                <h3 class="title"> my orders </h3>

                <div class="box">
                    <p>placed on : <span>06/04/2022</span> </p>
                    <p>name : <span>shaikh anas</span> </p>
                    <p>number : <span>1234567890</span></p>
                    <p>address : <span>flat no. 123, bulding no. 2, jogeshwari, mumbai, india - 400104</span></p>
                    <p>payment method : <span>cash on delivery</span></p>
                    <p>your orders : <span>pizza 01 $3/- x 2, pizza 03 $2/- x 1, pizza 06 $4/- x 4, pizza 07, $2/- x 1</span></p>
                    <p>total price : <span>$11/-</span></p>
                    <p>payment status : <span style="color: var(--red);">pending</span> </p>
                </div>

                <div class="box">
                    <p>placed on : <span>06/04/2022</span> </p>
                    <p>name : <span>shaikh anas</span> </p>
                    <p>number : <span>1234567890</span></p>
                    <p>address : <span>flat no. 123, bulding no. 2, jogeshwari, mumbai, india - 400104</span></p>
                    <p>payment method : <span>cash on delivery</span></p>
                    <p>your orders : <span>pizza 01 $3/- x 2, pizza 03 $2/- x 1, pizza 06 $4/- x 4, pizza 07, $2/- x 1</span></p>
                    <p>total price : <span>$11/-</span></p>
                    <p>payment status : <span style="color: var(--red);">pending</span> </p>
                </div>

            </section>

        </div>

        <div class="shopping-cart">

            <section>
                <div id="close-cart"><span>close</span></div>



                <c:forEach var="p" items="${listItem1}">
                    <div class="box">
                        <form action="CartServlet" method="post">
                            <input type="hidden" name="productID" value="${p.products.productID}"/>                

                            <input type="hidden" name="action" value="view"/>
                            <button type="submit" class="fas fa-times"></button>
                        </form>

                        <img src="${p.products.productImage}" alt="">
                        <div class="content">
                            <p>${p.products.productName}<span>( $${p.unitPrice}/- x ${p.quantity} )</span></p>
                            <form action="ProcessServlet">
                                <input type="hidden" name="productID" value="${p.products.productID}"/>
                                <button type="submit" class="fas fa-edit" name="btnAction" value="addToCart"></button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
                <a href="#order" class="btn">order now</a>

            </section>

        </div>

        <div class="home-bg">

            <section class="home" id="home">

                <div class="slide-container">

                    <div class="slide active">
                        <div class="image">
                            <img src="images/home-img-1.png" alt="">
                        </div>
                        <div class="content">
                            <h3>homemade Pepperoni Pizza</h3>
                            <div class="fas fa-angle-left" onclick="prev()"></div>
                            <div class="fas fa-angle-right" onclick="next()"></div>
                        </div>
                    </div>

                    <div class="slide">
                        <div class="image">
                            <img src="images/home-img-2.png" alt="">
                        </div>
                        <div class="content">
                            <h3>Pizza With Mushrooms</h3>
                            <div class="fas fa-angle-left" onclick="prev()"></div>
                            <div class="fas fa-angle-right" onclick="next()"></div>
                        </div>
                    </div>

                    <div class="slide">
                        <div class="image">
                            <img src="images/home-img-3.png" alt="">
                        </div>
                        <div class="content">
                            <h3>Mascarpone And Mushrooms</h3>
                            <div class="fas fa-angle-left" onclick="prev()"></div>
                            <div class="fas fa-angle-right" onclick="next()"></div>
                        </div>
                    </div>

                </div>

            </section>

        </div>

        <!-- about section starts  -->

        <section class="about" id="about">

            <h1 class="heading">about us</h1>

            <div class="box-container">

                <div class="box">
                    <img src="images/about-1.svg" alt="">
                    <h3>made with love</h3>
                    <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Illum quae amet beatae magni numquam facere sit. Tempora vel laboriosam repudiandae!</p>
                    <a href="#menu" class="btn">our menu</a>
                </div>

                <div class="box">
                    <img src="images/about-2.svg" alt="">
                    <h3>30 minutes delivery</h3>
                    <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Illum quae amet beatae magni numquam facere sit. Tempora vel laboriosam repudiandae!</p>
                    <a href="#menu" class="btn">our menu</a>
                </div>

                <div class="box">
                    <img src="images/about-3.svg" alt="">
                    <h3>share with freinds</h3>
                    <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Illum quae amet beatae magni numquam facere sit. Tempora vel laboriosam repudiandae!</p>
                    <a href="#menu" class="btn">our menu</a>
                </div>

            </div>

        </section>

        <!-- about section ends -->

        <!-- menu section starts  -->

        <section id="menu" class="menu">

            <h1 class="heading">our menu</h1>

            <div class="box-container">
                <c:forEach var="p" items="${productList}">
                    <div class="box">
                        <div class="price">$<span>${p.unitPrice}</span>/-</div>
                        <a href="DetailsServlet?ProductID=${p.productID}"><img src="${p.productImage}" alt="Pizza"></a>
                        <div class="name">${p.productName}</div>
                        <form action="ProcessServlet">
                            <input type="number" min="1" max="100" value="1" class="qty" name="quantity">
                            <input type="hidden"  value="${p.productID}" name="productID">
                            <input type="hidden"  value="add" name="action">
                            <input type="submit" value="addToCart" name="btnAction" class="btn">
                        </form>
                    </div>
                </c:forEach>
            </div>

        </section>

        <!-- menu section ends -->

        <!-- order section starts  -->

        <section class="order" id="order">

            <h1 class="heading">order now</h1>

            <form action="CartServlet" method="post">

                <div class="display-orders">
                    <c:forEach var="p" items="${listItem1}">
                        <p>${p.products.productName}<span>( $${p.unitPrice}/- x ${p.quantity} )</span></p>
                    </c:forEach>
                </div>

                <div class="flex">
                    <div class="inputBox">
                        <span>your name :</span>
                        <input type="text" name="name" class="box" required placeholder="enter your name" maxlength="20">
                    </div>
                    <div class="inputBox">
                        <span>your number :</span>
                        <input type="number" name="number" class="box" required placeholder="enter your number" min="0">
                    </div>
                    <div class="inputBox">
                        <span>payment method</span>
                        <select name="method" class="box">
                            <option value="cash on delivery">cash on delivery</option>
                            <option value="credit card">credit card</option>
                            <option value="paytm">paytm</option>
                            <option value="paypal">paypal</option>
                        </select>
                    </div>
                    <div class="inputBox">
                        <span>address line 01 :</span>
                        <input type="text" name="flat" class="box" required placeholder="e.g. flat no." maxlength="50">
                    </div>
                    <div class="inputBox">
                        <span>address line 02 :</span>
                        <input type="text" name="street" class="box" required placeholder="e.g. street name." maxlength="50">
                    </div>
                    <div class="inputBox">
                        <span>pin code :</span>
                        <input type="number" name="pin_code" class="box" required placeholder="e.g. 123456" min="0">
                    </div>
                </div>

                <input type="submit" value="order now" class="btn" name="order">

            </form>

        </section>

        <!-- order section ends -->

        <!-- faq section starts  -->

        <section class="faq" id="faq">

            <h1 class="heading">FAQ</h1>

            <div class="accordion-container">

                <div class="accordion active">
                    <div class="accordion-heading">
                        <span>how does it work?</span>
                        <i class="fas fa-angle-down"></i>
                    </div>
                    <p class="accrodion-content">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, quas. Quidem minima veniam accusantium maxime, doloremque iusto deleniti veritatis quos.
                    </p>
                </div>

                <div class="accordion">
                    <div class="accordion-heading">
                        <span>how long does it take for delivery?</span>
                        <i class="fas fa-angle-down"></i>
                    </div>
                    <p class="accrodion-content">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, quas. Quidem minima veniam accusantium maxime, doloremque iusto deleniti veritatis quos.
                    </p>
                </div>

                <div class="accordion">
                    <div class="accordion-heading">
                        <span>can I order for huge parties?</span>
                        <i class="fas fa-angle-down"></i>
                    </div>
                    <p class="accrodion-content">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, quas. Quidem minima veniam accusantium maxime, doloremque iusto deleniti veritatis quos.
                    </p>
                </div>

                <div class="accordion">
                    <div class="accordion-heading">
                        <span>how much protein it contains?</span>
                        <i class="fas fa-angle-down"></i>
                    </div>
                    <p class="accrodion-content">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, quas. Quidem minima veniam accusantium maxime, doloremque iusto deleniti veritatis quos.
                    </p>
                </div>


                <div class="accordion">
                    <div class="accordion-heading">
                        <span>is it cooked with oil?</span>
                        <i class="fas fa-angle-down"></i>
                    </div>
                    <p class="accrodion-content">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, quas. Quidem minima veniam accusantium maxime, doloremque iusto deleniti veritatis quos.
                    </p>
                </div>

            </div>

        </section>

        <!-- faq section ends -->

        <!-- footer section starts  -->

        <section class="footer">

            <div class="box-container">

                <div class="box">
                    <i class="fas fa-phone"></i>
                    <h3>phone number</h3>
                    <p>+123-456-7890</p>
                    <p>+111-222-3333</p>
                </div>

                <div class="box">
                    <i class="fas fa-map-marker-alt"></i>
                    <h3>our address</h3>
                    <p>mumbai, india - 400104</p>
                </div>

                <div class="box">
                    <i class="fas fa-clock"></i>
                    <h3>opening hours</h3>
                    <p>00:09am to 00:10pm</p>
                </div>

                <div class="box">
                    <i class="fas fa-envelope"></i>
                    <h3>email address</h3>
                    <p>shaikhanas@gmail.com</p>
                    <p>anasbhai@gmail.com</p>
                </div>

            </div>

            <div class="credit">
                &copy; copyright @ 2022 by <span>mr. web designer</span> | all rights reserved!
            </div>

        </section>

        <!-- footer section ends -->


        <!-- custom js file link  -->
        <script src="js/script.js"></script>


    </body>
</html>