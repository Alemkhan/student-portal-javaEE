<%--
  Created by IntelliJ IDEA.
  User: Assylkhan
  Date: 11.11.2020
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath());
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Portal</title>
</head>
<body>
<%@ include file = "components/header.jsp" %>
<div class="row m-3">
    <div class="list-group mb-3 col-lg col-md-12 col-sm-12">
        <h2 class="card-header text-light bg-dark">
            CLUBS
        </h2>
        <c:forEach var="club" items="${clubs}">
            <a href="clubs?id=<c:out value="${club.getClub_id()}"/> " class="list-group-item list-group-item-action">
                <c:out value="${club.getClub_name()}"/>
            </a>
        </c:forEach>
        <a href="addClubForm.jsp" class="list-group-item list-group-item-action">Create Club</a>
        <c:if test="${sessionScope.user.role.role_name == 'admin' &&
                sessionScope.user.id != null}">
            <a href="/deleteClub" class="list-group-item list-group-item-action">Delete Clubs</a>
        </c:if>
    </div>

    <div class="col-lg-7 col-md-12 col-sm-12">
        <c:forEach var="newsItem" items="${news}">
            <div class="jumbotron">
                <h1 class="display-4"><c:out value="${newsItem.getTitle()}"/></h1>
                <p class="lead"><c:out value="${newsItem.getDescription()}"/></p>
                <hr class="my-4">
                <p><c:out value="${newsItem.getDate()}"/> by <c:out value="${newsItem.getClub().getClub_name()}"/></p>
                <!---IMAGE--->
            </div>
        </c:forEach>
    </div>

    <div class="col-lg col-md-12 col-sm-12">
        <c:forEach var="eventItem" items="${events}">
            <div class="card mb-3">
                <div class="card-body" style="width: 18rem;">
                    <h2 class="card-title"><c:out value="${eventItem.getTitle()}"/></h2>
                    <h4 class="card-subtitle mb-2 text-muted"><c:out value="${eventItem.getDate()}"/></h4>
                    <p class="card-text"><c:out value="${eventItem.getDescription()}"/></p>
                    <a href="clubs?id=<c:out value="${eventItem.getClub().getClub_id()}"/> "><button type="button" class="btn btn-info">More Info</button></a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
