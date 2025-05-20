<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "java.util.Objects" %>



<div class="login-content">
	<form action = "LoginExecute.action" method = "post">
		<h1>ログイン</h1>

		<div class="error-message">
			<c:if test="${ message != null}">

				<small>${ message }</small>
			</c:if>
		</div>


		<p><input type = "text" name = "teacher_id" placeholder="ID" value="${teacher_id}" required="required"><br>
		<input type = "password" name = "password" placeholder="password" required="required"></p>
		<p><input type = "submit" value = "ログイン"></p>
	</form>
</div>

<%@include file = "tool/footer.jsp" %>