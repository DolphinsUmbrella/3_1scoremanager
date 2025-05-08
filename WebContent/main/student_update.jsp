<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%@include file = "../tool/sidebar.jsp" %>
</div>

<<<<<<< HEAD
		<div id = "menu">
			<p>氏名</p>
<form action="Product.action" method="post">
  <input type="text" name="keyword">
</form>
			<p>ここにいろいろ
		</div>

		<a href = "StudentUpdateExecute.action">変更</a>
		<a href = "StudentList.action">戻る</a>

=======
<div class = "main-content">
	<%-- 見出し --%>
	<div class="header">
		<h2>学生情報変更</h2>
>>>>>>> branch 'master' of https://github.com/DolphinsUmbrella/3_1scoremanager.git
	</div>
	<div class="align-left">
	<form action = "StudentUpdateExecute.action">

		<p>入学年度：${ student.getEntYear() }</p>

		<p>学生番号：${ student.getNo() }</p>

		<p>　　氏名：
			<input type = "text" name = "name" placeholder="変更前：${ student.getName() }" value="${ student.getName() }">
		</p>
		<c:if test="${ not empty errorMessage }">
			<p style="color:red;">　　　　　[${ errorMessage }]</p>
		</c:if>

		<p>　クラス：
			<select name = "classNum">
				<c:forEach var="c" items="${ cList }">
					<option value = "${ c }">${ c }</option>
				</c:forEach>
			</select>
		</p>

		<p><label for = "isAttend">　在学中：</label>
			<input type = "checkbox" id = "isAttend" name = "isAttend" value = "true" checked>
			<input type = "hidden" value = "false" name = "isAttend">
		</p>

		<p><input type = "submit" value = "変更"></p>
		<p><a href = "StudentList.action">戻る</a></p>
	</form>
	</div>

</div>


<%@include file = "../tool/footer.jsp" %>