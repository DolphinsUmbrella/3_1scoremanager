<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file = "../tool/sidebar.jsp" %>

<div class = "main-content">
	<%-- 見出し --%>
	<div class = "header">
		<h2>成績管理</h2>
	</div>

	<%-- 成績検索フォーム --%>
	<div class="filter-group">

		<form action = "TestRegist.action" method = "post">
			<div class="test-input-serach-box">

				<span class="input-submit">
					<input type = "submit" value = "検索">
				</span>

				<span class="label-1">入学年度</span>
				<span class="label-2">クラス</span>
				<span class="label-3">科目</span>
				<span class="label-4">回数</span>

				<span class="input-1">

					<!-- 入学年度をJavaに送信する時の変数 -->
					<select name = "ent_year">

						<option value = "0" >-------</option>
						<c:forEach var="y" items="${ year }">
							<c:choose>
								<c:when test="${ y == entYear }">
									<option value = "${ y }" selected>${ y }</option>
								</c:when>
								<c:otherwise>
									<option value = "${ y }">${ y }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</span>


				<span class="input-2">

					<!-- クラス番号をJavaに送信する時の変数 -->
					<select name = "class_num">

					<!-- <select name = "classNum">? -->
						<option value = "000" selected>-------</option>
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
				</span>

				<span class="input-3">

					<!-- 科目をJavaに送信する時の変数 -->
					<select name = "subject">

						<option value = "000">----</option>
						<c:forEach var="s" items="${ subList }">
							<c:choose>
								<c:when test="${ s.getCd() == subject.getCd() }">
									<option value = "${ s.getCd() }" selected>${ s.getName() }</option>
								</c:when>
								<c:otherwise>
									<option value = "${ s.getCd() }">${ s.getName() }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</span>

				<span class="input-4">
					<select name = "count">
						<option value = "0">----</option>
						<c:forEach var="i" begin="1" end="2">
							<c:choose>
								<c:when test="${ i == count }">
									<option value = "${ i }" selected>${ i }</option>
								</c:when>
								<c:otherwise>
									<option value = "${ i }">${ i }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</span>

			</div>
		</form>
	</div>

	<div>
		<p style = "color:red;">${ nullParameterMessage }</p>
		<p style = "color:red;">${ noTestMessage }</p>
	</div>

	<%-- 検索結果がある場合は表示 --%>
	<c:if test="${ tList.size() > 0 }">
		<div>
			<small>科目：${ subject.getName() }(${ count }回)</small>
		</div>

		<form action = "TestRegistExecute.action" method = "post" class="table-form">
			<table>
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>点数</th>
					<!-- <th>削除</th> -->
				</tr>
				<c:forEach var="t" items="${ tList }">
					<tr>
						<td>${ t.getStudent().getEntYear() }</td>
						<td>${ t.getStudent().getClassNum() }</td>
						<td>${ t.getStudent().getNo() }</td>
						<td>${ t.getStudent().getName() }</td>
						<td>
							<c:choose>
								<c:when test="${ t.getPoint() > 0 }">
									<input type = "number" min="0" max="100"
										   name = "point_${ t.getStudent().getNo() }"
										   value = "${ t.getPoint() }">
								</c:when>
								<c:otherwise>
									<input type = "number" min="0" max="100"
										   name = "point_${ t.getStudent().getNo() }">
								</c:otherwise>
							</c:choose>
						</td>
						<!-- 仮の削除ボタンの作成
						<td>
							<input type = "checkbox" id = "test_delete" name = "test_delete" value = "true" checked>
							<input type = "hidden" value = "false" name = "test_delete">
						</td>
						-->
					</tr>
				</c:forEach>
			</table>
			<div><input type = "submit" value="登録して終了"></div>
		</form>
	</c:if>
</div>

<%@include file = "../tool/footer.jsp" %>