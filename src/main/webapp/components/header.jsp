<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Assylkhan
  Date: 11.11.2020
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Carousel Template for Bootstrap</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/carousel/">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Student Portal</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-info">
    <a class="navbar-brand" href="/dashboard">AITU</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/dashboard">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="search.jsp">Search</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <a href="profile?id=<c:out value="${sessionScope.user.id}"/>">
                <button class="text-lg btn btn-outline-light" type="submit"><c:out value="${sessionScope.user.first_name} ${sessionScope.user.last_name}"/></button>
            </a>
            <div class="media ml-3">
                <img src="https://www.meme-arsenal.com/memes/4755e2d723dc73f9757832eff0992707.jpg" style="height: 64px; width: 64px; border-radius: 50%" class="mr-3" alt="...">
            </div>
        </form>
    </div>
</nav>
</body>
</html>
