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


		String entYear = request.getParameter("entYear");
		String classNum = request.getParameter("class_num");
		String subjectdata = request.getParameter("subject");
		String num = request.getParameter("count");

		if (Objects.isNull(entYear) && Objects.isNull(classNum) && Objects.isNull(subjectdata)&&Objects.isNull(num)){
			//何も値が入っていない(初回遷移時)

			//userの所属する学校のクラス情報を取得
			ClassNumDao cDao = new ClassNumDao();
			List<String> cList = cDao.filter(user.getSchool());

			//userの所属する学校の科目情報を取得
			SubjectDao subDao = new SubjectDao();
			List<Subject> subList = subDao.filter(user.getSchool());

			StudentDao stuDao = new StudentDao();
			List<Integer> year = stuDao.selectInt_Year();

			request.setAttribute("cList", cList);
			request.setAttribute("subList", subList);
			request.setAttribute("year", year);
			return "test_regist.jsp";
		}
		//入力された入学年度、クラス、科目、回数のデータを取得
		Test t = new Test();
		Subject sub = new Subject();
		sub.setCd(subjectdata);
		TestDao tDao = new TestDao();
		List<Test> tList = tDao.filter(Integer.parseInt(entYear),classNum,t.getSubject(),Integer.parseInt(num),user.getSchool());

		request.setAttribute("tList", tList);
		    return "test_regist.jsp";
		}
}