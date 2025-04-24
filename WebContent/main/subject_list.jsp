<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<h2 id = "headline">科目管理</h2>

		<div id = "menu">

		</div>

		<a href = "SubjectCreate.action">新規登録</a>
		<a href = "SubjectUpdate.action">ダミー科目</a>
		<a href = "SubjectDelete.action">ダミー科目削除</a>

	</div>

<%@include file = "../tool/footer.jsp" %>