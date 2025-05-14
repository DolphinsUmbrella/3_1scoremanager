<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>学生情報変更</h2>
	</div>
	<div class="align-left">
	<form action = "StudentUpdateExecute.action" method = "post">

		<p>入学年度：${ student.getEntYear() }</p>

		<p>学生番号：${ student.getNo() }</p>

		<p>　　氏名：
			<input type = "text" name = "name" value="${ student.getName() }" required="required">
		</p>
		<c:if test="${ not empty errorMessage }">
			<p style="color:red;">　　　　　[${ errorMessage }]</p>
		</c:if>

		<p>　クラス：
			<select name = "classNum">
				<c:forEach var="c" items="${ cList }">
					<c:choose>
						<c:when test="${ c == student.getClassNum() }">
							<option value = "${ c }" selected>${ c }</option>
						</c:when>
						<c:otherwise>
							<option value = "${ c }">${ c }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</p>

		<p><label for = "isAttend">　在学中：</label>
			<c:choose>
				<c:when test="${ student.getIsAttend() }">
					<input type = "checkbox" id = "isAttend" name = "isAttend" value = "true" checked>
				</c:when>
				<c:otherwise>
					<input type = "checkbox" id = "isAttend" name = "isAttend" value = "true">
				</c:otherwise>
			</c:choose>
			<input type = "hidden" value = "false" name = "isAttend">
		</p>

		<p><input type = "submit" value = "変更"></p>
		<p><a href = "StudentList.action">戻る</a></p>
	</form>
	</div>

</div>


<%@include file = "../tool/footer.jsp" %>