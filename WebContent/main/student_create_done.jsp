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


			<p>登録が完了しました


		<a href = "StudentCreate.action">戻る</a>
		<a href = "StudentList.action">学生一覧</a>

	</div>


<%@include file = "../tool/footer.jsp" %>