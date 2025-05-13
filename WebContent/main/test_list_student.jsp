<%@page contentType = "text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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