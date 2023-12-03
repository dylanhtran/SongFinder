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
        <title>Add Song</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="SongFinder.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1><center>Add a Song<center></h1>
                        <br>
                        <div class="container">
                            <div class="row">
                                <div class="col-4"></div>

                                <div class="col-4">
                                    <form method="post" action="Private">
                                        <c:forEach items="${requestScope.errors}" var="error">
                                            <p class="text-danger">${error}</p>
                                        </c:forEach>

                                        <label for="song-field">Song Title:</label>
                                        <br>
                                        <input type="text" id="song-field" name="song" class="form-control">
                                        <br>
                                        <label for="artist-field">Artist:</label>
                                        <br>
                                        <input type="text" id="artist-field" name="artist" class="form-control">
                                        <br>
                                        Genres:
                                        <br>
                                        <c:forEach var="genre" items="${allGenres}">
                                            <input type="checkbox" id="${genre.genreID}" name="genre" value="${genre.genreName}"/>
                                            <label for="${genre.genreID}">${genre.genreName}</label>
                                            <br>
                                        </c:forEach>
                                        <br>
                                        <br>
                                        <input type="hidden" name="action" value="addSong">
                                        <button type="submit" class="btn btn-success">Add Song</button>
                                    </form>
                                </div>

                                <div class="col-4"></div>
                            </div>
                        </div>
                        </main>
                        </body>
                        </html>
