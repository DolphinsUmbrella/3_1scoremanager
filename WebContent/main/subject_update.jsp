<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>科目情報変更</h2>
	</div>
	<div class="align-left">
	<form action = "SubjectUpdateExecute.action" method = "post">

		<p>科目コード：${ subject.getCd() }</p>
		<input type = "hidden" name = "cd" value = "${ subject.getCd() }">

		<p>　　科目名：
			<input type = "text"
				   name = "name"
				   value="${ subject.getName() }"
				   required="required">
		</p>

		<p><input type = "submit" value = "変更"></p>
		<p><a href = "SubjectList.action">戻る</a></p>
	</form>
	</div>

</div>

<%@include file = "../tool/footer.jsp" %>