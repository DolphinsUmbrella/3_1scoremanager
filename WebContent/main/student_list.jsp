<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file = "../tool/sidebar.jsp" %>
<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>学生管理</h2>
	</div>

	<a href = "StudentCreate.action" style = "float: right;">新規追加</a>

	<div>
		<form action = "StudentList.action">

			<%-- 入学年度 --%>
			<%-- 次の年度から10年間 --%>
			<p>
			入学年度
			<select name = "entYear">
				<option value = "0" selected>--------</option>
				<c:forEach var="y" items="${ year }">
					<option value = "${ y }">${ y }</option>
				</c:forEach>
			</select>

			<%-- クラス --%>
			クラス
			<select name = "classNum">
				<option value = "000" selected>--------</option>
				<c:forEach var="c" items="${ cList }">
					<option value = "${ c.getClassNum() }">${ c.getClassNum() }</option>
				</c:forEach>
			</select>

			<%-- 在学中チェックボックス --%>
			<%-- チェックしてないことを検知するやつ、falseを持たせる --%>
			<input type = "checkbox" id = "isAttend" name = "isAttend" value = "true" checked>
			<label for = "isAttend">在学中</label>
			<input type = "hidden" value = "false" name = "isAttend">
			<input type = "submit" value = "絞込み">
			</p>
		</form>

		<div>
			<small>検索結果：${ sList.size() }件</small>
			<table>
				<tr>
					<th>入学年度</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>クラス</th>
					<th>在学中</th>
					<th></th>
				</tr>
				<c:forEach var="s" items="${ sList }">
						<input type = "hidden" name = "no" value = "${ s.getNo() }">
						<tr>
							<td>${ s.getEntYear() }</td>
							<td>${ s.getNo() }</td>
							<td>${ s.getName() }</td>
							<td>${ s.getClassNum() }</td>
							<td>${ s.getIsAttend() }</td>
							<td>
							<form action = "StudentUpdate.action" method = "post">
							<input type = "submit" class = "link-button" value = "変更">
							</form>
							</td>
						</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>




<%@include file = "../tool/footer.jsp" %>