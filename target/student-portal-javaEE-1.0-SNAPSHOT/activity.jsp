<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.11.2020
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Activity adding</title>
</head>
<body>
<%@ include file = "components/header.jsp" %>
<div class="row">
    <center><h1>Adding activity to <c:out value="${clubForAdd.getClub_name()}"/></h1></center>
    <form action="">
        <p>Title</p>
        <input type="text" name="title"><br>
        <p>Description</p>
        <input type="text" name="description"><br>
        <select name="activity">
            <option value="news">News</option>
            <option value="event">Event</option>
        </select>
        <input type="submit" value="Add">
    </form>
</div>
</body>
</html>