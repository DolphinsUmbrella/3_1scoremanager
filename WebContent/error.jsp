<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>エラーページ</h2>
	</div>
		<p>エラーが発生しました。</p>
		<p><a href = "StudentList.action">戻る</a></p>

</div>


<%@include file = "../tool/footer.jsp" %>