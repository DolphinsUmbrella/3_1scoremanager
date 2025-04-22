<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>

	<div id = "main-content" style = "margin: 0 30px;">
		<%-- 見出し --%>
		<h2 id = "headline">科目情報変更</h2>

		<div id = "menu">

		</div>

		<a href = "SubjectUpdateExecute.action">登録</a>
		<a href = "SubjectList.action">戻る</a>

	</div>
</div>

<%@include file = "../tool/footer.jsp" %>