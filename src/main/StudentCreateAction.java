package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
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


		//入学年度指定なしの場合0が格納されます
		String entYearStr = request.getParameter("entYear");

		//クラス番号指定なしの場合000が格納されます
		String classNum = request.getParameter("classNum");

		//初回遷移時はis_attendがnullとなっている　null判定をするため文字列型で保持
		String isAttendStr = request.getParameter("isAttend");


		System.out.println("クラス番号："+classNum);

		List<Student> sList = new ArrayList<>();


		if (Objects.isNull(classNum)){
			sList = sDao.filter(user.getSchool());
		}else if (classNum.equals("000")){
			//クラスのみ指定なし
			sList = sDao.filter(user.getSchool(), Integer.parseInt(entYearStr), Boolean.parseBoolean(isAttendStr));

		}


		//クラス選択用

		//流れは合ってます
		//使うメソッドが違うのでそれだけ修正してください
		//ClassNumDaoのfilterを使います。引数に注意してください
		List<String> cList = cDao.filter(user.getSchool());

		request.setAttribute("sList", sList);
		request.setAttribute("cList", cList);

		request.setAttribute("classNum", classNum);

		//学生登録画面を表示

		return "student_create.jsp";
	}
}