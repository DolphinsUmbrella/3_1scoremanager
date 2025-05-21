package main;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
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

		//科目がない
		if (subList.size() <= 0){
			request.setAttribute("noSubMessage", "科目がありません");
		}

		return "subject_list.jsp";
	}
}