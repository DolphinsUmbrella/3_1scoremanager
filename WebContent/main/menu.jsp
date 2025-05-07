<%@page contentType = "text/html; charset=UTF-8" %>
<%@include file = "../tool/header.jsp" %>
<style>
/* 画面幅が769px以上のときに適用するスタイル */
@media screen and (min-width: 769px) {
    /* main-content内の直接の子要素（.button-group）を横並びに配置 */
    .main-content {
        display: flex; /* フレックスコンテナにする */
        flex-direction: row; /* 子要素を横方向に並べる */
        gap: 20px; /* 子要素間の隙間（例: 15px） */
    }

    /* 各ボタンのグループ (.button-group) のスタイル */
    .button-group {
        flex: 1; /* 利用可能なスペースを均等に分配し、幅を統一する */
        min-width: 0; /* Flexアイテムの縮小時の問題を回避 */
        /* 縦並び時の下マージンは不要になるためリセット（外部CSSで定義されている場合） */
        /* margin-bottom: 0; */
        /* グループ内の要素を中央揃え（必要に応じて） */
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    /* 成績管理のボタンを横並びにするための内側のdivのスタイル */
    .button-group > div {
        display: flex; /* フレックスコンテナにする */
        flex-direction: row; /* ボタンを横方向に並べる */
        gap: 20px; /* ボタン間の隙間（例: 20px） */
        align-items: center; /* 縦方向の中央揃え */
    }
}

/*-------------------------------------------------------------*/
/* ボタンのグループ */
.button-group {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-bottom: 20px;
	/* グループ間の下マージンを少し狭める */
	padding: 25px;
	/* グループ内の余白を広げる */
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #f9f9f9;
	width: 80%;
	box-sizing: border-box;
}


.button-group h3 {
	margin-bottom: 25px;
	/* タイトルとボタンの間隔を広げる */
	text-align: center;
}

/* 学生管理、成績管理、科目管理のボタンコンテナ */
.main-button-container {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 20px;
	/* 他の要素との間隔を少し狭める */
	padding: 0 20px;
	box-sizing: border-box;
}

/* 成績管理のボタンを横並びにするコンテナ */
.grade-buttons-container {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
	/* ボタン間の下マージン（縦並び時）を広げる */
}

.grade-buttons-container .btn-circle-border-simple {
	margin-right: 25px;
	/* ボタン間の右マージン（横並び時）を広げる */
	margin-bottom: 15px;
	/* ボタン間の下マージン（縦並び時）を広げる */
	width: 140px;
	/* ボタンの幅 */
	height: 140px;
	/* ボタンの高さを少し大きく */
	line-height: 140px;
	/* 行の高さを調整して中央揃え */
	font-size: 1.1em;
	/* フォントサイズを少し大きく */
}
</style>

<div>
  <%@include file = "../tool/sidebar.jsp" %>
</div>

<div class="main-content">
	<div class="header">
		<h2>メニュー</h2>
	</div>


	<div class="button-group">
		<h3>学生管理</h3>
		<a href="StudentList.action"class="btn-circle-border-simple student-btn">学生管理</a>
	</div>

	<div class="button-group">
		<h3>成績管理</h3>
		<%-- 成績管理のボタンを横並びにするためのdiv --%>
		<div style="display: flex; align-items: center;">
		<a href="TestRegist.action" class="btn-circle-border-simple grade-btn">成績登録</a>
		<a href="TestList.action" class="btn-circle-border-simple grade-btn">成績参照</a>
		</div>
	</div>

	<div class="button-group">
		<h3>科目管理</h3>
		<a href="SubjectList.action" class="btn-circle-border-simple subject-btn">科目管理</a>
	</div>
</div>


<%@include file = "../tool/footer.jsp" %>