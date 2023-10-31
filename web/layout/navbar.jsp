<%-- 
    Document   : navbar
    Created on : Oct 30, 2023, 9:55:14?PM
    Author     : Dylan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="index.jsp">Song Finder</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#movie-genius-navbar" aria-controls="movie-genius-navbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="movie-genius-navbar">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item text-dark">
                    <a href="index.jsp" class="nav-link text-dark">Home</a>
                </li>
                <li class="nav-item">
                    <a href="login.jsp" class="nav-link text-dark">Login</a>
                </li>
                <li class="nav-item">
                        <a href="register.jsp" class="nav-link text-dark">Register</a>
                </li>
            </ul>
        </div>
    </nav>
</header>