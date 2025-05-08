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

	<div class="filter-group">
		<form action = "TestListSubjectExecute.action" method = "post">
			<div class="subject-serach-box">
				<span class="grid-item label-1">入学年度</span>
				<span class="grid-item label-2">クラス</span>
				<span class="grid-item label-3">科目</span>

			<span class="grid-item input-1">
				<select name = "ent_year">
					<!-- <select name = "entYear"> -->
					<option value = "0" selected>-------</option>
					<c:forEach var="y" items="${ year }">
						<option value = "${ y }">${ y }</option>
					</c:forEach>
				</select>
			</span>


			<span class="grid-item input-2">
				<select name = "class_num">
					<!-- <select name = "classNum">? -->
					<option value = "000" selected>-------</option>
					<c:forEach var="c" items="${ cList }">
						<option value = "${ c }">${ c }</option>
					</c:forEach>
				</select>
			</span>


			<span class="grid-item input-3">
				<select name = "subject">
					<option value = "101">python</option>
					<option value = "102">java</option>
					<option value = "103">js</option>
				</select>
			</span>

			<input type = "submit" value = "科目別検索">

			</div>
		</form>

		<form action = "TestListStudentExecute.action" method = "post">
			<p>
				学生番号：
				<input type = "text" name = "no">
			</p>

			<input type = "submit" value = "学生別検索">
		</form>
	</div>
</div>

<%@include file = "../tool/footer.jsp" %>