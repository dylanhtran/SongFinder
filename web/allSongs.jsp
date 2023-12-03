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
        <title>Update Songs</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="SongFinder.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1><center>Song list</center></h1>
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    <table class="table table-striped table-bordered">

                        <thead>
                            <tr>   
                                <th>Title</th>
                                <th>Artist</th>
                                <th></th>
                            </tr>
                        </thead>
                        <c:forEach var="song" items="${allSongs}">
                            <tr>
                                <th style="width:30%">
                                    ${song.title}
                                </th>
                                <th style="width:46%">
                                    ${song.artist}
                                </th>
                                <th style="width:12%">
                                    <form action="Private" method="post">
                                        <input type="hidden" name="songID" value="${song.songID}"/>
                                        <input type="hidden" name="action" value="updateSongPage" class="form-control"/>
                                        <button type="submit" class="btn btn-success">Update Song</button>
                                    </form>
                                </th>
                                <th style="width:12%">
                                    <form action="Private" method="post">
                                        <input type="hidden" name="songID" value="${song.songID}"/>
                                        <input type="hidden" name="action" value="deleteSong" class="form-control"/>
                                        <button type="submit" class="btn btn-danger">Delete Song</button>
                                    </form>
                                </th>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
    </body>
</html>
