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
        <title>Add Playlist</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="SongFinder.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1><center>Add a Playlist<center></h1>
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    
                    <div class="col-4">
                        <form method="post" action="Private">
                            <c:forEach items="${requestScope.errors}" var="error">
                                <p class="text-danger">${error}</p>
                            </c:forEach>

                            <label for="genre-field">Playlist Name:</label>
                            <br>
                            <input type="text" id="playlist-field" name="playlist" class="form-control">
                            <br>
                            <input type="hidden" name="action" value="addPlaylist">
                            <button type="submit" class="btn btn-success">Add Playlist</button>
                    </div>
                    
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
    </body>
</html>
