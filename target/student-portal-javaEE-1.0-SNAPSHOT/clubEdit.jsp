<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.11.2020
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Club Admin</title>
</head>
<body class="bg-light">
<%@include file="components/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<div class="container">
    <center><h1 class="display-1">List of Events</h1></center><br>
    <div class="row mt-3">
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Event ID</th>
                    <th scope="col">Event Title</th>
                    <th scope="col">Event Description</th>
                    <th scope="col">Event Date</th>
                    <th scope="col">Edit</th>
                    <th scope="col">Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="event" items="${clubForEdit.getEventsList()}">
                    <tr>
                        <th scope="row"><c:out value="${event.getId()}" /></th>
                        <td><c:out value="${event.getTitle()}" /></td>
                        <td><c:out value="${event.getDescription()}" /></td>
                        <td><c:out value="${event.getDate()}" /></td>
                        <td><a id="edit" href="/editEvent?event_id=<c:out value='${event.getId()}'/>">Edit</a></td>
                        <td><a href="#" onclick="deleteEvent(<c:out value='${clubForEdit.getClub_id()}'/>, <c:out value='${event.getId()}' />)">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>
    <center><h1 class="display-1">List of News</h1></center><br>
    <div class="row mt-3">
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">News ID</th>
                    <th scope="col">News Title</th>
                    <th scope="col">News Description</th>
                    <th scope="col">Publish Date</th>
                    <th scope="col">Edit</th>
                    <th scope="col">Delete</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="news" items="${clubForEdit.getNewsList()}">
                <tr>
                    <th scope="row"><c:out value="${news.getId()}" /></th>
                    <td><c:out value="${news.getTitle()}" /></td>
                    <td><c:out value="${news.getDescription()}" /></td>
                    <td><c:out value="${news.getDate()}" /></td>
                    <td><a href="/editNews?news_id=<c:out value='${news.getId()}' />">Edit</a></td>
                    <td><a href="#" onclick="deleteNews(<c:out value='${clubForEdit.getClub_id()}'/>, <c:out value='${news.getId()}' />)">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<script>

    function deleteNews(club_id, news_id){
        $.ajax({
            url: "/clubEdit",
            type: "GET",
            data: {
                action : "deleteNews",
                club_id : club_id,
                news_id : news_id
            },
            success: function () {
                window.location.href = "clubEdit?club_id="+club_id
            }
        });
    };

    function deleteEvent(club_id, event_id){
        $.ajax({
            url: "/clubEdit",
            type: "GET",
            data: {
                action : "deleteEvent",
                club_id : club_id,
                event_id : event_id
            },
            success: function () {
                window.location.href = "clubEdit?club_id="+club_id
            }
        });
    };

</script>
</html>
