package main;

import java.time.LocalDate;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		//学生登録完了画面の処理

			StudentDao sDao = new StudentDao();

			//値の格納

			//入学年度
			String entYearStr = request.getParameter("entYear");

			//学生番号
			String no = request.getParameter("no");

			//氏名
			String name = request.getParameter("name");

			//クラス
			String classNum = request.getParameter("classNum");



			List<Student> sList = new ArrayList<>();

			if (Objects.isNull(entYearStr) && Objects.isNull(classNum)){
				//何も値が入っていない(初回遷移時)
				sList = sDao.filter(user.getSchool());

			}else if (entYearStr.equals("0") && classNum.equals("000")){
				//入学年度クラス指定なし
				sList = sDao.filter(user.getSchool());

			}else if (entYearStr.equals("0")){
				//入学年度のみ指定なし
				sList = sDao.filter(user.getSchool(), classNum);

			}else if (classNum.equals("000")){
				//クラスのみ指定なし
				sList = sDao.filter(user.getSchool(), Integer.parseInt(entYearStr));

			}else{
				//すべて指定あり
				sList = sDao.filter(user.getSchool(), Integer.parseInt(entYearStr), classNum);

			}

			//入学年度選択用
			List<Integer> year = new ArrayList<>();
			LocalDate now = LocalDate.now();
			int nowYear = now.getYear();


			//クラス選択用
			ClassNumDao cDao = new ClassNumDao();
			List<ClassNum> cList = cDao.get("", user.getSchool());

			request.setAttribute("sList", sList);
			request.setAttribute("cList", cList);
			request.setAttribute("year", year);


			//エラー画面


			//学生登録完了画面を表示

		return "student_create_done.jsp";
	}
}