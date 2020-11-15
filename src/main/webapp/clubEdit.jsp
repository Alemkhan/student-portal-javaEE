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
<body>
<%@include file="components/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<div class="container">
    <center><h1>List of Events</h1></center><br>
    <div class="row mt-3">
        <div class="result"></div>
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
                    <td><a id="edit" href="/clubEdit?club_id=<c:out value='${clubForEdit.getClub_id()}'/>&event_id=<c:out value='${event.getId()}' />">Edit</a></td>
                    <td><a id="delete" onclick="deleteUser(<c:out value='${clubForEdit.getClub_id()}'/>, <c:out value='${event.getId()}' />)" href="/clubEdit?club_id=<c:out value='${clubForEdit.getClub_id()}'/>&event_id=<c:out value='${event.getId()}' />">Delete</a></td>
                    <input id="club_id" type="hidden" value="<c:out value='${clubForEdit.getClub_id()}'/>">
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
<script>

    $("#edit").click(function () {

    });

    function deleteUser(club_id, event_id){
        $.ajax({
            url: "/clubEdit",
            type: "GET",
            data: {
                action : "deleteEvent",
                club_id : club_id,
                event_id : event_id
            },
            success: function (data) {
                $(".result").html("Success");
            }
        });
    };

</script>
</html>
