<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<h2 id = "headline">成績参照</h2>
		<p>科目別検索結果

		<div id = "menu">

		</div>

		<div>
			<small>科目：${ subjectName }</small>
		</div>
		<table>
			<tr>
				<th>入学年度</th>
				<th>クラス</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>1回</th>
				<th>2回</th>
			</tr>
			<c:forEach var="t" items="${ tsubList }">
					<tr>
						<td>${ t.getEntYear() }</td>
						<td>${ t.getClassNum() }</td>
						<td>${ t.getStudentNo() }</td>
						<td>${ t.getStudentName() }</td>
						<td>
							<c:choose>
								<c:when test="${ t.getPoint(1) != null }">
									${ t.getPoint(1) }
								</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${ t.getPoint(2) != null }">
									${ t.getPoint(2) }
								</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</td>
					</tr>
			</c:forEach>
		</table>

	</div>

<%@include file = "../tool/footer.jsp" %>