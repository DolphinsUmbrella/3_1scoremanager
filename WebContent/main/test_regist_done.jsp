<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<div class= "header">
		<%-- 見出し --%>
		<h2>成績登録</h2>
		</div>

		<p>登録が完了しました</p>

		<a href = "TestRegist.action">戻る</a>
		<a href = "TestList.action">成績参照</a>

	</div>

<%@include file = "../tool/footer.jsp" %>