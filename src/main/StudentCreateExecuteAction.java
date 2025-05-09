package main;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
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
			Student s = new Student();
			s.setNo(no);
			s.setName(name);
			s.setEntYear(Integer.parseInt(entYearStr));
			s.setClassNum(classNum);
			s.setSchool(user.getSchool());

			StudentDao sDao = new StudentDao(); //学生Dao

			//入力値をチェック エラーメッセージを表示

			//入力年度が選択されてない場合
			if(entYearStr.equals("none")){
				request.setAttribute("entYear", entYearStr);
				request.setAttribute("message","入学年度を選択してください。");

				return "student_create_done.jsp";
			}

			//入力された学生番号が重複してる場合
			if(Objects.nonNull(sDao.get(no))){
				request.setAttribute("no",no);
				request.setAttribute("name", name);
				request.setAttribute("classNum", classNum);
				request.setAttribute("message", "学生番号が重複しています。");

				return "student_create_done.jsp";

			}

			boolean result = sDao.save(s);//学生登録








		// 学生登録完了画面を表示

		return "student_create_done.jsp";
	}
}