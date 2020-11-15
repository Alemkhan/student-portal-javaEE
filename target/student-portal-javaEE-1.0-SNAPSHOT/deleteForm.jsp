<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.11.2020
  Time: 0:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Club</title>
</head>
<body>
<%@ include file = "components/header.jsp" %>
<center><h1>Adding activity to <c:out value="${clubForAdd.getClub_name()}"/></h1></center>
<div class="container">
    <form action="/confirmDelete" class="form border-dark border-1" method="post">
        <select name="club_id">
        <c:forEach var="club" items="${clubsForDelete}">
            <option value="<c:out value="${club.getClub_id()}"/>"><c:out value="${club.getClub_name()}"/></option>
        </c:forEach>
        </select>
        <input type="submit" class="btn-lg btn-success" value="Delete">
    </form>
</div>
</body>
</html>
