<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>科目情報登録</h2>
	</div>
	<div class="align-left">
	<form action = "SubjectCreateExecute.action" method = "post">

		<p>
			科目コード：
			<input type = "text"
				   name = "cd"
				   value = "${ subjectCd }"
				   required="required">
		</p>
		<c:if test="${ subjectCdMessage != null }">
			<small class = "error-message">${ subjectCdMessage }</small>
		</c:if>

		<p>
			　　科目名：
			<input type = "text"
				   name = "name"
				   value = "${ subjectName }"
				   required="required">
		</p>
		<c:if test="${ subjectNameMessage != null }">
			<small class = "error-message">${ subjectNameMessage }</small>
		</c:if>

		<p><input type = "submit" value = "登録"></p>
		<p><a href = "SubjectList.action">戻る</a></p>
	</form>
	</div>

</div>

<%@include file = "../tool/footer.jsp" %>