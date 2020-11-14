<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Club" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Assylkhan
  Date: 11.11.2020
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Portal</title>
</head>
<body>
<%@ include file = "components/header.jsp" %>
<div class="row m-3">
    <div class="list-group col">
        <h2 class="card-header text-light bg-dark">
            CLUBS
        </h2>
        <%
            ArrayList<Club> clubs = (ArrayList<Club>)request.getAttribute("clubs");
            System.out.println(clubs);
        %>
        <c:forEach var="club" items="${clubs}">
            <a href="clubs?id=<c:out value="${club.getClub_id()}"/> " class="list-group-item list-group-item-action">
                <c:out value="${club.getClub_name()}"/>
            </a>
        </c:forEach>

    </div>
    <div class="col-7">
        <div class="jumbotron">
            <h1 class="display-4">News Title</h1>
            <p class="lead">Description</p>
            <hr class="my-4">
            <p>Publish date</p>
            <!---IMAGE--->
        </div>
    </div>
    <div class="col-3 ml-3">
        <div class="card mb-3">
            <div class="card-body" style="width: 18rem;">
                <h2 class="card-title">Event title</h2>
                <h4 class="card-subtitle mb-2 text-muted">Publish date</h4>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <button type="button" class="btn btn-info">Info</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
