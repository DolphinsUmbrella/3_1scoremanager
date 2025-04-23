<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<div class="header">
			<h2>学生情報追加</h2>
		</div>

		<div id = "menu">
			<p>変更が完了しました
		</div>

		<a href = "StudentList.action">学生一覧</a>

	</div>


<%@include file = "../tool/footer.jsp" %>