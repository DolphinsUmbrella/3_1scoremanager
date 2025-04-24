<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<h2 id = "headline">成績参照</h2>

		<div id = "menu">

		</div>

		<a href = "TestListSubjectExecute.action">科目別検索</a>
		<a href = "TestListStudentExecute.action">学生別検索</a>

	</div>

<%@include file = "../tool/footer.jsp" %>