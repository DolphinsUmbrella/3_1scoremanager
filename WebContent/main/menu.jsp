<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>

<div>
  <%@include file = "../tool/sidebar.jsp" %>
</div>

<div class="main-content">
	<div class="header">
		<h2>メニュー</h2>
	</div>


	<div class="button-group">
		<h3>学生管理</h3>
		<a href="StudentList.action"class="btn-circle-border-simple student-btn">学生管理</a>
	</div>

	<div class="button-group">
		<h3>成績管理</h3>
		<div style="display: flex; align-items: center;">
		<a href="TestRegist.action" class="btn-circle-border-simple grade-btn">成績登録</a>
		<a href="TestList.action" class="btn-circle-border-simple grade-btn">成績参照</a>
		</div>
	</div>

	<div class="button-group">
		<h3>科目管理</h3>
		<a href="SubjectList.action" class="btn-circle-border-simple subject-btn">科目管理</a>
	</div>
</div>


<%@include file = "../tool/footer.jsp" %>