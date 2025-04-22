<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>

	<div id = "main-content" style = "margin: 0 30px;">
		<%-- 見出し --%>
		<h2 id = "headline">メニュー</h2>

		<div id = "menu">
			<form action = StudentList.action method = "post">
				<div id = "box-red">
					<input type = "submit"
						   value = "学生管理"
						   class = "link-button">
				</div>
			</form>

			<div id = "box-green">
				<div>
					<p>成績管理</p>
					<p><a href = "TestRegist.action">成績登録</a></p>
					<p><a href = "TestList.action">成績参照</a></p>
				</div>
			</div>

			<div id = "box-blue">
				<a href = "SubjectList.action">科目管理</a>
			</div>
		</div>

	</div>
</div>

<%@include file = "../tool/footer.jsp" %>