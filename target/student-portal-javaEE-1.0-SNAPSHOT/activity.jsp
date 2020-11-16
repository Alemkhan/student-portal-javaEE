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
<center><h1>Adding activity to <c:out value="${clubForAdd.getClub_name()}"/></h1></center>
<div class="container">
    <form action="/addActivity" class="form border-dark border-1" method="post">
        <p>Title</p>
        <input type="text" class="form-control" name="title"><br>
        <p>Description</p>
        <input type="text" class="form-control" name="description"><br>
        <select class="form-control" name="activity">
            <option value="news">News</option>
            <option value="event">Event</option>
        </select>
        <input type="hidden" name="club_id" value="<c:out value="${clubForAdd.getClub_id()}"/>">
        <input type="submit" class="mt-3 btn-lg btn-success" value="Add">
    </form>
</div>
</body>
</html>
