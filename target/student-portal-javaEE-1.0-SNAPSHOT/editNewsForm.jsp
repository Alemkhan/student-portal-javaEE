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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<body>
<%@ include file = "components/header.jsp" %>
<div class="container">
    <div class="row m-3">
        <form class="form border-dark border-1">
            <h4>Title</h4>
            <input id="title" type="text" name="title" class="form-control" value="<c:out value='${newsToChange.getTitle()}'/>"><br>
            <h4>Description</h4>
            <input id="desc" type="text" class="form-control" name="description" value="<c:out value='${newsToChange.getDescription()}'/>"><br>
            <input id="news_id" type="hidden" class="form-control" name="news_id" value="<c:out value='${newsToChange.getId()}'/>"><br>
            <input id="club_id" type="hidden" name="club_id" value="<c:out value='${newsToChange.getClub().getClub_id()}'/>"><br>
            <input id="updateNews" type="button" class="btn btn-success" value="Edit">
        </form>
    </div>
</div>
</body>
<script>
    $("#updateNews").click(function () {

        title = $("#title").val();
        desc = $("#desc").val();
        news_id = $("#news_id").val()
        club_id = $("#club_id").val()

        $.ajax({
            url: "/updateNews",
            type: 'POST',
            data: {
                club_id: club_id,
                title: title,
                description: desc,
                news_id: news_id,
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
