<%@ page import="Models.Club" %><%--
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
<body>
<%@ include file = "components/header.jsp" %>
<!--FRONT PLEASE-->
<c:out value="${club.getClub_name()}"/>


<c:if test="${sessionScope.user.role.role_name == 'admin' ||
              newsItem.getClub().getOwner().getId == sessionScope.user.id &&
              sessionScope.user.id != null}">
    <a href="#">EDIT</a>
</c:if>



</body>
</html>
