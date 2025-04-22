<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style = "display: flex;">
	<%@include file = "../tool/sidebar.jsp" %>

	<div id = "main-content" style = "margin: 0 30px;">
		<%-- 見出し --%>
		<h2 id = "headline">成績参照</h2>
		<p>科目別検索結果

		<div id = "menu">

		</div>

	</div>
</div>

<%@include file = "../tool/footer.jsp" %>