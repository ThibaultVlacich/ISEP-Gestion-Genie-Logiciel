<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag description="Wrapper Tag" pageEncoding="UTF-8"%>

<%@ attribute name="appCss" %>
<%@ attribute name="appJs" %>

<!DOCTYPE html>
<html>
<head>
	<title>ISEP - Gestion de projet GL</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="robots" content="noindex,nofollow" />

	<!-- Bootstrap -->
	<link rel="stylesheet" href="<spring:url value="/resources/bootstrap-3.3.7/css/bootstrap.min.css" />">

	<!-- Custom styles -->
	<link href="<spring:url value="/resources/core/css/app.css" />" rel="stylesheet" />
	<c:if test="${!empty appCss}" >
		<link href="${appCss}" rel="stylesheet" />
	</c:if>

	<!-- jQuery -->
	<script src="<spring:url value="/resources/jquery-3.2.0.min.js" />"></script>
	<!-- Bootstrap -->
	<script src="<spring:url value="/resources/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>

	<!-- Custom scripts -->
    <script src="<spring:url value="/resources/core/js/script.js" />"></script>
	<c:if test="${!empty appJs}" >
		<script src="${appJs}"></script>
	</c:if>
</head>
<body>
	<div class="col-menu">
		<div class="col-menu-inner">
			<div class="top-logo">
				<a href="<spring:url value="/"/>">
                    <spring:url value="/resources/core/images/logo-isep.png" var="logoIsep" />
                    <spring:url value="/resources/core/images/logo-isep@2x.png" var="logoIsepRetina" />
                    <img src="${logoIsep}" srcset="${logoIsep} 1x, ${logoIsepRetina} 2x" alt="ISEP Logo">
                </a>
			</div>

			<ul class="apps-menu">
				<li class="active">
					<a href="/" class="app-link">
						Tableau de bord
					</a>
				</li>
			</ul>
		</div>
	</div>

	<div class="col-content">
		<header class="page-head">
			<div class="toolbar-box">
				<button id="menu-toggle" title="Menu" class="btn btn-default hidden-lg hidden-md"><i class="glyphicon glyphicon-menu-hamburger"></i></button>
			</div>

			<div class="pull-right">
				<div class="userbox">
					<div class="btn-group">
						<button type="button" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
							Welcome <span class="caret"></span>
						</button>

						<ul class="dropdown-menu pull-right" role="menu">
							<li><a href="<spring:url value="/user/login"/>" class="navbar-link"><i class="glyphicon glyphicon-log-in"></i> Sign in</a></li>
						</ul>
					</div>
				</div>
				{/if}
			</div>

			<div class="clearfix"></div>
		</header>

		<div class="content">
			<div class="app-content">
				<jsp:doBody/>
			</div>

			<footer class="app-footer">
				<hr />
				<p>&copy; ISEP 2017</p>
			</footer>
		</div>
	</div>
	<div class="clearfix"></div>
</body>
</html>
