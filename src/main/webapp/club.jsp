<%@ page import="Models.Club" %>
<%@ page import="Models.Event" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: alemh
  Date: 15.11.2020
  Time: 1:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body class="bg-light">
<%@ include file = "components/header.jsp" %>
<!--FRONT PLEASE-->
<%--qweryy--%>
<c:if test="${sessionScope.user.role.role_name == 'admin' ||
              newsItem.getClub().getOwner().getId == sessionScope.user.id &&
              sessionScope.user.id != null}">
    <a href="/clubEdit?club_id=<c:out value="${club.getClub_id()}"/>" class="m-3 btn-lg btn-primary">EDIT</a>
</c:if>
<div class="container">
    <div class="jumbotron p-3 p-md-5 text-dark rounded bg-white m-3 border">
        <div class="col-md-6 px-0">
            <h1 class="display-4 text-primary"><c:out value="${club.getClub_name()}"/></h1>
            <p class="lead my-3"><c:out value="${club.getDescription()}"/></p>
        </div>
    </div>
    <% Club club = (Club) request.getAttribute("club");
    ArrayList<Event> eventsList = club.getEventsList();
    if (!eventsList.isEmpty()) { %>
    <!---Carousel of Events--->
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <%
                int length = club.getEventsList().size();
                for (int i = 1; i < length; i++) {%>
            <li data-target="#carouselExampleIndicators" data-slide-to="<%=i%>"></li>
            <% }%>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="row m-3">
                    <div class="col">
                        <div class="card flex-md-row mb-4 box-shadow h-md-250">
                            <div class="card-body d-flex flex-column align-items-start">
                                <strong class="d-inline-block mb-2 text-primary">Event</strong>
                                <h3 class="mb-0">
                                    <a class="text-dark" href="#"><%=eventsList.get(0).getTitle()%></a>
                                </h3>
                                <div class="mb-1 text-muted"><%=eventsList.get(0).getDate()%></div>
                                <p class="card-text mb-auto"><%=eventsList.get(0).getDescription()%></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <% for (int i = 1; i < length; i++) {%>
            <div class="carousel-item">
                <div class="row m-3">
                    <div class="col">
                        <div class="card flex-md-row mb-4 box-shadow h-md-250">
                            <div class="card-body d-flex flex-column align-items-start">
                                <strong class="d-inline-block mb-2 text-primary">Event</strong>
                                <h3 class="mb-0">
                                    <a class="text-dark" href="#"><%=eventsList.get(i).getTitle()%></a>
                                </h3>
                                <div class="mb-1 text-muted"><%=eventsList.get(i).getDate()%></div>
                                <p class="card-text mb-auto"><%=eventsList.get(i).getDescription()%></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div><%} %>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <%}%>

    <!---Creators--->
<%--    <div class="container marketing bg-white m-3 border-1">--%>
<%--        <div class="row p-4">--%>
<%--            <div class="col-lg-4">--%>
<%--                <img class="rounded-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">--%>
<%--                <h2 class="p-2">Heading</h2>--%>
<%--                <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>--%>
<%--            </div>--%>
<%--            <div class="col-lg-4">--%>
<%--                <img class="rounded-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">--%>
<%--                <h2 class="p-2">Heading</h2>--%>
<%--                <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>--%>
<%--            </div>--%>
<%--            <div class="col-lg-4">--%>
<%--                <img class="rounded-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">--%>
<%--                <h2 class="p-2">Heading</h2>--%>
<%--                <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

    <!---NEWSS--->
    <div class="row m-3 text-dark bg-white">
        <div class="col-md-8 blog-main p-3">
            <h3 class="pb-3 mb-4 font-italic border-bottom">
                News of <c:out value="${club.getClub_name()}"/>
            </h3>
            <c:forEach var="clubNews" items="${club.getNewsList()}">
            <div class="blog-post">
                <h2 class="blog-post-title"><c:out value="${clubNews.getTitle()}"/></h2>
                <p class="blog-post-meta text-primary"><c:out value="${clubNews.getDate()}"/></p>
                <p><c:out value="${clubNews.getDescription()}"/></p>
            </div>
                <hr>
            </c:forEach>
        </div>
    </div>
    <hr>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://getbootstrap.com/docs/4.0/assets/js/vendor/popper.min.js"></script>
<script src="https://getbootstrap.com/docs/4.0/dist/js/bootstrap.min.js"></script>
<script src="https://getbootstrap.com/docs/4.0/assets/js/vendor/holder.min.js"></script>
</body>
</html>
