<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.11.2020
  Time: 0:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<body>
<%@ include file = "components/header.jsp" %>
<center><h1>Adding a new Club</h1></center>
<div class="container">
    <form class="form border-dark border-1 m-3">
        <p>Club Name</p>
        <input id="name" type="text" class="form-control" name="name"><br>
        <p>Description</p>
        <input id="desc" type="text" class="form-control" name="description"><br>
        <input id="postClub" type="button" class="btn-lg btn-success" value="Add">
    </form>
</div>
</body>
<script>
    $("#postClub").click(function () {

        name = $("#name").val();
        desc = $("#desc").val();

        $.ajax({
            url: "/addClub",
            type: 'POST',
            data: {
                name: name,
                description: desc,
            },
            success: function () {
                window.location.href = "/dashboard"
            },
        });
    });
</script>
</html>
