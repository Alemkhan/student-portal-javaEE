<%--
  Created by IntelliJ IDEA.
  User: alemh
  Date: 11.11.2020
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
    <div id="addResultDiv" style="color: red"></div>
        <form id="userForm">
            <label for="email">Email:</label><br>
            <input type="email" name="email" class="form-control" id="email"><br>
            <label for="password">Password:</label><br>
            <input type="password" name="password" class="form-control" id="password"><br>
            <input type="button" class="btn btn-primary" id="btn" value="Log In">
        </form>
    </body>
    <script>
        $("#btn").click(function (){
            event.preventDefault();
            email = $("#email").val();
            password = $("#password").val();
            var arr = {
                "email" : email,
                "password" : password
            };
            $.ajax({
                type: 'POST',
                url: 'rest/userService/login',
                contentType: 'application/json',
                data: {
                    "email" : email,
                    "password" : password
                },
                success: {
                    function(result) {
                        $('#addResultDiv').html(result);
                    }
                },
                error : {
                    function(jqxhr, status, errorMsg) {
                        alert('Failed! ' + errorMsg);
                    }
                }
            });
            return false;
        });
    </script>
</html>
