<%@page contentType = "text/html; charset=UTF-8" %>

<%-- サイドバー --%>
<div>
	<%-- ログイン状態でないなら中身は空にしてください --%>

	<%-- ナビゲーション --%>
	<nav>
		<ul>
			<li>
				<%-- mainへ --%>
				<a href = "Menu.action">メニュー</a>
			</li>

			<li>
				<%-- 学生管理一覧へ --%>
				<a href = "StudentList.action">学生管理</a>
			</li>

			<li>
				<a href = "TestRegist.action">成績登録</a>
			</li>

			<li>
				<a href = "TestList.action">成績参照</a>
			</li>

			<li>
				<a href = "SubjectList.action">科目管理</a>
			</li>
		</ul>
	</nav>
</div>