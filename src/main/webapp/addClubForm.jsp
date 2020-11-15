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
<body>
<%@ include file = "components/header.jsp" %>
<center><h1>Adding activity to <c:out value="${clubForAdd.getClub_name()}"/></h1></center>
<div class="container">
    <form action="/addClub" class="form border-dark border-1 m-3" method="post">
        <p>Club Name</p>
        <input type="text" class="form-control" name="name"><br>
        <p>Description</p>
        <input type="text" class="form-control" name="description"><br>
        <input type="submit" class="btn-lg btn-success" value="Add">
    </form>
</div>
</body>
</html>
