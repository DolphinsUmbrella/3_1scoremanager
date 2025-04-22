<%@page contentType = "text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "java.util.Objects" %>
<!DOCTYPE HTML>
<html lang = "ja">
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../css/style.css" type="text/css">
		<title>学生管理システム</title>
	</head>

	<body>
		<header>
			<div>
				<h2>得点管理システム</h2>
			</div>
			<c:if test="${user != null}">
				<small>${ user.name }様</small>
				<small><a href = "Logout.action">ログアウト</a></small>
			</c:if>
		</header>