<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<%-- 見出し --%>
		<div class="header">
			<h2>学生情報追加</h2>
		</div>

		<div class="align-left">
	<form action = "StudentCreateExecute.action" method = "post">

		<p>
			入学年度：
			<select name = "entYear">
				<option value = "0">----</option>
				<c:forEach var="y" items="${ year }">
					<c:choose>
						<c:when test="${ y == entYear }">
							<option value = "${ y }" selected>${ y }
						</c:when>
						<c:otherwise>
							<option value = "${ y }">${ y }
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</p>

		<small class = "error-message">${ entYearMessage }</small>

		<p>
			学生番号：
			<input type = "text"
				   name = "no"
				   value = "${ no }"
				   placeholder="学生番号を入力"
				   required="required">
		</p>

		<small class = "error-message">${ studentNoMessage }</small>

		<p>
			　　氏名：
			<input type = "text"
				   name = "name"
				   value = "${ name }"
				   placeholder="氏名を入力"
				   required="required">
		</p>

		<p>　クラス：
			<select name = "classNum">
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
		</p>

		<p><label for = "isAttend">　在学中：</label>
			<c:choose>
				<c:when test="${ isAttend }">
					<input type = "checkbox"
						   id = "isAttend"
						   name = "isAttend"
						   value = "true"
						   checked>
				</c:when>
				<c:when test="${ isAttend == false }">
					<input type = "checkbox"
						   id = "isAttend"
						   name = "isAttend"
						   value = "true">
				</c:when>
				<c:otherwise>
					<input type = "checkbox"
						   id = "isAttend"
						   name = "isAttend"
						   value = "true"
						   checked>
				</c:otherwise>
			</c:choose>
		</p>

		<p><input type = "submit" value = "登録"></p>
		<p><a href = "StudentList.action">戻る</a></p>
	</form>
	</div>


	</div>


<%@include file = "../tool/footer.jsp" %>