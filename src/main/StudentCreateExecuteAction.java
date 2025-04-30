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
			sDao.insert(student);//学生登録

			//値の格納

			//入学年度
			String entYearStr = request.getParameter("entYear");

			//学生番号
			String no = request.getParameter("no");

			//氏名
			String name = request.getParameter("name");

			//クラス
			String classNum = request.getParameter("classNum");



			//登録処理
			Student student = new Student();
			student.setNo(no);
			student.setName(name);
			student.setEntYear(Integer.parseInt(entYearStr));
			student.setClassNum(classNum);
			student.setSchool(user.getSchool());



			List<Student> sList = new ArrayList<>();

			//入力情報をチェック
			if (no == null || no.isEmpty()|| name== null || name.isEmpty()
					|| entYearStr == null|| entYearStr.isEmpty()
					|| classNum == null || classNum.isEmpty()){

				//エラー画面
				request.setAttribute("error","このフィールドを入力してください。");
			}

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



			//クラス選択用
			ClassNumDao cDao = new ClassNumDao();
			List<ClassNum> cList = cDao.get("", user.getSchool());

			request.setAttribute("sList", sList);
			request.setAttribute("cList", cList);
			request.setAttribute("year", year);


		// 学生登録完了画面を表示

		return "student_create_done.jsp";
	}
}