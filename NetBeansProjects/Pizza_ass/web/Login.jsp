<%-- 
    Document   : LoginPage
    Created on : Feb 20, 2024, 12:59:25 PM
    Author     : vipha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Created By CodingNepal -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title>Login and Registration</title>
        <link rel="stylesheet" href="css/LoginPage.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="wrapper">
            <div class="title-text">
                <div class="title login">
                    Login Form
                </div>
                <div class="title signup">
                    Signup Form
                </div>
            </div>
            <div class="form-container">
                <div class="slide-controls">
                    <input type="radio" name="slide" id="login" checked>
                    <input type="radio" name="slide" id="signup">
                    <label for="login" class="slide login">Login</label>
                    <label for="signup" class="slide signup">Signup</label>
                    <div class="slider-tab"></div>
                </div>
                <div class="form-inner">
                    <form action="ProcessServlet" method="post" class="login">
                        <div>
                            <h4 style="color: red">${requestScope.message}</h4>
                        </div>
                        <div class="field">
                            <input type="text" name="txtUsername" value="${userName}" placeholder="User Id" required>
                        </div>
                        <div class="field">
                            <input type="password" name="txtPassword" value="${password}" placeholder="Password" required>
                        </div>
                        <div style="margin-top: 10px">
                            <input type="checkbox" name="remember" value="ON"> Remember Account</input>
                        </div>
                        <div class="pass-link">
                            <a href="#">Forgot password?</a>
                        </div>
                        <div class="field btn">
                            <div class="btn-layer"></div>
                            <input type="submit" name="btnAction" value="Login">
                        </div>
                        <div class="signup-link">
                            Not a member? <a href="">Signup now</a>
                        </div>
                    </form>


                    <form action="ProcessServlet" method="post" class="signup">
                        <div class="field">
                            <input type="text" name="fullName" placeholder="Full Name" required>
                        </div>
                        <div class="field">
                            <input type="text" name="userId" placeholder="User Id" required>
                        </div>
                        <div class="field">
                            <input type="password" name="password" placeholder="Password" required>
                        </div>
                        <div class="field">
                            <input type="password" name="passwordConfirm" placeholder="Confirm password" required>
                        </div>
                        <div>
                            <h4 style="color: red">${requestScope.message}</h4>
                        </div>
                        <div class="field btn">
                            <div class="btn-layer"></div>
                            <input type="submit" name="btnAction" value="Signup">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            const loginText = document.querySelector(".title-text .login");
            const loginForm = document.querySelector("form.login");
            const loginBtn = document.querySelector("label.login");
            const signupBtn = document.querySelector("label.signup");
            const signupLink = document.querySelector("form .signup-link a");
            signupBtn.onclick = (() => {
                loginForm.style.marginLeft = "-50%";
                loginText.style.marginLeft = "-50%";
            });
            loginBtn.onclick = (() => {
                loginForm.style.marginLeft = "0%";
                loginText.style.marginLeft = "0%";
            });
            signupLink.onclick = (() => {
                signupBtn.click();
                return false;
            });

            document.addEventListener("DOMContentLoaded", function () {
                var showSignup = "${requestScope.showSignup}";
                if (showSignup === "true") {
                    signupBtn.click();
                }
            });


        </script>

    </body>
</html>
