<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "tool/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "java.util.Objects" %>



<div class="login-content">
  <form action = "LoginExecute.action" method = "post">
    <h1>ログイン</h1>

    <div>
			<input type = "text" name = "teacher_id" placeholder="ID (半角の入力)" value="${teacher_id}" required="required">
		</div>
		<div>
			<input type = "password" name = "password" id ="password" placeholder="Password（30文字以内の半角英数字）" required="required">
		</div>
		<div><input type="checkbox" id="toggleCheckbox" />
		<label for="toggleCheckbox">パスワードを表示する</label></div>
    <div><input type = "submit" value = "ログイン"></div>

		<div class="error-message">
      <c:if test="${ message != null}">
        <small>${ message }</small>
      </c:if>
    </div>

  </form>
</div>

<%@include file = "tool/footer.jsp" %>