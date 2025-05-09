<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<div class = "header">
			<h2>成績登録</h2>
		</div>

		<%-- 科目別検索フォーム --%>
		<div class="filter-group">

			<form action = "TestListSubjectExecute.action" method = "post">
				<div class="test-input-serach-box">

				<span class="input-submit">
					<input type = "submit" value = "検索">
				</span>

				<span class="label-1">入学年度</span>
				<span class="label-2">クラス</span>
				<span class="label-3">科目</span>
				<span class="label-4">回数</span>

				<span class="input-1">
					<select name = "ent_year">
						<!-- <select name = "entYear"> -->
						<option value = "0" selected>-------</option>
						<c:forEach var="y" items="${ year }">
							<option value = "${ y }">${ y }</option>
						</c:forEach>
					</select>
				</span>


				<span class="input-2">
					<select name = "class_num">
					<!-- <select name = "classNum">? -->
						<option value = "000" selected>-------</option>
						<c:forEach var="c" items="${ cList }">
							<option value = "${ c }">${ c }</option>
						</c:forEach>
					</select>
				</span>


				<span class="input-3">
					<select name = "subject" selected>
						<option value = "000">----</option>
						<option value = "101">python</option>
						<option value = "102">java</option>
						<option value = "103">js</option>
					</select>
				</span>

				<span class="input-4">
					<select name = "count">
						<option value = "0">----</option>
						<option value = "1">1</option>
						<option value = "2">2</option>

					</select>
				</span>

				</div>
			</form>
		</div>


		<div>
			<a href = "TestRegistExecute.action">登録して終了</a>
		</div>

	</div>


<%@include file = "../tool/footer.jsp" %>