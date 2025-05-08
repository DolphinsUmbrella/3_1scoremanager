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

<<<<<<< HEAD
		<div>
		<p>データを更新する学生を選択してください</p>
			<select name="count">
			<option value="1">----------------</option>
			<p>ここにいろいろ
		</div>
=======
		<div class="align-left">
	<form action = "StudentCreateExecute.action">
>>>>>>> branch 'master' of https://github.com/DolphinsUmbrella/3_1scoremanager.git

		<p>入学年度：<input type = "text" name = "entYear" placeholder="入学年度を入力"></p>

		<p>学生番号：<input type = "text" name = "classNum" placeholder="学生番号を入力"></p>

		<p>　　氏名：<input type = "text" name = "name" placeholder="氏名を入力">
		</p>

		<p>　クラス：
			<select name = "classNum">
				<option value = "none">----</option>
				<c:forEach var="c" items="${ cList }">
					<option value = "${ c }">${ c }</option>
				</c:forEach>
			</select>
		</p>

		<p><label for = "isAttend">　在学中：</label>
			<input type = "checkbox" id = "isAttend" name = "isAttend" value = "true" checked>
			<input type = "hidden" value = "false" name = "isAttend">
		</p>

		<p><input type = "submit" value = "登録"></p>
		<p><a href = "StudentList.action">戻る</a></p>
	</form>
	</div>


	</div>


<%@include file = "../tool/footer.jsp" %>