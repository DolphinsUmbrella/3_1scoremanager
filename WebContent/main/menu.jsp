<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>

	<div class = "main-content">
		<%-- 見出し --%>
		<div class="header">
			<h2>メニュー</h2>
		</div>

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

<%@include file = "../tool/footer.jsp" %>