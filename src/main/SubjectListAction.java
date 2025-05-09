package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Student;
import bean.Teacher;
import bean.School;
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

		SubjectDao subDao = new SubjectDao();


		List<Subject> subList = subDao.filter(user.getSchool());

		request.setAttribute("subList", subList);

		return "subject_list.jsp";
	}
}