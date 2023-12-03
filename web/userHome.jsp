<%-- 
    Document   : index
    Created on : Oct 30, 2023, 10:02:01 PM
    Author     : Dylan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Song Finder</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="SongFinder.css" rel="stylesheet" type="text/css">
    </head>
    <body style="text-align: center">
        <jsp:include page="layout/navbar.jsp" />
        <h1><center>Home</center></h1>
        <h2><center>Click below to get started!</center></h2>
        <div class="container">
            <div class="row">
                <div class="col-4"></div>

                <div class="col-4">
                    <form method="post" action="Private" style="display: inline-block">
                        <input type="hidden" name="action" value="playlistCheck">
                        <button type="submit" class="btn btn-success btn-lg">Get Started!</button>
                    </form>
                </div>
                <div class="col-4"></div>
            </div>
        </div>
    </body>
</html>
