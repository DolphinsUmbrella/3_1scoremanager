package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		//4/25小柿：student_noを持ってくる
		//	どんな名前で値が入ってくるかわからないため仮
		String no = request.getParameter("no");

		System.out.print("成績検索(学生)：");
		System.out.println(no);

		//学生番号が入力されていない
		if (no.isEmpty()){
			request.setAttribute("shortageFilterMessage", "学生番号を入力して下さい");

			//エラー回避のため空のlistを作成
			List<TestListStudent> list = new ArrayList<>();
			request.setAttribute("tstuList", list);
		}
		else{
			StudentDao sDao = new StudentDao();

			Student stu = new Student();
			stu.setNo(no);
			stu.setName(sDao.get(no).getName());

			//4/25小柿：この辺もDao待ち
			TestListStudentDao tstuDao = new TestListStudentDao();
			List<TestListStudent> list = tstuDao.filter(stu);

			//確認用出力
			for (TestListStudent s : list){
				System.out.println(s.getSubjectName()+"："+s.getNum()+"："+s.getPoint());
			}

			request.setAttribute("tstuList", list);
			request.setAttribute("student", stu);

			//検索結果が無い場合のメッセージ
			if (list.size() <= 0){
				//そもそも存在しない学生番号だった
				if (Objects.isNull(stu.getName())){
					request.setAttribute("noTestMessage", "学生番号"+no+"の生徒が存在しません");
				}
				//シンプル成績が無かった
				else{
					request.setAttribute("noTestMessage", "学生番号"+no+"の生徒は成績が登録されていません");
				}
			}
		}

		//sessionの理由忘れた、あとで変えるかも
		request.setAttribute("studentNo", no);

		//ヘッダー用
		request.setAttribute("testListHeader", "成績参照（学生別検索結果）");

		//検索結果表示フラグ
		request.setAttribute("testListStudentFlag", true);

		return "test_list.jsp";
	}
}