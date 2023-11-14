<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
if (session.getAttribute("loggedInUser")!= null) {
    response.sendRedirect("userPage.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Song Finder - Registration</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        
        <main id="main-content">
            <h1>Register for MovieGenius</h1>
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    
                    <div class="col-4">
                        <c:if test="${not empty errors}">
                            <ul class="list-unstyled">
                                <c:forEach items="${errors}" var="error">
                                    <li class="text-danger">${error}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                        <form method="post" action="Public">
                            <label for="email-field">Email</label>
                            <br>
                            <input type="email" id="email-field" name="email" class="form-control">
                            <br>
                            <label for="username-field">Username</label>
                            <br>
                            <input type="text" id="username-field" name="username" class="form-control">
                            <br>
                            <label for="password-field">Password</label>
                            <br>
                            <input type="password" id="password-field" name="password" class="form-control">
                            <br>
                            <label for="verify-password-field">Verify Password</label>
                            <br>
                            <input type="password" id="verify-password-field" name="verify-password" class="form-control">
                            <br>
                            <button type="submit" class="btn btn-success">Create Account</button>
                            <input type="hidden" name="action" value="register">
                        </form>
                    </div>
                    
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
        
    </body>
</html>
