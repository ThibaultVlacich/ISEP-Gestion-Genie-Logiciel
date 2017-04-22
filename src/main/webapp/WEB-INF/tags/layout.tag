<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag description="Layout Tag" pageEncoding="UTF-8"%>

<c:set var="debugMode">
	<spring:eval expression="@propertyConfigurer.getProperty('projet-gl.debug')" />
</c:set>

<%@ attribute name="appCss" %>
<%@ attribute name="appJs" %>

<!DOCTYPE html>
<html>
	<head>
		<title>ISEP - Gestion de projet GL</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="robots" content="noindex,nofollow" />

		<link href="<spring:url value="/public/css/app.css" />" rel="stylesheet" />
		<c:if test="${!empty appCss}" >
			<link href="${appCss}" rel="stylesheet" />
		</c:if>
	</head>
	<body>
		<div class="col-menu">
			<div class="col-menu-inner">
				<div class="top-logo">
					<a href="<spring:url value="/"/>">
					<spring:url value="/public/images/logo-isep.png" var="logoIsep" />
					<spring:url value="/public/images/logo-isep@2x.png" var="logoIsepRetina" />
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
				</div>
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

		<!-- Load scripts -->
		<c:choose>
			<c:when test="${!empty appJs}">
				<script src="${appJs}"></script>
			</c:when>
			<c:otherwise>
				<script src="<spring:url value="/public/js/app.js" />"></script>
			</c:otherwise>
		</c:choose>

		<c:if test="${debugMode}">
			<!-- Load LiveReload if we are in debug mode -->
			<script type="text/javascript">
				document.write('<script src="//localhost:35729/livereload.js?snipver=1" type="text/javascript"><\/script>')
			</script>
		</c:if>
	</body>
</html>
