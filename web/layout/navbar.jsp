<%-- 
    Document   : navbar
    Created on : Oct 30, 2023, 9:55:14?PM
    Author     : Dylan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="Private?action=goToHomePage">Song Finder</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#song-finder-navbar" aria-controls="song-finder-navbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="song-finder-navbar">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item text-dark">
                    <a href="Private?action=goToHomePage" class="nav-link text-dark">Home</a>
                </li>
                <c:if test="${empty sessionScope.loggedInUser}">
                    <li class="nav-item">
                        <a href="login.jsp" class="nav-link text-dark">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a href="register.jsp" class="nav-link text-dark">Register</a>
                    </li>
                </c:if>
                <c:if test="${loggedInUser.userType eq 'admin'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Genre
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">
                                <a href="Private?action=addGenrePage" class="nav-link text-dark">Add Genre</a>
                            </li>
                            <li class="dropdown-item">
                                <a href="Private?action=deleteGenrePage" class="nav-link text-dark">Delete Genre</a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Users
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">
                                <a href="Private?action=allUserPage" class="nav-link text-dark">All users</a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Song
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">
                                <a href="Private?action=addSongPage" class="nav-link text-dark">Add Song</a>
                            </li>
                            <li class="dropdown-item">
                                <a href="Private?action=allSongPage" class="nav-link text-dark">Update Songs</a>
                            </li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${loggedInUser.userType eq 'user'}">
                    <li class="nav-item">
                        <a href="Private?action=discoverPage" class="nav-link text-dark">Discover New Music</a>
                    </li>
                    <li class="nav-item">
                        <a href="Private?action=playlistPage" class="nav-link text-dark">My Playlists</a>
                    </li>
                </c:if>
                <c:if test="${not empty loggedInUser || loggedInUser != null}">
                    <form action="Private" method="post">
                        <input type="hidden" value="logout" name="action" />
                        <button role="link" type="submit" class="nav-link text-dark">Log Out</button>
                    </form>
                </c:if>
            </ul>
        </div>
    </nav>
</header>