<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<div class = "header">
			<h2>成績参照（学生別検索結果）</h2>
		</div>

		<div>
			<small>氏名：${ student.getName() }(${ student.getNo() })</small>
		</div>
		<table>
			<tr>
				<th>科目名</th>
				<th>科目コード</th>
				<th>回数</th>
				<th>点数</th>
			</tr>
			<c:forEach var="t" items="${ tstuList }">
					<tr>
						<td>${ t.getSubjectName() }</td>
						<td>${ t.getSubjectCd() }</td>
						<td>${ t.getNum() }</td>
						<td>${ t.getPoint() }</td>
					</tr>
			</c:forEach>
		</table>

	</div>

<%@include file = "../tool/footer.jsp" %>