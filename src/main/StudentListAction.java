package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action{
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

		//入学年度指定なしの場合0が格納されます
		String entYearStr = request.getParameter("entYear");

		//クラス番号指定なしの場合000が格納されます
		String classNum = request.getParameter("classNum");

		//初回遷移時はis_attendがnullとなっている　null判定をするため文字列型で保持
		String isAttendStr = request.getParameter("isAttend");

		//確認用
		System.out.println("スコープに格納された値");
		System.out.println("入学年度　："+entYearStr);
		System.out.println("クラス番号："+classNum);
		System.out.println("在学フラグ："+isAttendStr);

		List<Student> sList = new ArrayList<>();

		if (Objects.isNull(entYearStr) && Objects.isNull(classNum) && Objects.isNull(isAttendStr)){
			//何も値が入っていない(初回遷移時)
			sList = sDao.filter(user.getSchool());

		}else if (entYearStr.equals("0") && classNum.equals("000")){
			//入学年度クラス指定なし
			sList = sDao.filter(user.getSchool(), Boolean.parseBoolean(isAttendStr));

		}else if (entYearStr.equals("0")){
			//入学年度のみ指定なし
			sList = sDao.filter(user.getSchool(), classNum, Boolean.parseBoolean(isAttendStr));

		}else if (classNum.equals("000")){
			//クラスのみ指定なし
			sList = sDao.filter(user.getSchool(), Integer.parseInt(entYearStr), Boolean.parseBoolean(isAttendStr));

		}else{
			//すべて指定あり
			sList = sDao.filter(user.getSchool(), Integer.parseInt(entYearStr), classNum, Boolean.parseBoolean(isAttendStr));

		}

		//入学年度選択用
		List<Integer> year = sDao.selectInt_Year();

		//List<Integer> year = new ArrayList<>();

		//LocalDate now = LocalDate.now();
		//int nowYear = now.getYear();

		//今が1～3月なら今の年-1、年度を取得したいため
		//if (now.getMonthValue() <= 3){
		//	nowYear--;
		//}

		//for (int y = nowYear+1; y > nowYear-10; y--){
		//	year.add(y);
		//}

		//クラス選択用
		List<String> cList = sDao.selectClass_Num();

		//ClassNumDao cDao = new ClassNumDao();
		//List<String> cList = cDao.filter(user.getSchool());

		request.setAttribute("sList", sList);
		request.setAttribute("cList", cList);
		request.setAttribute("year", year);

		return "student_list.jsp";
	}
}