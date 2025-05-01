package main;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

//入力した変更の値に更新するアクション
public class StudentUpdateExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}



		return "student_update_done.jsp";
	}
}