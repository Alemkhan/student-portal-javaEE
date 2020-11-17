<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.11.2020
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Activity adding</title>
</head>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<body>
<%@ include file = "components/header.jsp" %>
<center><h1>Adding activity to <c:out value="${clubForAdd.getClub_name()}"/></h1></center>
<div class="container">
    <form class="form border-dark border-1">
        <p>Title</p>
        <input id = "title" type="text" class="form-control" name="title"><br>
        <p>Description</p>
        <input id = "desc" type="text" class="form-control" name="description"><br>
        <select id="typeOfAct" class="form-control" name="activity">
            <option value="news">News</option>
            <option value="event">Event</option>
        </select>
        <input id="club_id" type="hidden" name="club_id" value="<c:out value="${clubForAdd.getClub_id()}"/>">
        <input id="postActivity" type="button" class="mt-3 btn-lg btn-success" value="Add">
    </form>
</div>
</body>
<script>
        $("#postActivity").click(function () {

            title = $("#title").val();
            desc = $("#desc").val();
            typeOf = $("#typeOfAct").val();
            club_id = $("#club_id").val()

            $.ajax({
                url: "/addActivity",
                type: 'POST',
                data: {
                    club_id: club_id,
                    title: title,
                    description: desc,
                    activity: typeOf,
                },
                success: function (data) {
                    console.log(data)
                    window.location.href = "clubs?id="+club_id
                },
                error: function (data) {
                    console.log(data)
                }
            });
        });
</script>
</html>
