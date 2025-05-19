<%@page contentType = "text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "java.util.Objects" %>
<!DOCTYPE HTML>
<html lang = "ja">
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/tool/css/style.css" type="text/css">
		<title>学生管理システム</title>
	</head>

	<body>
		<header>
			<h2>得点管理システム</h2>


			<div class="header-right">
				<c:if test="${user != null}">
					<p>${ user.getName() }様
					<a href = "Logout.action" class = "link-button-header">ログアウト</a>
					</p>
				</c:if>
			</div>

		</header>