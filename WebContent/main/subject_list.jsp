<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し -%>
		<h2 id = "headline">科目管理</h2>

		<div id = "menu">

		</div>

		<a href = "SubjectCreate.action">新規登録</a>
		<a href = "SubjectUpdate.action">ダミー科目</a>
		<a href = "SubjectDelete.action">ダミー科目削除</a>

	</div>
--%>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>
</div>
<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>科目管理</h2>

		<%-- 新規追加リンク --%>
		<a href = "SubjectCreate.action" class = "link-button-headline">新規追加</a>
	</div>

	<div>
		<p style = "color:red;">${ noSubMessage }</p>
	</div>
	<c:if test="${ subList.size() > 0 }">
		<table>
			<tr>
				<th>科目コード</th>
				<th>科目名</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="s" items="${ subList }">
				<tr>
					<td>${ s.getCd() }</td>
					<td>${ s.getName() }</td>
					<td>
						<form action = "SubjectUpdate.action" method = "post">
							<input type = "submit" class = "link-button" value = "変更">
							<input type = "hidden" name = "cd" value = "${ s.getCd() }">
						</form>
					</td>
					<td>
						<form action = "SubjectDelete.action" method = "post">
							<input type = "submit" class = "link-button" value = "削除">
							<input type = "hidden" name = "cd" value = "${ s.getCd() }">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>

<%@include file = "../tool/footer.jsp" %>