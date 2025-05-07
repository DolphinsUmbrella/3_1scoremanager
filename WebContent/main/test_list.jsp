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

		<div class="align-left">
		<form action = "TestListSubjectExecute.action" method = "post">
			<p>
				入学年度：
				<select name = "ent_year">
				<!-- <select name = "entYear"> -->
				<option value = "0" selected>-------</option>
				<c:forEach var="y" items="${ year }">
					<option value = "${ y }">${ y }</option>
				</c:forEach>
			</select>
			</p>

			<p>
				　クラス：
				<select name = "class_num">
				<!-- <select name = "classNum">? -->
				<option value = "000" selected>-------</option>
				<c:forEach var="c" items="${ cList }">
					<option value = "${ c }">${ c }</option>
				</c:forEach>
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
		<div>
			<a href = "TestListStudentExecute.action">学生別検索</a>
		</div>
		</div>

<<<<<<< HEAD
		<form action = "TestListStudentExecute.action" method = "post">
			<p>
				学生番号：
				<input type = "text" name = "no">
			</p>

			<p><input type = "submit" value = "学生別検索">
		</form>
=======
>>>>>>> branch 'master' of https://github.com/DolphinsUmbrella/3_1scoremanager.git

	</div>

<%@include file = "../tool/footer.jsp" %>