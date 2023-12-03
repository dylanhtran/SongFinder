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
        <title>${song.title}</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="SongFinder.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1><center>${song.title}</center></h1>
            <h2><center>${song.artist}</center></h2>
            
            <center><audio controls autoplay>
                <source src="${filepath}" type="audio/mp3">
                Your browser does not support the audio tag.
                </audio></center>

            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>

                    <br>
                    <form action="Private" method="post" style="text-align: center">
                        <select name="playlistID" id="playlistID" class="form-control">
                            <c:forEach var="playlist" items="${allPlaylists}">
                                <option value="${playlist.playlistID}">${playlist.playlistName}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <input type="hidden" name="genreID" value="${genreID}"/>
                        <input type="hidden" name="songID" value="${song.songID}"/>
                        <input type="hidden" name="title" value="${song.title}"/>
                        <input type="hidden" name="artist" value="${song.artist}"/>
                        <input type="hidden" name="action" value="addSongToPlaylist" class="form-control"/>
                        <button type="submit" class="btn btn-success btn-lg">Add to Playlist</button>
                    </form>
                    <form action="Private" method="post" style="text-align: center">
                        <br>
                        <input type="hidden" name="genreID" value="${genreID}"/>
                        <input type="hidden" name="action" value="skipSong" class="form-control"/>
                        <button type="submit" class="btn btn-primary btn-lg">Next Song</button>
                    </form>
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
    </body>
</html>
