<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>

	<div id = "main-content" style = "margin: 0 30px;">
		<%-- 見出し --%>
		<h2 id = "headline">学生情報追加</h2>

		<div id = "menu">
			<p>変更が完了しました
		</div>

		<a href = "StudentList.action">学生一覧</a>

	</div>
</div>

<%@include file = "../tool/footer.jsp" %>