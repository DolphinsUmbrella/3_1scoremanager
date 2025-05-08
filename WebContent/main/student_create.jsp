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

		<div>
		<p>データを更新する学生を選択してください</p>
			<select name="count">
			<option value="1">----------------</option>
			<p>ここにいろいろ
		</div>

		<a href = "StudentCreateExecute.action">登録して終了</a>
		<a href = "StudentList.action">戻る</a>

	</div>


<%@include file = "../tool/footer.jsp" %>