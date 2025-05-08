<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file = "../tool/sidebar.jsp" %>
<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>学生管理</h2>

		<%-- 新規追加リンク --%>
		<a href = "StudentCreate.action">新規追加</a>
	</div>
	<div class="filter-group">

			<form action = "StudentList.action">
				<%-- フィルタリングコントロール全体を囲むGridコンテナ --%>
				<div class="serach-box">
					<%-- ラベルの項目 --%>
					<span class="grid-item label-entyear">入学年度</span>
					<span class="grid-item label-classnum">クラス</span>
					<span class="grid-item label-isattend">在学中</span>
					<%-- 「絞込み」ボタンのラベルは不要 --%>

					<%-- 入力要素の項目 --%>
					<span class="grid-item input-entyear">
						<%-- 入学年度のセレクトボックス --%>
						<select name = "entYear">
							<option value = "0" selected>-------</option>
							<c:forEach var="y" items="${ year }">
								<option value = "${ y }">${ y }</option>
							</c:forEach>
						</select>
					</span>

					<span class="grid-item input-classnum">
						<%-- クラスのセレクトボックス --%>
						<select name = "classNum">
							<option value = "000" selected>-------</option>
							<c:forEach var="c" items="${ cList }">
								<option value = "${ c }">${ c }</option>
							</c:forEach>
						</select>
					</span>

					<%-- 在学中チェックボックスと非表示フィールド --%>
					<span class="grid-item input-isattend">
						<input type = "checkbox" id = "isAttend" name = "isAttend" value = "true" checked>
						<input type = "hidden" value = "false" name = "isAttend">
						<%-- ラベルテキストはGridの別のセルに配置 --%>
					</span>

					<%-- 絞込みボタン --%>
					<span class="grid-item input-submit">
						<input type = "submit" value = "絞込み">
					</span>
				</div>
			</form>

		</div>


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
						<td>${ s.getIsAttend() }</td>
						<td>
						<form action = "StudentUpdate.action" method = "post">
							<input type = "submit" class = "link-button" name="" value = "変更">
							<input type = "hidden" name = "no" value = "${ s.getNo() }">
						</form>
						</td>
					</tr>
			</c:forEach>
		</table>
</div>




<%@include file = "../tool/footer.jsp" %>