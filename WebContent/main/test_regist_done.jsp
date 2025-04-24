<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<h2 id = "headline">成績登録</h2>
		<p>登録が完了しました

		<div id = "menu">

		</div>

		<a href = "TestRegist.action">戻る</a>
		<a href = "TestList.action">成績参照</a>

	</div>

<%@include file = "../tool/footer.jsp" %>