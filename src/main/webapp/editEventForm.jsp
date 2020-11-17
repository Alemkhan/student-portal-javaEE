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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<body class="bg-light">
<%@ include file = "components/header.jsp" %>
<div class="container">
    <div class="row m-3 ">
        <form class="form border-dark border-1">
            <h4>Title</h4>
            <input id="title" type="text" name="title" class="form-control" value="<c:out value='${eventToChange.getTitle()}'/>"><br>
            <h4>Description</h4>
            <input id="desc" type="text" class="form-control" name="description" value="<c:out value='${eventToChange.getDescription()}'/>"><br>
            <input id="event_id" type="hidden"  class="form-control" name="event_id" value="<c:out value='${eventToChange.getId()}'/>"><br>
            <input id="club_id" type="hidden" name="club_id" value="<c:out value='${eventToChange.getClub().getClub_id()}'/>"><br>
            <input id="updateEvent" type="button" class="btn-lg btn-success" value="Edit">
        </form>
    </div>
</div>
</body>
<script>
    $("#updateEvent").click(function () {

        title = $("#title").val();
        desc = $("#desc").val();
        event_id = $("#event_id").val()
        club_id = $("#club_id").val()

        $.ajax({
            url: "/updateEvent",
            type: 'POST',
            data: {
                club_id: club_id,
                title: title,
                description: desc,
                event_id: event_id,
            },
            success: function (data) {
                console.log(data)
                window.location.href = "clubEdit?club_id="+club_id
            },
            error: function (data) {
                console.log(data)
            }
        });
    });
</script>
</html>
