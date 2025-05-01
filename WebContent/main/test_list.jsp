<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<div class="header">
			<h2>成績参照</h2>
		</div>

		<form action = "TestListSubjectExecute.action" method = "post">
			<p>
				入学年度：
				<select name = "ent_year">
					<option value = "2024">2024</option>
					<option value = "2025">2025</option>
				</select>
			</p>

			<p>
				クラス：
				<select name = "class_num">
					<option value = "102">102</option>
					<option value = "201">201</option>
				</select>
			</p>

			<p>
				科目：
				<select name = "subject">
					<option value = "101">python</option>
					<option value = "102">java</option>
					<option value = "103">js</option>
				</select>
			</p>

			<p><input type = "submit" value = "科目別検索">
		</form>

		<a href = "TestListStudentExecute.action">学生別検索</a>

	</div>

<%@include file = "../tool/footer.jsp" %>