<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "java.util.Objects" %>


<c:if test="${ message != null}">
		<p>${ message }</p>
	</c:if>

<div class="login-content">
	<form action = "LoginExecute.action" method = "post">
		<h1>ログイン</h1>
		<p><input type = "text" name = "teacher_id"><br>
		<input type = "password" name = "password"></p>
		<p><input type = "submit" value = "ログイン"></p>
	</form>
</div>

<%@include file = "tool/footer.jsp" %>