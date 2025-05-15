<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>科目情報削除</h2>
	</div>
	<div class="align-left">
		<form action = "SubjectDeleteExecute.action" method = "post">
			<input type = "hidden" name = "cd" value = "${ subject.getCd() }">

			<p>「${ subject.getName() }(${ subject.getCd() })」を削除してもよろしいですか</p>

			<p><input  class = "button-red" type = "submit" value = "削除"></p>
		</form>
		<p><a href = "SubjectList.action">戻る</a></p>
	</div>

</div>

<%@include file = "../tool/footer.jsp" %>