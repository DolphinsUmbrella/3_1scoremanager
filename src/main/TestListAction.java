package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
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

		String school = user.getSchool();

		ClassNumDao cDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();

		List<String> cList = cDao.filter(school);
		List<Subject> subList = subDao.filter(school);

		//入学年度選択用
		List<Integer> year = new ArrayList<>();
		LocalDate now = LocalDate.now();
		int nowYear = now.getYear();

		//今が1～3月なら今の年-1、年度を取得したいため
		if (now.getMonthValue() <= 3){
			nowYear--;
		}

		for (int y = nowYear+1; y > nowYear-10; y--){
			year.add(y);
		}

		request.setAttribute("cList", cList);
		request.setAttribute("subList", subList);
		request.setAttribute("year", year);

		return "test_list.jsp";
	}
}