<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

	<div class = "main-content">
		<div class= "header">
		<%-- 見出し --%>
		<h2>成績管理</h2>
		</div>

		<p>登録が完了しました</p>

		<h4>成績登録へ → <a href = "TestRegist.action">戻る</a></h4>

		<h4>成績参照へ → <a href = "TestList.action">移動</a></h4>

	</div>

<%@include file = "../tool/footer.jsp" %>