<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.11.2020
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Event</title>
</head>
<body class="bg-light">
<%@ include file = "components/header.jsp" %>
<div class="container">
    <div class="row m-3 ">
        <form action="/updateEvent" class="form border-dark border-1" method="post">
            <h4>Title</h4>
            <input type="text" name="title" class="form-control" value="<c:out value='${eventToChange.getTitle()}'/>"><br>
            <h4>Description</h4>
            <input type="text" class="form-control" name="description" value="<c:out value='${eventToChange.getDescription()}'/>"><br>
            <input type="hidden"  class="form-control" name="event_id" value="<c:out value='${eventToChange.getId()}'/>"><br>
            <input type="hidden" name="club_id" value="<c:out value='${eventToChange.getClub().getClub_id()}'/>"><br>
            <input type="submit" class="btn-lg btn-success" value="Edit">
        </form>
    </div>
</div>
</body>
</html>
