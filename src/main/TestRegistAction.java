package main;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}


		String entYear = request.getParameter("ent_year");
		String classNum = request.getParameter("class_num");
		String subjectCd = request.getParameter("subject");
		String num = request.getParameter("count");

		System.out.println("入学年度："+entYear);
		System.out.println("クラス番号："+classNum);
		System.out.println("科目コード："+subjectCd);
		System.out.println("回数："+num+"回目");

		//userの所属する学校のクラス情報を取得
		ClassNumDao cDao = new ClassNumDao();
		List<String> cList = cDao.filter(user.getSchool());

		//userの所属する学校の科目情報を取得
		SubjectDao subDao = new SubjectDao();
		List<Subject> subList = subDao.filter(user.getSchool());

		//入学年度一覧を取得
		StudentDao stuDao = new StudentDao();
		List<Integer> year = stuDao.selectInt_Year();

		request.setAttribute("cList", cList);
		request.setAttribute("subList", subList);
		request.setAttribute("year", year);

		if (Objects.isNull(entYear)||
			Objects.isNull(classNum) ||
			Objects.isNull(subjectCd)||
			Objects.isNull(num)){

			//何も値が入っていない(初回遷移時)
			if (Objects.isNull(entYear)&&
				Objects.isNull(classNum) &&
				Objects.isNull(subjectCd)&&
				Objects.isNull(num)){
				System.out.println("初回遷移です");
				return "test_regist.jsp";
			}
			//何か値が入ってる
			else{
				System.out.println("入力値が不足しています");
				request.setAttribute("entYear", entYear);
				request.setAttribute("classNum", classNum);
				request.setAttribute("subject", subjectCd);
				request.setAttribute("count", num);
				//エラーメッセージ
				request.setAttribute("nullParameterMessage",
									 "入学年度とクラスと科目と回数を入力して下さい");
				return "test_regist.jsp";
			}
		}
		//入力された入学年度、クラス、科目、回数のデータを取得
		System.out.println("検索を実行します");

		Subject sub = subDao.get(subjectCd, user.getSchool());

		TestDao tDao = new TestDao();
		List<Test> tList = tDao.filter(Integer.parseInt(entYear),classNum,sub,Integer.parseInt(num),
										user.getSchool());

		System.out.println("件数："+tList.size());

		request.setAttribute("tList", tList);
		request.setAttribute("count", num);
		request.setAttribute("subject", sub);
		request.setAttribute("searchTestList", true);

		//初期値セット用
		request.setAttribute("entYear", entYear);
		request.setAttribute("classNum", classNum);

		return "test_regist.jsp";
		}
}