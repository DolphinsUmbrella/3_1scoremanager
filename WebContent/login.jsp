<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "java.util.Objects" %>

<h2>ログイン</h2>

<c:if test="${ message != null}">
	<p>${ message }</p>
</c:if>
<form action = "LoginExecute.action" method = "post">
	<p>id: <input type = "text" name = "teacher_id"></p>
	<p>パスワード: <input type = "text" name = "password"></p>
	<p><input type = "submit" value = "ログイン"></p>
</form>

<%@include file = "tool/footer.jsp" %>