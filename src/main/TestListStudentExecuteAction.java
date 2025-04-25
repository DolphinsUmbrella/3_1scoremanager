package main;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		//4/25小柿：student_noを持ってくる
		//	どんな名前で値が入ってくるかわからないため仮
		String no = request.getParameter("no");

		//4/25小柿：この辺もDao待ち
		TestListStudentDao tstuDao = new TestListStudentDao();
		List<Test> list = tstuDao.filter(no);



		return "test_list_student.jsp";
	}
}