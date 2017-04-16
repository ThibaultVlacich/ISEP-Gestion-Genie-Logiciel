<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <div class="app-user">
        <form class="form-signin" action="<spring:url value="/user/login"/>" method="post">
            <h2 class="form-signin-heading">Sign in</h2>
            <div class="form-group">
                <label for="inputLogin" class="sr-only">Login</label>
                <input type="text" id="inputLogin" name="login" class="form-control" placeholder="Login" required autofocus>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
            </div>
            <p class="form-text text-muted">
                Use the same login and password you use on Moodle.
            </p>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
    </div>
</t:layout>
