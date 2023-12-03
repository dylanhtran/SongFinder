<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (session.getAttribute("loggedInUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Discover</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="SongFinder.css" rel="stylesheet" type="text/css">
    </head>
    <body style="align-items: center">
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1><center>Discover New Music!</center></h1>
            <br>
            <h2><center>Choose a genre below to get started</center></h2>
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    <form action="Private" method="post" style="text-align: center">
                        <input type="hidden" name="action" value="discoverSongPage">
                        <br>
                        <select name="genreID" id="genreID" class="form-control">
                            <c:forEach var="genre" items="${allGenres}">
                                <option value="${genre.genreID}">${genre.genreName}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <button type="submit" class="btn btn-success btn-lg">Discover</button>
                    </form>
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
    </body>
</html>
