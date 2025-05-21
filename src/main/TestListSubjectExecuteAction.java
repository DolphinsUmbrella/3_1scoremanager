package main;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		//値の格納
		int entYear = Integer.parseInt(request.getParameter("ent_year"));
		String classNum = request.getParameter("class_num");
		SubjectDao subDao = new SubjectDao();

		//本来はこれが正しいです、subjectDaoが完成次第こっちに
		Subject subject = subDao.get(request.getParameter("subject"), user.getSchool());

		//仮実装部分
		/*
		Subject subject = new Subject();
		subject.setCd(request.getParameter("subject"));
		subject.setName("仮科目名");
		*/

		//動作確認用出力
		System.out.println("入学年度　："+entYear);
		System.out.println("クラス番号："+classNum);
		System.out.println("科目コード："+subject.getCd());

		//フィルターの入力が不十分
		if (entYear == 0 ||
			classNum.equals("000") ||
			Objects.isNull(subject.getCd())){
			request.setAttribute("shortageFilterMessage", "入学年度、クラス、科目を選択してください");
		}

		TestListSubjectDao tsubDao = new TestListSubjectDao();
		//入学年度、クラス番号、学生番号、氏名、点数を取得
		List<TestListSubject> tsubList = tsubDao.filter(entYear, classNum, subject, user.getSchool());

		//リクエストスコープに値を格納
		//検索結果
		request.setAttribute("tsubList", tsubList);
		//科目名
		request.setAttribute("subjectName", subject.getName());

		request.setAttribute("entYear", entYear);
		request.setAttribute("classNum", classNum);
		request.setAttribute("subjectCd", subject.getCd());

		//ヘッダー用
		request.setAttribute("testListHeader", "成績参照（科目別検索結果）");

		//検索結果が１件もない
		if (tsubList.size() <= 0){
			request.setAttribute("noTestMessage", "該当の成績が存在しません");
		}

		//検索結果表示フラグ
		request.setAttribute("testListSubjectFlag", true);

		return "test_list.jsp";
	}
}