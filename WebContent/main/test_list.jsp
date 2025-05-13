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
	<%-- 科目別検索フォーム --%>
	<div class="filter-group">

		<form action = "TestListSubjectExecute.action" method = "post">
			<div class="subject-serach-box">

			<span class="input-submit">
				<input type = "submit" value = "検索">
			</span>
				<span class="label-1"><b>科目</b></span>
				<span class="label-2">入学年度</span>
				<span class="label-3">クラス</span>
				<span class="label-4">科目</span>

			<span class="input-2">
				<select name = "ent_year">
					<!-- <select name = "entYear"> -->
					<option value = "0" selected>-------</option>
					<c:forEach var="y" items="${ year }">
						<c:choose>
							<c:when test="${ y == entYear }">
								<option value = "${ y }" selected>${ y }</option>
							</c:when>
							<c:otherwise>
								<option value = "${ y }">${ y }</option>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</select>
			</span>


			<span class="input-3">
				<select name = "class_num">
					<!-- <select name = "classNum">? -->
					<option value = "000">-------</option>
					<c:forEach var="c" items="${ cList }">
						<c:choose>
							<c:when test="${ c == classNum }">
								<option value = "${ c }" selected>${ c }</option>
							</c:when>
							<c:otherwise>
								<option value = "${ c }">${ c }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</span>


			<span class="input-4">
				<select name = "subject">
					<option value = "000">----</option>
					<c:forEach var="s" items="${ subList }">
						<c:choose>
							<c:when test="${ s.getCd() == subjectCd }">
								<option value = "${ s.getCd() }" selected>s.getName()</option>
							</c:when>
							<c:otherwise>
								<option value = "${ s.getCd() }">s.getName()</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</span>

			</div>
		</form>
	</div>

	<%-- 学生別検索フォーム --%>
	<div class="filter-group">
		<form action = "TestListStudentExecute.action" method = "post">

			<div class="subject-serach-box">
				<span class="input-submit">
					<input type = "submit" value = "検索">
				</span>

				<span class="label-1"><b>学生</b></span>
				<span class="label-2">学生番号</span>
				<span class="input-2">
					<input type = "text" name = "no">
				</span>
			</div>
		</form>
	</div>
</div>


<%@include file = "../tool/footer.jsp" %>