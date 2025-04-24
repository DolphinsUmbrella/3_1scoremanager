<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>
	<div class = "main-content">
		<%-- 見出し --%>
		<div class="header">
			<h2>学生情報変更</h2>
		</div>

		<div id = "menu">
			<p>ここにいろいろ
		</div>

		<a href = "StudentUpdateExecute.action">変更</a>
		<a href = "StudentList.action">戻る</a>

	</div>


<%@include file = "../tool/footer.jsp" %>