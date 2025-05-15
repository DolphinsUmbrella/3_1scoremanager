package main;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		StudentDao sDao = new StudentDao();
		ClassNumDao cDao = new ClassNumDao();

		//クラス選択用

		//流れは合ってます
		//使うメソッドが違うのでそれだけ修正してください
		//ClassNumDaoのfilterを使います。引数に注意してください
		List<String> cList = cDao.filter(user.getSchool());

		//年度選択用
		List<Integer> year = sDao.selectInt_Year();
		year.add(0, year.get(0)+1);

		request.setAttribute("cList", cList);

		request.setAttribute("year", year);

		//学生登録画面を表示

		return "student_create.jsp";
	}
}