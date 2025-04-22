<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>

	<div id = "main-content" style = "margin: 0 30px;">
		<%-- 見出し --%>
		<h2 id = "headline">学生管理</h2>

		<a href = "StudentCreate.action"
		   style = "float: right;">新規追加</a>

		<div id = "menu"
			 style = "height: 100px">
			<form action = "StudentList.action">
				<div class = "filter-select">
					<%-- 入学年度 --%>
					<%-- 次の年度から10年間 --%>
					<p>入学年度</p>
					<select name = "entYear">
						<option value = "0" selected>--------</option>
						<c:forEach var="y" items="${ year }">
							<option value = "${ y }">${ y }</option>
						</c:forEach>
					</select>
				</div>

				<style>
					.filter-select {
						float: left;
						width: 100px
					}
				</style>
				<div class = "filter-select">
					<%-- クラス --%>
					<p>クラス</p>
					<select name = "classNum">
						<option value = "000" selected>--------</option>
						<c:forEach var="c" items="${ cList }">
							<option value = "${ c.getClassNum() }">${ c.getClassNum() }</option>
						</c:forEach>
					</select>
				</div>

				<div class = "filter-select">
					<%-- 在学中チェックボックス --%>
					<%-- チェックしてないことを検知するやつ、falseを持たせる --%>
					<input type = "checkbox"
						   id = "isAttend"
						   name = "isAttend"
						   value = "true" checked>
					<label for = "isAttend">在学中</label>
					<input type = "hidden" value = "false" name = "isAttend">
				</div>

				<input type = "submit" value = "絞込み">
			</form>
		</div>

		<div id = "list">
			<small>検索結果：${ sList.size() }件</small>
		</div>

		<div>
			<table style = "width: 800px">
				<%-- ボタンをリンクの見た目に変えるやつ --%>
				<%-- Gemini産。ホンマありがとう --%>
				<style>
					.link-button {
						background: none;
						color: blue; /* リンクのテキスト色 */
						border: none;
						padding: 0;
						font: inherit;
						cursor: pointer;
						text-decoration: underline; /* 下線 */
					}

					.link-button:hover {
						text-decoration: none; /* ホバー時の下線削除 */
					}

					.link-button:focus {
						outline: none; /* フォーカス時のデフォルトの枠線を削除 */
						/* 必要であれば、独自のフォーカススタイルを追加 */
						text-decoration: none;
					}
					th, td {
						text-align: center;
					}
				</style>

				<tr>
					<th>入学年度</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>クラス</th>
					<th>在学中</th>
					<th></th>
				</tr>
				<c:forEach var="s" items="${ sList }">
					<form action = "StudentUpdate.action" method = "post">
						<input type = "hidden" name = "no" value = "${ s.getNo() }">
						<tr>
							<td>${ s.getNo() }</td>
							<td>${ s.getName() }</td>
							<td>${ s.getEntYear() }</td>
							<td>${ s.getClassNum() }</td>
							<td>${ s.getIsAttend() }</td>
							<td><input type = "submit" class = "link-button" value = "変更"></td>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>

	</div>
</div>

<%@include file = "../tool/footer.jsp" %>