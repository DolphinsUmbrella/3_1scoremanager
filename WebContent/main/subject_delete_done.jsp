<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<h2 id = "headline">科目情報削除</h2>
		<p>削除が完了しました</p>

		<div id = "menu">

		</div>

		<a href = "SubjectList.action">科目一覧</a>

	</div>

<%@include file = "../tool/footer.jsp" %>