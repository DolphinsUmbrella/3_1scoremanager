package main;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		//リストの情報がセッションに残っていた場合削除します

		School school = user.getSchool();

		ClassNumDao cDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();

		List<String> cList = cDao.filter(school);

		//5/1小柿：動作確認のため一時的にコメントアウトしてます、こっちのが正しいです
		List<Subject> subList = subDao.filter(school);

		//入学年度取得
		StudentDao stuDao = new StudentDao();
		List<Integer> year = stuDao.selectInt_Year();

		//入学年度は最大10年分
		if (year.size() > 10){
			year = year.subList(year.size()-11, year.size()-1);
		}

		//ここから入学年度選択用までの間のやつは後で消してください
		/*
		List<Subject> subList = new ArrayList<>();
		Subject s1 = new Subject();
		s1.setCd("101");
		Subject s2 = new Subject();
		s1.setCd("102");
		Subject s3 = new Subject();
		s1.setCd("103");
		subList.add(s1);
		subList.add(s2);
		subList.add(s3);
		*/

		session.setAttribute("cList", cList);
		session.setAttribute("subList", subList);
		session.setAttribute("year", year);

		//header用
		request.setAttribute("testListHeader", "成績参照");

		return "test_list.jsp";
	}
}