package main;

import java.time.LocalDate;
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

public class SubjectListAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		StudentDao sDao = new StudentDao();

		//値の格納
		int entYear = Integer.parseInt(request.getParameter("entYear"));
		String classNum = request.getParameter("classNum");
		boolean isAttend = Boolean.parseBoolean(request.getParameter("isAttend"));

		List<Student> sList = new ArrayList<>();

		if (Objects.isNull(entYear) && Objects.isNull(classNum) && Objects.isNull(isAttend)){
			//何も値が入っていない(初回遷移時)
			sList = sDao.filter(user.getSchool());

		}else if (Objects.isNull(entYear) && Objects.isNull(classNum)){
			//入学年度クラス指定なし
			sList = sDao.filter(user.getSchool(), isAttend);

		}else if (Objects.isNull(entYear)){
			//入学年度のみ指定なし
			sList = sDao.filter(user.getSchool(), classNum, isAttend);

		}else if (Objects.isNull(classNum)){
			//クラスのみ指定なし
			sList = sDao.filter(user.getSchool(), entYear, isAttend);

		}else{
			//すべて指定あり
			sList = sDao.filter(user.getSchool(), entYear, classNum, isAttend);

		}

		//入学年度選択用
		List<Integer> year = new ArrayList<>();
		LocalDate now = LocalDate.now();
		int nowYear = now.getYear();

		//今が1～3月なら今の年-1、年度を取得したいため
		if (now.getMonthValue() <= 3){
			nowYear--;
		}

		for (int y = nowYear+1; y < nowYear-10; y--){
			year.add(y);
		}

		//クラス選択用
		ClassNumDao cDao = new ClassNumDao();
		List<String> cList = cDao.filter(user.getSchool());

		session.setAttribute("sList", sList);
		session.setAttribute("cList", cList);
		session.setAttribute("year", year);

		return "subject_list.jsp";
	}
}