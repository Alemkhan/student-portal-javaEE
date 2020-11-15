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
<%--    title--%>
    <title>Club Admin</title>
</head>
<body>
<%@include file="components/header.jsp"%>
<div class="container">
    <center><h1>List of Events</h1></center><br>
    <div class="row mt-3">
        <table border="1" cellpadding="5">
            <tr>
                <th>Event ID</th>
                <th>Event Title</th>
                <th>Event Description</th>
                <th>Event Date</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="event" items="${clubForEdit.getEventsList()}">
                <tr>
                    <td><c:out value="${event.getId()}" /></td>
                    <td><c:out value="${event.getTitle()}" /></td>
                    <td><c:out value="${event.getDescription()}" /></td>
                    <td><c:out value="${event.getDate()}" /></td>
                    <td><a href="/editEvent?club_id=<c:out value='${clubForEdit.getId()}' />&event_id=<c:out value='${event.getId()}' />">Edit</a></td>
                    <td><a href="/deleteEvent?event_id=<c:out value='${book.getId()}' />">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
