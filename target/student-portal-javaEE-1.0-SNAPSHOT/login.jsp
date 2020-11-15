<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alemh
  Date: 11.11.2020
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Login V17</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="loginfolder/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginfolder/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginfolder/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginfolder/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginfolder/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginfolder/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginfolder/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginfolder/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginfolder/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginfolder/css/util.css">
    <link rel="stylesheet" type="text/css" href="loginfolder/css/main.css">
    <!--===============================================================================================-->
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form class="login100-form validate-form" id="userForm" action="/login" method="post">


                <span class="login100-form-title p-b-34">
						Account Login
				</span>

                <c:if test="${requestScope.messageResponse != null}">
                    <span class="login100-form-title p-b-34" style="color:red">
                        <c:out value="${requestScope.messageResponse}"/>
                        <%request.setAttribute("messageResponse", null);%>
				    </span>
                </c:if>

                <div class="wrap-input100 rs1-wrap-input100 validate-input m-b-20" data-validate="Type user name">
                    <input id="first-name" class="input100" type="text" name="email" placeholder="User name">
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 rs2-wrap-input100 validate-input m-b-20" data-validate="Type password">
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        Login
                    </button>
                </div>
            </form>

            <div class="login100-more" style="background-image: url('loginfolder/images/bg-01.jpg');"></div>
        </div>
    </div>
</div>



<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<script>
    $(".selection-2").select2({
        minimumResultsForSearch: 20,
        dropdownParent: $('#dropDownSelect1')
    });
</script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>






<%--    <script>--%>
<%--        $("#btn").click(function (){--%>
<%--            event.preventDefault();--%>
<%--            email = $("#email").val();--%>
<%--            password = $("#password").val();--%>
<%--            console.log(email + " " + password);--%>
<%--            $.ajax({--%>
<%--                url: '/login',--%>
<%--                type: 'POST',--%>
<%--                data: {--%>
<%--                    "email" : email,--%>
<%--                    "password" : password--%>
<%--                },--%>
<%--                accepts: "application/json; charset=utf-8",--%>
<%--                success:--%>
<%--                    function() {--%>
<%--                        window.location.href = "index.jsp"--%>
<%--                    },--%>
<%--                error :--%>
<%--                    function(response) {--%>
<%--                        if (response.status === 404) {--%>
<%--                            $('#addResultDiv').html(response.responseText);--%>
<%--                        }--%>
<%--                    }--%>
<%--            });--%>
<%--            return false;--%>
<%--        });--%>
<%--    </script>--%>

