<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:layout>
    <div class="alert alert-warning" role="alert">
        <h4>404 Not found</h4>
        <p>
            Sorry, but the page you're looking for doesn't exist.
            <br><br>
            <a href="<spring:url value="/" />">Go back to the home page</a>
        </p>
    </div>
</t:layout>
