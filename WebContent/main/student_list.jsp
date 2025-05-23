<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file = "../tool/sidebar.jsp" %>
<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>学生管理</h2>

		<%-- 新規追加リンク --%>
		<a href = "StudentCreate.action"class = "link-button-headline">新規追加</a>
	</div>
	<div class="filter-group">
		<form action = "StudentList.action" method = "post">
			<div class="student-serach-box">
				<span class="label-1">入学年度</span>
				<span class="label-2">クラス</span>
				<span class="label-3">在学中</span>


				<%-- 入学年度のセレクトボックス --%>
				<span class="input-1">
					<select name = "entYear">
						<option value = "0" selected>-------</option>
						<c:forEach var="y" items="${ year }">
							<c:choose>
								<c:when test="${ y == entYear}">
									<option value = "${ y }" selected>${ y }</option>
								</c:when>
								<c:otherwise>
									<option value = "${ y }">${ y }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</span>

				<%-- クラスのセレクトボックス --%>
				<span class="input-2">
					<select name = "classNum">
						<option value = "000" selected>-------</option>
						<c:forEach var="c" items="${ cList }">
							<c:choose>
								<c:when test="${ c == classNum}">
									<option value = "${ c }" selected>${ c }</option>
								</c:when>
								<c:otherwise>
									<option value = "${ c }">${ c }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</span>

				<%-- 在学中チェックボックスと非表示フィールド --%>
				<span class="input-3">
					<input type = "checkbox" id = "isAttend" name = "isAttend" value = "true" checked>
					<input type = "hidden" value = "false" name = "isAttend">
				</span>

				<%-- 絞込みボタン --%>
				<span class="input-submit">
					<input type = "submit" value = "絞込み">
				</span>
			</div>
		</form>
	</div>

	<c:if test="${ sList.size() <= 0 }">
		<p>該当の学生が存在しません</p>
	</c:if>

	<c:if test="${ sList.size() > 0 }">
		<div>
			<small>検索結果：${ sList.size() }件</small>
		</div>
		<table>
			<tr>
				<th>入学年度</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>クラス</th>
				<th>在学中</th>
			</tr>
			<c:forEach var="s" items="${ sList }">
				<tr>
					<td>${ s.getEntYear() }</td>
					<td>${ s.getNo() }</td>
					<td>${ s.getName() }</td>
					<td>${ s.getClassNum() }</td>
					<c:choose>
						<c:when test="${ s.getIsAttend() != true}">
							<td>✕</td>
						</c:when>
						<c:otherwise>
							<td>○</td>
						</c:otherwise>
					</c:choose>
					<td>
						<form action = "StudentUpdate.action" method = "post">
							<input type = "submit"
								   class = "link-button"
								   name = ""
								   value = "変更">
							<input type = "hidden" name = "no" value = "${ s.getNo() }">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>

<%@include file = "../tool/footer.jsp" %>