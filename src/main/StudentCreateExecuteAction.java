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

			///学生登録完了画面の処理

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

			//エラーメッセージを表示(例外処理)

			if(entYearStr.equals("none")){
				request.setAttribute("入学年度を選択してください。");
			}

			if(no.equals("null")){
				request.setAttribute("学生番号が重複しています。");
			}

			//学生番号、氏名が未入力の場合

			if(no.equals())





			//登録処理
			Student student = new Student();
			student.setNo(no);
			student.setName(name);
			student.setEntYear(Integer.parseInt(entYearStr));

			student.setClassNum(classNum);
			student.setSchool(user.getSchool());

			boolean result = sDao.save(student);//学生登録





		// 学生登録完了画面を表示

		return "student_create_done.jsp";
	}
}