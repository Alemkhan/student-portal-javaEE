<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.11.2020
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit News</title>
</head>
<body>
<%@ include file = "components/header.jsp" %>
<div class="container">
    <div class="row m-3">
        <form action="/updateNews" class="form border-dark border-1" method="post">
            <h4>Title</h4>
            <input type="text" name="title" class="form-control" value="<c:out value='${newsToChange.getTitle()}'/>"><br>
            <h4>Description</h4>
            <input type="text" class="form-control" name="description" value="<c:out value='${newsToChange.getDescription()}'/>"><br>
            <input type="hidden" class="form-control" name="news_id" value="<c:out value='${newsToChange.getId()}'/>"><br>
            <input type="hidden" name="club_id" value="<c:out value='${newsToChange.getClub().getClub_id()}'/>"><br>
            <input type="submit" class="btn btn-success" value="Edit">
        </form>
    </div>
</div>
</body>
</html>
